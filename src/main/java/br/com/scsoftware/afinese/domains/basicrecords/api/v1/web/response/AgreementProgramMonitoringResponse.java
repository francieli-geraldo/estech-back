package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;
import br.com.scsoftware.afinese.infrastructure.common.serializer.CustomBigDecimalSerializer;
import br.com.scsoftware.afinese.infrastructure.common.serializer.PercentBigDecimalSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class AgreementProgramMonitoringResponse {
    private String groupName;
    private String patientName;
    private String programName;
    private StatusAgreement status;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal startingWeight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal currentWeight;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal accumulatedEvolution;
    private LocalDate startDate;
    private LocalDate dateConclusion;
    private Integer breakfastTotal;
    private Integer morningSnackTotal;
    private Integer lunchTotal;
    private Integer afternoonSnackTotal;
    private Integer dinnerTotal;
    private Integer hiitTotal;
    @JsonSerialize(using = PercentBigDecimalSerializer.class)
    private BigDecimal postingPercentage;
    private Integer balanceTotal;
    @JsonSerialize(using = PercentBigDecimalSerializer.class)
    private BigDecimal balancePercentage;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal evolutionPeriod;
    @JsonSerialize(using = CustomBigDecimalSerializer.class)
    private BigDecimal goal;
    private Integer notesTotal;

    List<DailyPostingProgramMonitoringResponse> dailyPosting;
}


