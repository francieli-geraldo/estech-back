package br.com.scsoftware.afinese.domains.basicrecords.business;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class UpdateAgreementBO {
    private Long id;
    private Long programId;
    private Long groupId;
    private StatusAgreement status;
    private BigDecimal startingWeight;
    private BigDecimal goal;
    private LocalDate hiringDate;
    private LocalDate startDate;
    private LocalDate dateConclusion;
    private LocalDate cancellationDate;
    private String reasonCancellation;
    private String notes;
}
