package br.com.scsoftware.estech.domains.basicrecords.business;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class DailyPostingBO {
    private Long id;
    private Long agreementId;
    private LocalDate date;
    private boolean balance;
    private BigDecimal previousWeight;
    private BigDecimal currentWeight;
    private BigDecimal evolution;
    private BigDecimal accumulatedEvolution;
    private boolean breakfast;
    private boolean morningSnack;
    private boolean lunch;
    private boolean afternoonSnack;
    private boolean dinner;
    private boolean hiit;
    private String notes;
}
