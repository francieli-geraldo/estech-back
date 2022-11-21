package br.com.scsoftware.afinese.domains.basicrecords.entity;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.domains.basicrecords.utils.DateUtils;
import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "agreement")
@EqualsAndHashCode(callSuper = true)
public class Agreement extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public StatusAgreement getStatus() {
        if (Objects.nonNull(status) && status.equals(StatusAgreement.ACTIVE)) {
            int numberOfDayBetween = DateUtils.numberOfDayBetween(LocalDate.now(), hiringDate);
            if (numberOfDayBetween < 0) {
                return StatusAgreement.OVERDUE;
            } else if (numberOfDayBetween <= 7) {
                return StatusAgreement.OVERDUE_LESS_7;
            } else if (numberOfDayBetween <= 15) {
                return StatusAgreement.OVERDUE_LESS_15;
            } else if (numberOfDayBetween <= 30) {
                return StatusAgreement.OVERDUE_LESS_30;
            }
        }
        return status;
    }

    public boolean isOpened() {
        if (Objects.isNull(status)) {
            return false;
        }

        return StatusAgreement.ACTIVE.equals(status) || StatusAgreement.OVERDUE.equals(status) || StatusAgreement.OVERDUE_LESS_7.equals(status) ||
                StatusAgreement.OVERDUE_LESS_15.equals(status) || StatusAgreement.OVERDUE_LESS_30.equals(status);
    }
}
