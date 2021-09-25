package br.com.scsoftware.afinese.domains.auth.controller.api.v1.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author samuel-cruz
 */
@Data
public class WelcomePasswordRequest {

    @NotBlank
    @Email
    private String username;
}