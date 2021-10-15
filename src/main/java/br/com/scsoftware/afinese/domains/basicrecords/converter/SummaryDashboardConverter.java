package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.SummaryDashboardResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.SummaryDashboard;

public class SummaryDashboardConverter {
    public static SummaryDashboardResponse toDTO(SummaryDashboard dashboard) {
        return SummaryDashboardResponse.builder()
                .totalNewContractsMonth(dashboard.getTotalNewContractsMonth())
                .totalNewContracts(dashboard.getTotalNewContracts())
                .totalCompletedContractsMonth(dashboard.getTotalCompletedContractsMonth())
                .totalCompletedContracts(dashboard.getTotalCompletedContracts())
                .totalReleasesWeek(dashboard.getTotalReleasesWeek())
                .totalReleasesMonth(dashboard.getTotalReleasesMonth())
                .totalWeightMonth(dashboard.getTotalWeightMonth())
                .totalWeight(dashboard.getTotalWeight())
                .build();
    }
}
