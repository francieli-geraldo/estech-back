package br.com.scsoftware.estech.domains.auth.controller.api.v1.request;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * @author samuel-cruz
 */
@Data
public class WelcomePasswordRequest {

    @NotBlank
    @Email
    private String username;
}