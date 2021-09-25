package br.com.scsoftware.afinese.domains.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author samuel-cruz
 */
public interface JWTUserDetailsService extends UserDetailsService {

    String authenticate(String username, String password, AuthenticationManager authenticationManager);

    void welcome(String username);

    void forgotPassword(String username);

    void resetPassword(Long userId, String password, String token);
}
