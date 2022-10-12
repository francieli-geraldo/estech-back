package br.com.scsoftware.afinese.infrastructure.config;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class Timezones {
    private Timezones() {
    }

    private static final Map<String, String> mapTimeZones = new HashMap<>();

    public static void initialize() {
        mapTimeZones.clear();

        mapTimeZones.put("AC", "America/Rio_branco");
        mapTimeZones.put("AL", "America/Maceio");
        mapTimeZones.put("AP", "America/Belem");
        mapTimeZones.put("AM", "America/Manaus");
        mapTimeZones.put("BA", "America/Bahia");
        mapTimeZones.put("CE", "America/Fortaleza");
        mapTimeZones.put("DF", "America/Sao_Paulo");
        mapTimeZones.put("ES", "America/Sao_Paulo");
        mapTimeZones.put("GO", "America/Sao_Paulo");
        mapTimeZones.put("MA", "America/Fortaleza");
        mapTimeZones.put("MT", "America/Cuiaba");
        mapTimeZones.put("MS", "America/Campo_Grande");
        mapTimeZones.put("MG", "America/Sao_Paulo");
        mapTimeZones.put("PR", "America/Sao_Paulo");
        mapTimeZones.put("PB", "America/Fortaleza");
        mapTimeZones.put("PA", "America/Belem");
        mapTimeZones.put("PE", "America/Recife");
        mapTimeZones.put("PI", "America/Fortaleza");
        mapTimeZones.put("RJ", "America/Sao_Paulo");
        mapTimeZones.put("RN", "America/Fortaleza");
        mapTimeZones.put("RS", "America/Sao_Paulo");
        mapTimeZones.put("RO", "America/Porto_Velho");
        mapTimeZones.put("RR", "America/Boa_Vista");
        mapTimeZones.put("SC", "America/Sao_Paulo");
        mapTimeZones.put("SE", "America/Maceio");
        mapTimeZones.put("SP", "America/Sao_Paulo");
        mapTimeZones.put("TO", "America/Araguaia");
    }

    public static TimeZone getTimeZoneFromUf(String uf) {
        return TimeZone.getTimeZone(mapTimeZones.get(uf.toUpperCase()));
    }

    public static void setTimeZoneFromUf(String uf) {
        TimeZone.setDefault(getTimeZoneFromUf(uf));
    }
}
