package br.com.scsoftware.estech.domains.auth.converter;

import br.com.scsoftware.estech.domains.auth.business.RoleBO;
import br.com.scsoftware.estech.domains.auth.entity.Role;

public class RoleConverter {
    public static RoleBO toBO(Role role) {
        return RoleBO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
