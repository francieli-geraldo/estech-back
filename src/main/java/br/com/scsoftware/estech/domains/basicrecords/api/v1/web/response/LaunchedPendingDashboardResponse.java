package br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author samuel-cruz
 */
@Data
@Builder
public class LaunchedPendingDashboardResponse {
    private Integer groupId;
    private String groupName;
    private Integer totalLaunched;
    private Integer totalPending;

}


