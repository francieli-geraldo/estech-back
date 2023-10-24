package br.com.scsoftware.estech.domains.basicrecords.entity;

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
@Entity(name = "grouppatients")
@EqualsAndHashCode(callSuper = true)
public class Group extends BaseEntity {

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;
}
