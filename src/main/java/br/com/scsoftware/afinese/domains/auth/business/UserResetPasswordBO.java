package br.com.scsoftware.afinese.domains.auth.business;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResetPasswordBO {
    private Long id;
    private String name;
    private String username;
    private String passwordResetToken;
    private LocalDateTime passwordResetExpires;
}
