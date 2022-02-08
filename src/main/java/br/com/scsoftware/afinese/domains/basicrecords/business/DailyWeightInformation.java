package br.com.scsoftware.afinese.domains.basicrecords.business;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author samuel-cruz
 */
public interface DailyWeightInformation {
    LocalDate getDate();

    BigDecimal getCurrentWeight();

    BigDecimal getEvolution();
}
