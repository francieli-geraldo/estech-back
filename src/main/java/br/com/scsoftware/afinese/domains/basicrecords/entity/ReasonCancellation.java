package br.com.scsoftware.afinese.domains.basicrecords.entity;

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

