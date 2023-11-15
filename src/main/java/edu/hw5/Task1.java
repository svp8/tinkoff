package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class Task1 {

    public static final int MINUTE = 60;

    private Task1() {

    }

    public static String averageTime(List<String> info) {
        Duration duration = Duration.ZERO;
        for (String time : info) {
            String[] range = time.split(" - ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
            LocalDateTime start = LocalDateTime.parse(range[0], formatter);
            LocalDateTime end = LocalDateTime.parse(range[1], formatter);
            duration = duration.plus(Duration.between(start, end));
        }
        duration = duration.dividedBy(info.size());
        String hours = String.valueOf(duration.toHours());
        String minutes = String.valueOf(duration.toMinutesPart());
        return hours + "ч " + minutes + "м";
    }
}
