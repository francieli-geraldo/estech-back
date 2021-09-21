package br.com.scsoftware.afinese.domains.basicrecords.business;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DailyWeightInformationBO {
    private BigDecimal evolution;
    private BigDecimal previousWeight;
    private BigDecimal accumulatedEvolution;
}
