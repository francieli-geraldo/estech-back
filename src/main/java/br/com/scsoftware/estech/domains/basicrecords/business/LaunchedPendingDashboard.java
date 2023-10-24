package br.com.scsoftware.estech.domains.basicrecords.business;

/**
 * @author samuel-cruz
 */
public interface LaunchedPendingDashboard {
    Integer getGroupId();

    String getGroupName();

    Integer getTotalLaunched();

    Integer getTotalPending();
}
