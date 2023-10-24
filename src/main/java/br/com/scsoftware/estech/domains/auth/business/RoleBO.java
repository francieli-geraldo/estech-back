package br.com.scsoftware.estech.domains.auth.business;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoleBO {
    private Long id;
    private String name;
}
