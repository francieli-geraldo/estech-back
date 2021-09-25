package br.com.scsoftware.afinese.domains.auth.controller.api.v1.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author samuel-cruz
 */
@Data
public class ResetPasswordRequest {

    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String password;

}