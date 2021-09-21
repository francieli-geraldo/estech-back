package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateAgreement {
    @NotNull
    @Positive
    private Long patientId;
    @NotNull
    @Positive
    private Long programId;
    @NotNull
    @Positive
    private Long groupId;
    @NotNull
    @Positive
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal startingWeight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    @Positive
    private BigDecimal goal;
    @NotNull
    private LocalDate hiringDate;
    @NotNull
    private LocalDate startDate;
    private String notes;
}
