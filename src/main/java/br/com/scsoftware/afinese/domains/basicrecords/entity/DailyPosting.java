package br.com.scsoftware.afinese.domains.basicrecords.entity;

import br.com.scsoftware.afinese.infrastructure.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "dailyposting")
@EqualsAndHashCode(callSuper = true)
public class DailyPosting extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "agreement_id")
    private Agreement agreement;

    @Column(name = "release_date")
    @NotNull
    private LocalDate date;

    @Column(name = "balance")
    private boolean balance;

    @Column(name = "previous_weight", precision = 6, scale = 3)
    private BigDecimal previousWeight;

    @Column(name = "current_weight", precision = 6, scale = 3)
    private BigDecimal currentWeight;

    @Column(name = "evolution", precision = 6, scale = 3)
    private BigDecimal evolution;

    @Column(name = "accumulated_evolution", precision = 6, scale = 3)
    private BigDecimal accumulatedEvolution;

    @Column(name = "breakfast")
    private boolean breakfast;

    @Column(name = "morning_snack")
    private boolean morningSnack;

    @Column(name = "lunch")
    private boolean lunch;

    @Column(name = "afternoon_snack")
    private boolean afternoonSnack;

    @Column(name = "dinner")
    private boolean dinner;

    @Column(name = "hiit")
    private boolean hiit;

    @Column(name = "notes")
    private String notes;
}
