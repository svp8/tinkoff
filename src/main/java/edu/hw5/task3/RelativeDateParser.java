package edu.hw5.task3;

import java.time.LocalDate;
@SuppressWarnings("FallThrough")
public class RelativeDateParser implements Parser {

    public static final String TOMORROW = "tomorrow";
    public static final String YESTERDAY = "yesterday";
    public static final String TODAY = "today";

    @Override
    public boolean canParse(String date) {
        return date.equals(TOMORROW) || date.equals(YESTERDAY) || date.equals(TODAY);
    }

    @Override
    public LocalDate parse(String date) {
        LocalDate today = LocalDate.now();
        return switch (date) {
            case TOMORROW:
                yield today.plusDays(1);
            case TODAY:
                yield today;
            case YESTERDAY:
                yield today.minusDays(1);
            default:
                yield null;
        };
    }
}
