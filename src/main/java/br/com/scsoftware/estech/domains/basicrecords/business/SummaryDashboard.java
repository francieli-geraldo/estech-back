package br.com.scsoftware.estech.domains.basicrecords.business;

import java.math.BigDecimal;

/**
 * @author samuel-cruz
 */
public interface SummaryDashboard {
    Integer getTotalNewContractsMonth();

    Integer getTotalNewContracts();

    Integer getTotalCompletedContractsMonth();

    Integer getTotalCompletedContracts();

    Integer getTotalReleasesWeek();

    Integer getTotalReleasesMonth();

    BigDecimal getTotalWeightMonth();

    BigDecimal getTotalWeight();
}
