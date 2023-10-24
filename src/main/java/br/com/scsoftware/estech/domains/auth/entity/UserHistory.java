package br.com.scsoftware.estech.domains.auth.entity;

import br.com.scsoftware.estech.infrastructure.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_history")
@EqualsAndHashCode(callSuper = true)
public class UserHistory extends BaseEntity {

    @NotBlank
    @Column(name = "username")
    private String username;

    public void setTenantId(Long tenantId) {
        super.setTenantId(tenantId);
    }
}
