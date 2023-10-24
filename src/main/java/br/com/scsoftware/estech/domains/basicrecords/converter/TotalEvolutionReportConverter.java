package br.com.scsoftware.estech.domains.basicrecords.converter;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.TotalEvolutionReportResponse;
import br.com.scsoftware.estech.domains.basicrecords.business.TotalEvolutionReport;
import br.com.scsoftware.estech.domains.basicrecords.enums.StatusAgreement;

public class TotalEvolutionReportConverter {
    public static TotalEvolutionReportResponse toDTO(TotalEvolutionReport report) {
        return TotalEvolutionReportResponse.builder()
                .groupName(report.getGroupName())
                .patientName(report.getPatientName())
                .programName(report.getProgramName())
                .status(StatusAgreement.valueOf(report.getStatus()))
                .postingPercentage(report.getPostingPercentage())
                .balancePercentage(report.getBalancePercentage())
                .evolutionPeriod(report.getEvolutionPeriod())
                .goal(report.getGoal())
                .goalPercentage(report.getGoalPercentage())
                .notesTotal(report.getNotesTotal())
            .build();
    }
}
