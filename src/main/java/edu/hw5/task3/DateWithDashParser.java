package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateWithDashParser implements Parser {
    private final DateTimeFormatter dateTimeFormatter;

    public DateWithDashParser() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[yyyy-MM-dd]" + "[yyyy-MM-d]" + "[yyyy-M-dd]" + "[yyyy-M-d]"));
        dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
    }

    @Override
    public boolean canParse(String date) {
        try {
            this.dateTimeFormatter.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public LocalDate parse(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
