package br.com.scsoftware.afinese.domains.auth.service.impl;

import br.com.scsoftware.afinese.domains.auth.business.AuthenticatedUserBO;
import br.com.scsoftware.afinese.domains.auth.business.UserBO;
import br.com.scsoftware.afinese.domains.auth.business.UserResetPasswordBO;
import br.com.scsoftware.afinese.domains.auth.converter.UserConverter;
import br.com.scsoftware.afinese.domains.auth.entity.User;
import br.com.scsoftware.afinese.domains.auth.repository.UserRepository;
import br.com.scsoftware.afinese.domains.auth.service.UserService;
import br.com.scsoftware.afinese.domains.basicrecords.service.impl.BaseServiceImpl;
import br.com.scsoftware.afinese.infrastructure.common.exception.ResourceNotFoundException;
import br.com.scsoftware.afinese.infrastructure.common.exception.UnauthorizedException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author samuel-cruz
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }

    @Override
    @Cacheable("user.username")
    public Optional<UserBO> getRecordByUserName(final String username) {
        return UserConverter.toBO(userRepository.findByUsername(username));
    }

    @Override
    public UserResetPasswordBO getUserForResetPassword(final Long userId) {
        return UserConverter.toUserResetPasswordBO(userRepository.findById(userId))
                .orElseThrow(ResourceNotFoundException::of);
    }

    @Override
    public UserBO listUser(final String username) {
        if (!hasHole("ADMIN") && !getAuthenticatedUser().getUsername().equals(username)) {
            throw ResourceNotFoundException.of();
        }
        return getRecordByUserName(username).orElseThrow(ResourceNotFoundException::of);
    }

    @Override
    public String getAvatar(final Long id) {
        if (!hasHole("ADMIN") && getAuthenticatedUser().getId().compareTo(id) != 0) {
            throw ResourceNotFoundException.of();
        }

        final User user = getRecord(id).orElseThrow(ResourceNotFoundException::of);
        if (Objects.isNull(user.getAvatar())) {
            return null;
        }
        return Base64.encodeBase64String(new ByteArrayInputStream(user.getAvatar()).readAllBytes());
    }

    @Override
    public boolean hasHole(final String roleName) {
        return getAuthenticatedUser().getAuthorities().stream()
                .filter(p -> p.getAuthority().equals("ROLE_".concat(roleName)))
                .findFirst()
                .isPresent();
    }

    @Override
    public String forgotPassword(final Long id, final String username) {
        final String passwordResetToken = UUID.randomUUID().toString()
                .concat(id.toString())
                .concat(UUID.randomUUID().toString())
                .toUpperCase().replace("-", "");
        final User user = getById(id);
        user.setPasswordResetToken(passwordResetToken);
        user.setPasswordResetExpires(Timestamp.valueOf(LocalDateTime.now().plusHours(1L)));
        repository.save(user);

        return passwordResetToken;
    }

    @Override
    public void updatePassword(final Long id, final String newPassword) {
        final User user = getById(id);
        user.setPasswordResetToken(null);
        user.setPasswordResetExpires(null);
        user.setPassword(newPassword);
        user.setChangePassword(false);
        repository.save(user);
    }

    public static AuthenticatedUserBO getAuthenticatedUser() {
        Authentication authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .orElseThrow(() -> new UnauthorizedException(""));

        return (AuthenticatedUserBO) authentication.getPrincipal();
    }

    public static Long getTenantIdAuthenticatedUser() {
        return getAuthenticatedUser().getTenantId();
    }

}
