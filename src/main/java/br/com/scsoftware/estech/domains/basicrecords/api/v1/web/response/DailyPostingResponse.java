package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.BalanceDailyPosting;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class DailyPostingResponse {
    private Long id;
    private Long agreementId;
    @JsonIgnore
    private BigDecimal agreementStartingWeight;
    private Long patientId;
    private String patientName;
    private Long groupId;
    private String groupName;
    private LocalDate date;
    private BalanceDailyPosting balance;
    private Boolean breakfast;
    private Boolean morningSnack;
    private Boolean lunch;
    private Boolean afternoonSnack;
    private Boolean dinner;
    private Boolean hiit;
    private String notes;
}
