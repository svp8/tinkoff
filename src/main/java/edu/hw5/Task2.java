package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private Task2() {

    }

    public static final int THIRTEEN = 13;

    public static List<String> friday13List(int year) {
        List<String> fridays = new ArrayList<>();
        LocalDate localDate = LocalDate.ofYearDay(year, THIRTEEN);
        while (localDate.getYear() != year + 1) {
            if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(localDate.toString());
            }
            localDate = localDate.plusMonths(1);
        }
        return fridays;
    }

    public static String closestFriday13(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (date.getDayOfMonth() != THIRTEEN) {
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return date.toString();
    }
}
