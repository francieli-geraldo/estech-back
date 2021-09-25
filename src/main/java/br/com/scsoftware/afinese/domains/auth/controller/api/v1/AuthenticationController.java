package br.com.scsoftware.afinese.domains.auth.controller.api.v1;

import br.com.scsoftware.afinese.domains.auth.controller.api.v1.request.ForgotPasswordRequest;
import br.com.scsoftware.afinese.domains.auth.controller.api.v1.request.LoginRequest;
import br.com.scsoftware.afinese.domains.auth.controller.api.v1.request.ResetPasswordRequest;
import br.com.scsoftware.afinese.domains.auth.controller.api.v1.request.WelcomePasswordRequest;
import br.com.scsoftware.afinese.domains.auth.controller.api.v1.response.LoginResponse;
import br.com.scsoftware.afinese.domains.auth.service.JWTUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author samuel-cruz
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JWTUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginRequest request) {

        final String token = userDetailsService.authenticate(request.getUsername(), request.getPassword(),
                authenticationManager);
        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .build()
        );
    }

    @PostMapping("/welcome")
    public ResponseEntity<Void> welcome(@Valid @RequestBody WelcomePasswordRequest request) {

        userDetailsService.welcome(request.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {

        userDetailsService.forgotPassword(request.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reset-password/{token}")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request, @PathVariable final String token) {

        userDetailsService.resetPassword(request.getId(), passwordEncoder.encode(request.getPassword()), token);
        return ResponseEntity.noContent().build();
    }
}
