package br.com.scsoftware.estech.domains.auth.controller.api.v1.request;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * @author samuel-cruz
 */
@Data
public class ResetPasswordRequest {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    @ToString.Exclude
    private String password;

}