package br.com.scsoftware.afinese.domains.basicrecords.business;

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

    Date getDateConclusion();

    Integer getBreakfastTotal();

    Integer getMorningSnackTotal();

    Integer getLunchTotal();

    Integer getAfternoonSnackTotal();

    Integer getDinnerTotal();

    Integer getHiitTotal();

    BigDecimal getPostingPercentage();

    Integer getBalanceTotal();

    BigDecimal getBalancePercentage();

    BigDecimal getEvolutionPeriod();

    BigDecimal getGoal();

    Integer getNotesTotal();
}
