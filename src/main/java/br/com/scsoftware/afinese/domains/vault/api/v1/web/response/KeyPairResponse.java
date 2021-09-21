package br.com.scsoftware.afinese.domains.vault.api.v1.web.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KeyPairResponse {

    private String publicKey;
    private String privateKey;
}
