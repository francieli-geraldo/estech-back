package br.com.scsoftware.estech.domains.basicrecords.business;

import br.com.scsoftware.estech.domains.basicrecords.enums.StatusAgreement;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class CreateAgreementBO {
    private Long id;
    private Long patientId;
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
