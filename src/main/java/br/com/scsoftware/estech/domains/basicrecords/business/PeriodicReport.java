package br.com.scsoftware.estech.domains.basicrecords.business;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author samuel-cruz
 */
public interface PeriodicReport {
    String getGroupName();

    String getPatientName();

    String getProgramName();

    String getStatus();

    Date getStartDate();

    BigDecimal getStartingWeight();

    BigDecimal getCurrentWeight();

    BigDecimal getAccumulatedEvolution();

    Date getDateConclusion();

    Integer getBreakfastTotal();

    Integer getMorningSnackTotal();

    Integer getLunchTotal();

    Integer getAfternoonSnackTotal();

    Integer getDinnerTotal();

    Integer getHiitTotal();
    Integer getMentorshipTotal();

    BigDecimal getPostingPercentage();

    Integer getBalanceTotal();

    BigDecimal getBalancePercentage();

    BigDecimal getEvolutionPeriod();

    BigDecimal getGoal();

    Integer getNotesTotal();
}
