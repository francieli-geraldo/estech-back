package br.com.scsoftware.estech.domains.basicrecords.converter;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.DailyPostingPendingDashboardResponse;
import br.com.scsoftware.estech.domains.basicrecords.business.DailyPostingPendingDashboard;

public class DailyPostingPendingDashboardConverter {
    public static DailyPostingPendingDashboardResponse toDTO(DailyPostingPendingDashboard dashboard) {
        return DailyPostingPendingDashboardResponse.builder()
                .patientId(dashboard.getPatientId())
                .patientName(dashboard.getPatientName())
                .patientPhone(dashboard.getPatientPhone())
                .programId(dashboard.getProgramId())
                .programName(dashboard.getProgramName())
                .agreementId(dashboard.getAgreementId())
                .build();
    }
}
