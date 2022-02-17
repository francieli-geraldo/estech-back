package br.com.scsoftware.afinese.domains.basicrecords.business;

/**
 * @author samuel-cruz
 */
public interface DailyPostingPendingDashboard {
    Integer getPatientId();

    String getPatientName();

    String getPatientPhone();

    Integer getProgramId();

    String getProgramName();

    Long getAgreementId();
}
