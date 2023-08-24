package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class DailyPostingProgramMonitoringResponse {
    private LocalDate date;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal currentWeight;
}


