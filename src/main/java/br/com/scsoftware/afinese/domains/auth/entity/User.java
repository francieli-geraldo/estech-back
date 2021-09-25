package br.com.scsoftware.afinese.domains.auth.entity;

import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity(name = "user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "username")
    private String username;

    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "password_reset_token")
    @Lob
    private String passwordResetToken;

    @Column(name = "password_reset_expires")
    @Temporal(TemporalType.TIMESTAMP)
    @Lob
    private Date passwordResetExpires;

    @Column(name = "avatar")
    @Lob
    private byte[] avatar;

    @Column(name = "change_password")
    private boolean changePassword;

    @Column(name = "account_locked")
    private boolean accountLocked;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @Where(clause = "active=true")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<UserRole> roles;
}
