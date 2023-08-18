package br.com.scsoftware.afinese.domains.auth.entity;

import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

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
