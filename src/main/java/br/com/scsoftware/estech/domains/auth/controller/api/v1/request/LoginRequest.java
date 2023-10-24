package br.com.scsoftware.estech.domains.auth.controller.api.v1.request;

import lombok.Data;
import lombok.ToString;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

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