package br.com.scsoftware.afinese.domains.basicrecords.converter;

import br.com.scsoftware.afinese.domains.basicrecords.api.v1.web.response.PeriodicReportResponse;
import br.com.scsoftware.afinese.domains.basicrecords.business.PeriodicReport;
import br.com.scsoftware.afinese.domains.basicrecords.enums.StatusAgreement;

public class PeriodicReportConverter {
    public static PeriodicReportResponse toDTO(PeriodicReport report) {
        return PeriodicReportResponse.builder()
                .groupName(report.getGroupName())
                .patientName(report.getPatientName())
                .programName(report.getProgramName())
                .status(StatusAgreement.valueOf(report.getStatus()))
                .startDate(report.getStartDate().toLocalDate())
                .dateConclusion(report.getDateConclusion() == null ? null : report.getDateConclusion().toLocalDate())
                .breakfastTotal(report.getBreakfastTotal())
                .morningSnackTotal(report.getMorningSnackTotal())
                .lunchTotal(report.getLunchTotal())
                .afternoonSnackTotal(report.getAfternoonSnackTotal())
                .dinnerTotal(report.getDinnerTotal())
                .hiitTotal(report.getHiitTotal())
                .postingPercentage(report.getPostingPercentage())
                .balanceTotal(report.getBalanceTotal())
                .balancePercentage(report.getBalancePercentage())
                .evolutionPeriod(report.getEvolutionPeriod())
                .goal(report.getGoal())
                .notesTotal(report.getNotesTotal())
            .build();
    }
}
