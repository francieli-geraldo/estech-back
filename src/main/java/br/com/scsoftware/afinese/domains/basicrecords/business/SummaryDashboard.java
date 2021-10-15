package br.com.scsoftware.afinese.domains.basicrecords.business;

import java.math.BigDecimal;
import java.sql.Date;

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
