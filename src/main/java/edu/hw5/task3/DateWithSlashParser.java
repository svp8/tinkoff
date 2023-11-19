package edu.hw5.task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateWithSlashParser implements Parser {
    private final DateTimeFormatter dateTimeFormatter;

    public DateWithSlashParser() {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern("[d/M/yyyy]" + "[dd/MM/yyyy]" + "[dd/M/yyyy]" + "[d/MM/yyyy]"
                + "[d/M/yy]" + "[dd/MM/yy]" + "[dd/M/yy]" + "[d/MM/yy]"
            ));
        dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();
    }

    @Override
    public boolean canParse(String date) {
        try {
            this.dateTimeFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public LocalDate parse(String date) {
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
