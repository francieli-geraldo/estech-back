package br.com.scsoftware.estech.domains.basicrecords.entity;

import br.com.scsoftware.estech.domains.basicrecords.enums.Sex;
import br.com.scsoftware.estech.infrastructure.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
