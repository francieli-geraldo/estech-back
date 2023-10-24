package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.estech.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
