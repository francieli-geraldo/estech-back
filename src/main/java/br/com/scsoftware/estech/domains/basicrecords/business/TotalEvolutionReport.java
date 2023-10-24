package br.com.scsoftware.estech.domains.basicrecords.business;

import java.math.BigDecimal;

/**
 * @author samuel-cruz
 */
public interface TotalEvolutionReport {
    String getGroupName();

    String getPatientName();

    String getProgramName();

    String getStatus();

    BigDecimal getPostingPercentage();

    BigDecimal getBalancePercentage();

    BigDecimal getEvolutionPeriod();

    BigDecimal getGoal();

    BigDecimal getGoalPercentage();

    Integer getNotesTotal();
}
