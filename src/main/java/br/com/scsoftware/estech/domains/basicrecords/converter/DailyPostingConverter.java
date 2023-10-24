package br.com.scsoftware.estech.domains.basicrecords.converter;

import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.BalanceDailyPosting;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.request.DailyPostingRequest;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.AgreementProgramMonitoringResponse;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.DailyPostingProgramMonitoringResponse;
import br.com.scsoftware.estech.domains.basicrecords.api.v1.web.response.DailyPostingResponse;
import br.com.scsoftware.estech.domains.basicrecords.business.DailyPostingBO;
import br.com.scsoftware.estech.domains.basicrecords.business.DailyWeightInformation;
import br.com.scsoftware.estech.domains.basicrecords.business.PeriodicReport;
import br.com.scsoftware.estech.domains.basicrecords.entity.DailyPosting;
import br.com.scsoftware.estech.domains.basicrecords.enums.StatusAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class DailyPostingConverter {
    public static DailyPostingResponse toDTO(DailyPosting dailyPosting) {
        return DailyPostingResponse.builder()
                .id(dailyPosting.getId())
                .date(dailyPosting.getDate())
                .patientId(dailyPosting.getAgreement().getPatient().getId())
                .groupId(dailyPosting.getAgreement().getGroup().getId())
                .balance(BalanceDailyPosting.builder()
                        .informed(dailyPosting.isBalance())
                        .previousWeight(dailyPosting.getPreviousWeight())
                        .currentWeight(dailyPosting.getCurrentWeight())
                        .evolution(dailyPosting.getEvolution())
                        .accumulatedEvolution(dailyPosting.getAccumulatedEvolution())
                        .build()
                )
                .breakfast(dailyPosting.isBreakfast())
                .morningSnack(dailyPosting.isMorningSnack())
                .lunch(dailyPosting.isLunch())
                .afternoonSnack(dailyPosting.isAfternoonSnack())
                .dinner(dailyPosting.isDinner())
                .hiit(dailyPosting.isHiit())
                .notes(dailyPosting.getNotes())
                .build();
    }

    public static DailyPostingResponse toDTO(Map<String, Object> dailyPosting) {

        return DailyPostingResponse.builder()
                .id(toLong(dailyPosting.get("id")))
                .date(toLocalDate(dailyPosting.get("releaseDate")))
                .agreementId(toLong(dailyPosting.get("agreementId")))
                .agreementStartingWeight(toBigDecimal(dailyPosting.get("startingWeight")))
                .patientId(toLong(dailyPosting.get("patientId")))
                .patientName(toString(dailyPosting.get("patientName")))
                .groupId(toLong(dailyPosting.get("groupId")))
                .groupName(toString(dailyPosting.get("groupId")))
                .balance(BalanceDailyPosting.builder()
                        .informed(toBoolean(dailyPosting.get("balance")))
                        .previousWeight(toBigDecimal(dailyPosting.get("previousWeight")))
                        .currentWeight(toBigDecimal(dailyPosting.get("currentWeight")))
                        .evolution(toBigDecimal(dailyPosting.get("evolution")))
                        .accumulatedEvolution(toBigDecimal(dailyPosting.get("accumulatedEvolution")))
                        .build()
                )
                .breakfast(toBoolean(dailyPosting.get("breakfast")))
                .morningSnack(toBoolean(dailyPosting.get("morningSnack")))
                .lunch(toBoolean(dailyPosting.get("lunch")))
                .afternoonSnack(toBoolean(dailyPosting.get("afternoonSnack")))
                .dinner(toBoolean(dailyPosting.get("dinner")))
                .hiit(toBoolean(dailyPosting.get("hiit")))
                .notes(toString(dailyPosting.get("notes")))
                .build();
    }

    private static Long toLong(Object value) {
        if (value == null)
            return null;

        return Long.valueOf(value.toString());
    }

    private static BigDecimal toBigDecimal(Object value) {
        if (value == null)
            return null;

        return (BigDecimal) value;
    }

    private static LocalDate toLocalDate(Object value) {
        if (value == null)
            return null;

        return LocalDate.parse(value.toString());
    }

    private static boolean toBoolean(Object value) {
        if (value == null)
            return false;

        return "1".equals(value.toString());
    }

    private static String toString(Object value) {
        if (value == null)
            return null;

        return value.toString();
    }

    public static DailyPostingBO toBO(DailyPostingResponse dailyPosting) {
        return DailyPostingBO.builder()
                .id(dailyPosting.getId())
                .date(dailyPosting.getDate())
                .balance(dailyPosting.getBalance().getInformed())
                .previousWeight(dailyPosting.getBalance().getPreviousWeight())
                .currentWeight(dailyPosting.getBalance().getCurrentWeight())
                .evolution(dailyPosting.getBalance().getEvolution())
                .accumulatedEvolution(dailyPosting.getBalance().getAccumulatedEvolution())
                .breakfast(dailyPosting.getBreakfast())
                .morningSnack(dailyPosting.getMorningSnack())
                .lunch(dailyPosting.getLunch())
                .afternoonSnack(dailyPosting.getAfternoonSnack())
                .dinner(dailyPosting.getDinner())
                .hiit(dailyPosting.getHiit())
                .notes(dailyPosting.getNotes())
                .build();
    }

    public static DailyPostingBO toBO(DailyPostingRequest dailyPosting) {
        return DailyPostingBO.builder()
                .date(dailyPosting.getDate())
                .balance(dailyPosting.getBalance().getInformed())
                .previousWeight(dailyPosting.getBalance().getPreviousWeight())
                .currentWeight(dailyPosting.getBalance().getCurrentWeight())
                .evolution(dailyPosting.getBalance().getEvolution())
                .accumulatedEvolution(dailyPosting.getBalance().getAccumulatedEvolution())
                .breakfast(dailyPosting.getBreakfast())
                .morningSnack(dailyPosting.getMorningSnack())
                .lunch(dailyPosting.getLunch())
                .afternoonSnack(dailyPosting.getAfternoonSnack())
                .dinner(dailyPosting.getDinner())
                .hiit(dailyPosting.getHiit())
                .notes(dailyPosting.getNotes())
                .build();
    }

    public static DailyPostingBO toBO(DailyPosting dailyPosting) {
        return DailyPostingBO.builder()
                .id(dailyPosting.getId())
                .date(dailyPosting.getDate())
                .balance(dailyPosting.isBalance())
                .previousWeight(dailyPosting.getPreviousWeight())
                .currentWeight(dailyPosting.getCurrentWeight())
                .evolution(dailyPosting.getEvolution())
                .accumulatedEvolution(dailyPosting.getAccumulatedEvolution())
                .breakfast(dailyPosting.isBreakfast())
                .morningSnack(dailyPosting.isMorningSnack())
                .lunch(dailyPosting.isLunch())
                .afternoonSnack(dailyPosting.isAfternoonSnack())
                .dinner(dailyPosting.isDinner())
                .hiit(dailyPosting.isHiit())
                .notes(dailyPosting.getNotes())
                .build();
    }

    public static DailyPosting fromBO(DailyPostingBO dailyPosting, DailyPosting dailyPostingEnt) {
        dailyPostingEnt.setDate(dailyPosting.getDate());
        dailyPostingEnt.setBalance(dailyPosting.isBalance());
        dailyPostingEnt.setPreviousWeight(dailyPosting.getPreviousWeight());
        dailyPostingEnt.setCurrentWeight(dailyPosting.getCurrentWeight());
        dailyPostingEnt.setBreakfast(dailyPosting.isBreakfast());
        dailyPostingEnt.setMorningSnack(dailyPosting.isMorningSnack());
        dailyPostingEnt.setLunch(dailyPosting.isLunch());
        dailyPostingEnt.setAfternoonSnack(dailyPosting.isAfternoonSnack());
        dailyPostingEnt.setDinner(dailyPosting.isDinner());
        dailyPostingEnt.setHiit(dailyPosting.isHiit());
        dailyPostingEnt.setNotes(dailyPosting.getNotes());

        return dailyPostingEnt;
    }

    public static DailyPostingProgramMonitoringResponse toDailyPostingProgramMonitoringResponse(DailyWeightInformation dailyWeightInformation) {
        return DailyPostingProgramMonitoringResponse.builder()
                .date(dailyWeightInformation.getDate())
                .currentWeight(dailyWeightInformation.getCurrentWeight())
                .build();
    }

    public static AgreementProgramMonitoringResponse toAgreementProgramMonitoringResponse(PeriodicReport periodicReport) {
        return AgreementProgramMonitoringResponse.builder()
                .groupName(periodicReport.getGroupName())
                .patientName(periodicReport.getPatientName())
                .programName(periodicReport.getProgramName())
                .status(StatusAgreement.valueOf(periodicReport.getStatus()))
                .startDate(periodicReport.getStartDate().toLocalDate())
                .startingWeight(periodicReport.getStartingWeight())
                .accumulatedEvolution(periodicReport.getAccumulatedEvolution())
                .currentWeight(periodicReport.getCurrentWeight())
                .dateConclusion(periodicReport.getDateConclusion() == null ? null : periodicReport.getDateConclusion().toLocalDate())
                .breakfastTotal(periodicReport.getBreakfastTotal())
                .morningSnackTotal(periodicReport.getMorningSnackTotal())
                .lunchTotal(periodicReport.getLunchTotal())
                .afternoonSnackTotal(periodicReport.getAfternoonSnackTotal())
                .dinnerTotal(periodicReport.getDinnerTotal())
                .hiitTotal(periodicReport.getHiitTotal())
                .postingPercentage(periodicReport.getPostingPercentage())
                .balanceTotal(periodicReport.getBalanceTotal())
                .balancePercentage(periodicReport.getBalancePercentage())
                .evolutionPeriod(periodicReport.getEvolutionPeriod())
                .goal(periodicReport.getGoal())
                .build();
    }
}
