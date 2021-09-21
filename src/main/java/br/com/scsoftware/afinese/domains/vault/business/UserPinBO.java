package br.com.scsoftware.afinese.domains.vault.business;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserPinBO {
    private String platform;
    private String document;
    private LocalDateTime createdAt;
}
