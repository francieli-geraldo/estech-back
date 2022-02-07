package br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class DailyPostingPendingDashboardResponse {
    private Integer patientId;
    private String patientName;
    private Integer groupId;
    private String groupName;
    private Long agreementId;

}


