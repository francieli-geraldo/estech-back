package br.com.scsoftware.estech.domains.basicrecords.converter;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.LaunchedPendingDashboardResponse;
import br.com.scsoftware.estech.domains.basicrecords.business.LaunchedPendingDashboard;

public class LaunchedPendingDashboardConverter {
    public static LaunchedPendingDashboardResponse toDTO(LaunchedPendingDashboard dashboard) {
        return LaunchedPendingDashboardResponse.builder()
                .groupId(dashboard.getGroupId())
                .groupName(dashboard.getGroupName())
                .totalLaunched(dashboard.getTotalLaunched())
                .totalPending(dashboard.getTotalPending())
                .build();
    }
}
