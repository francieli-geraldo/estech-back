package br.com.scsoftware.afinese.domains.basicrecords.business;

/**
 * @author samuel-cruz
 */
public interface DailyPostingPendingDashboard {
    Integer getPatientId();

    String getPatientName();

    Integer getGroupId();

    String getGroupName();

    Long getAgreementId();
}
