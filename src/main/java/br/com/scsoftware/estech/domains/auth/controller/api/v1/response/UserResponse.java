package br.com.scsoftware.estech.domains.auth.controller.api.v1.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author samuel-cruz
 */
@Builder
@Getter
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private boolean changePassword;
    private boolean accountLocked;
    private String avatar;
    private List<String> roles;
}