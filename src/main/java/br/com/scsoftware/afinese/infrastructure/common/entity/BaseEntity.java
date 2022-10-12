package br.com.scsoftware.afinese.infrastructure.common.entity;

import br.com.scsoftware.afinese.domains.auth.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Column(name = "id")
    private Long id;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at", columnDefinition = "DATETIME")
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "tenant_id")
    private Long tenantId;

    @PrePersist
    private void beforeSave() {
        if (id == null && tenantId == null)
            tenantId = UserServiceImpl.getTenantIdAuthenticatedUser();
    }

    public void invalidate() {
        active = false;
    }
}
