package br.com.scsoftware.afinese.domains.vault.converter;

import br.com.scsoftware.afinese.domains.vault.api.v1.web.response.KeyPairResponse;

import java.security.KeyPair;
import java.util.Base64;

public final class VaultConverter {

    public static KeyPairResponse convertFrom(KeyPair keyPair) {
        Base64.Encoder base64 = Base64.getMimeEncoder();
        return KeyPairResponse.builder()
                .privateKey(base64.encodeToString(keyPair.getPrivate().getEncoded()))
                .publicKey(base64.encodeToString(keyPair.getPublic().getEncoded()))
                .build();
    }
}
