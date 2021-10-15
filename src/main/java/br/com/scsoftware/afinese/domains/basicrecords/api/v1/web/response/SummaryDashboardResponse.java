package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class SummaryDashboardResponse {
    private Integer totalNewContractsMonth;
    private Integer totalNewContracts;
    private Integer totalCompletedContractsMonth;
    private Integer totalCompletedContracts;
    private Integer totalReleasesWeek;
    private Integer totalReleasesMonth;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalWeightMonth;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal totalWeight;

}


