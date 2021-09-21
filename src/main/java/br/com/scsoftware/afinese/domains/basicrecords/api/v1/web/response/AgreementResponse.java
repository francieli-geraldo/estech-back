package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class AgreementResponse {
    private Long id;
    private SummarizedPatientResponse patient;
    private ProgramResponse program;
    private GroupResponse group;
    private StatusAgreement status;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal startingWeight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal goal;
    private LocalDate hiringDate;
    private LocalDate startDate;
    private LocalDate dateConclusion;
    private LocalDate cancellationDate;
    private String reasonCancellation;
    private String notes;
}
