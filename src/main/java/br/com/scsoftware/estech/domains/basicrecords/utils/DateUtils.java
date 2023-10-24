package br.com.scsoftware.estech.domains.basicrecords.utils;

import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@UtilityClass
public class DateUtils {

    public static String asString(Date date) {
        return Objects.isNull(date) ? null : asString(date.toLocalDate());
    }

    public static String asString(LocalDate date) {
        return Objects.isNull(date) ? null : date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static Date asDate(LocalDate date) {
        return Objects.isNull(date) ? null : Date.valueOf(date);
    }

    public static LocalDate asDate(Date date) {
        return Objects.isNull(date) ? null : date.toLocalDate();
    }

    public static Date asDate(Object date) {
        return Objects.isNull(date) ? null : (Date) date;
    }

    public static LocalDate asDate(String date) {
        return Objects.isNull(date) ? null : LocalDate.parse(date);
    }

    public static LocalDate incrementOneDay(Date date) {
        return Objects.isNull(date) ? null : incrementDay(date, 1L);
    }

    public static LocalDate incrementOneMonth(Date date) {
        return Objects.isNull(date) ? null : incrementMonth(date, 1L);
    }

    public static LocalDate incrementLastDay(LocalDate date) {
        return Objects.isNull(date) ? null : date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
    }

    public static LocalDate incrementDay(Date date, long day) {
        return Objects.isNull(date) ? null : ChronoUnit.DAYS.addTo(asDate(date), day);
    }

    public static LocalDate incrementMonth(Date date, long amount) {
        return Objects.isNull(date) ? null : ChronoUnit.MONTHS.addTo(asDate(date), amount);
    }

    public static Integer numberOfMonthBetween(LocalDate start, LocalDate end) {
        return Math.toIntExact(ChronoUnit.MONTHS.between(start, end));
    }

    public static Integer numberOfDayBetween(LocalDate start, LocalDate end) {
        return Math.toIntExact(ChronoUnit.DAYS.between(start, end));
    }
}
