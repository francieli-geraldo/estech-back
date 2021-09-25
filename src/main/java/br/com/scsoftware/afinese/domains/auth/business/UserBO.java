package br.com.scsoftware.afinese.domains.auth.business;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserBO {
    private Long id;
    private Long tenantId;
    private String name;
    private String username;
    private String password;
    private boolean changePassword;
    private boolean accountLocked;
    private List<RoleBO> roles;
}
