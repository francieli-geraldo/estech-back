package br.com.scsoftware.afinese.domains.vault.business;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPinCreate {

    private String document;
    private String platform;
    private String encryptedPin;
}
