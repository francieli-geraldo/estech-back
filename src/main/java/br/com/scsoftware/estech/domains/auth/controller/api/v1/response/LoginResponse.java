package br.com.scsoftware.estech.domains.auth.controller.api.v1.response;

import lombok.Builder;
import lombok.Getter;

/**
 * @author samuel-cruz
 */
@Builder
@Getter
public class LoginResponse {

    private final String token;
}