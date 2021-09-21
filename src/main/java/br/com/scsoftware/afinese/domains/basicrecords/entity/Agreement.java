package br.com.scsoftware.afinese.domains.basicrecords.entity;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "agreement")
@EqualsAndHashCode(callSuper = true)
public class Agreement extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAgreement status;

    @NotNull
    @Column(name = "starting_weight", precision = 6, scale = 3)
    private BigDecimal startingWeight;

    @Column(name = "goal", precision = 6, scale = 3)
    private BigDecimal goal;

    @NotNull
    @Column(name = "hiring_date")
    private LocalDate hiringDate;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "date_conclusion")
    private LocalDate dateConclusion;

    @Column(name = "cancellation_date")
    private LocalDate cancellationDate;

    @Column(name = "reason_cancellation")
    private String reasonCancellation;

    @Column(name = "notes")
    private String notes;
}
