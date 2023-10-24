package br.com.scsoftware.estech.domains.basicrecords.business;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author samuel-cruz
 */
public interface DailyWeightInformation {
    Long getAgreementId();

    LocalDate getDate();

    BigDecimal getCurrentWeight();

    BigDecimal getEvolution();
}
