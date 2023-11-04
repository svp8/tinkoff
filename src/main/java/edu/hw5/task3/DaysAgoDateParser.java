package edu.hw5.task3;

import java.time.LocalDate;

public class DaysAgoDateParser implements Parser {
    @Override
    public boolean canParse(String date) {
        return date.matches("^\\d+ days? ago$");
    }

    @Override
    public LocalDate parse(String date) {
        int day = Integer.parseInt(date.split(" ")[0]);
        LocalDate today = LocalDate.now();
        return today.minusDays(day);
    }
}
