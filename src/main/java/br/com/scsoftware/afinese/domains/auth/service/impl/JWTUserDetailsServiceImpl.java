package br.com.scsoftware.afinese.domains.auth.service.impl;


import br.com.scsoftware.afinese.domains.auth.business.AuthenticatedUserBO;
import br.com.scsoftware.afinese.domains.auth.business.UserBO;
import br.com.scsoftware.afinese.domains.auth.business.UserResetPasswordBO;
import br.com.scsoftware.afinese.domains.auth.service.EmailService;
import br.com.scsoftware.afinese.domains.auth.service.JWTUserDetailsService;
import br.com.scsoftware.afinese.domains.auth.service.Mail;
import br.com.scsoftware.afinese.domains.auth.service.UserService;
import br.com.scsoftware.afinese.infrastructure.common.exception.BusinessException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ForbiddentException;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import br.com.scsoftware.afinese.infrastructure.config.security.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author samuel-cruz
 */
@Service
@AllArgsConstructor
@Slf4j
public class JWTUserDetailsServiceImpl implements JWTUserDetailsService {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final EmailService emailService;

    @Override
    @Cacheable("user.email")
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserBO user = userService.getRecordByUserName(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        final UserDetails userWithRoles = User.builder()
                .username(email)
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(userRole -> userRole.getName())
                        .collect(Collectors.joining(","))
                        .split(",")
                )
                .accountLocked(user.isAccountLocked())
                .build();

        return AuthenticatedUserBO.builder()
                .id(user.getId())
                .tenantId(user.getTenantId())
                .name(user.getName())
                .username(email)
                .password(user.getPassword())
                .changePassword(user.isChangePassword())
                .accountLocked(user.isAccountLocked())
                .authorities(userWithRoles.getAuthorities())
                .build();
    }

    @Override
    public String authenticate(final String username, final String password, final AuthenticationManager authenticationManager) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            return jwtTokenUtil.generateToken(loadUserByUsername(username));
        } catch (DisabledException | LockedException e) {
            throw new ForbiddentException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new ForbiddentException("INVALID_CREDENTIALS");
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void welcome(final String username) {
        final UserBO userBO = userService.getRecordByUserName(username).orElseThrow(ResourceNotFoundException::of);

        Map<String, Object> model = new HashMap<>();
        model.put("firstName", userBO.getFamilyName());
        model.put("email", username);

        final Mail email = Mail.builder()
                .subject("Boas vindas ao eSTech")
                .to(username)
                .model(model)
                .build();

        emailService.sendEmail(email, "email-welcome");
    }

    @Override
    public void forgotPassword(final String username) {
        final UserBO userBO = userService.getRecordByUserName(username).orElse(null);
        if (userBO == null) {
            log.error("User {} not found.", username);
            return;
        }


        final String passwordResetToken = userService.forgotPassword(userBO.getId(), username);

        Map<String, Object> model = new HashMap<>();
        model.put("firstName", userBO.getFamilyName());
        model.put("email", username);
        model.put("date", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
        model.put("token", passwordResetToken);

        final Mail email = Mail.builder()
                .subject("Solicitação de recuperação de senha - eSTech")
                .to(username)
                .model(model)
                .build();

        emailService.sendEmail(email, "email-forgot-password");
    }

    @Override
    public void resetPassword(final Long userId, final String password, final String token) {
        final String id = token.substring(32, token.length() - 32);

        if (StringUtils.isBlank(id) || !userId.toString().equals(id))
            throw new BusinessException("Token corrompido.");

        final UserResetPasswordBO userBO = userService.getUserForResetPassword(userId);
        if (!token.equals(userBO.getPasswordResetToken()) || LocalDateTime.now().isAfter(userBO.getPasswordResetExpires())) {
            throw new BusinessException("O token é inválido ou expirou.");
        }

        userService.updatePassword(userBO.getId(), password);
    }
}
