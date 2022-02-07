package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.LaunchedPendingDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummaryDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.LaunchedPendingDashboard;
import br.com.scsoftware.afinese.domains.basicrecords.business.SummaryDashboard;

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
