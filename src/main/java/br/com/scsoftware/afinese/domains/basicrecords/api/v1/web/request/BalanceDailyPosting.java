package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.request;

import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PositiveOrZero;
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
