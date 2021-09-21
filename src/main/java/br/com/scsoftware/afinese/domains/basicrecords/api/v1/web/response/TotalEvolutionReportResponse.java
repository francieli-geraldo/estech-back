package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import br.com.scsoftware.afinese.infrastructure.common.serializer.PercentBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class TotalEvolutionReportResponse {
    private String groupName;
    private String patientName;
    private String programName;
    private StatusAgreement status;
    @JsonSerialize(using = PercentBigDecimalSerializer.class)
    private BigDecimal postingPercentage;
    @JsonSerialize(using = PercentBigDecimalSerializer.class)
    private BigDecimal balancePercentage;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal evolutionPeriod;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal goal;
    @JsonSerialize(using = PercentBigDecimalSerializer.class)
    private BigDecimal goalPercentage;
    private Integer notesTotal;
}


