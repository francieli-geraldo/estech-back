package br.com.scsoftware.afinese.domains.auth.controller.api.v1.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author samuel-cruz
 */
@Data
public class LoginRequest {

    @NotBlank
    @Email
    private String username;
    @NotBlank
    @ToString.Exclude
    private String password;



}