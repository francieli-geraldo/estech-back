package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.estech.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@Builder
public class BalanceDailyPosting {
    @NotNull
    private Boolean informed;
    @Null
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal previousWeight;
    // @NotNull
    @PositiveOrZero
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal currentWeight;
    @Null
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal evolution;
    @Null
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal accumulatedEvolution;
}
