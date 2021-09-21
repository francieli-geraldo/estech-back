package br.com.scsoftware.afinese.domains.basicrecords.entity;

import br.com.scsoftware.afinese.domains.basicrecords.enums.Sex;
import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "patient")
@EqualsAndHashCode(callSuper = true)
public class Patient extends BaseEntity {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;
}
