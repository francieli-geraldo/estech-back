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
@Entity(name = "reasoncancellation")
@EqualsAndHashCode(callSuper = true)
public class ReasonCancellation extends BaseEntity {

    @NotBlank
    @Column(name = "reason")
    private String reason;

    @NotBlank
    @Column(name = "description")
    private String description;
}

