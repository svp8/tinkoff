package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public final class Task3 {
    private Task3() {

    }

    public static Optional<LocalDate> parseDate(@NotNull String date) {
        if (date.isBlank()) {
            return Optional.empty();
        }
        List<Parser> parsers = Arrays.asList(
            new DateWithDashParser(),
            new DateWithSlashParser(),
            new DaysAgoDateParser(),
            new RelativeDateParser()
        );
        Optional<Parser> parser = parsers.stream().filter(p -> p.canParse(date)).findFirst();
        if (parser.isPresent()) {
            LocalDate localDate = parser.get().parse(date);
            return Optional.of(localDate);
        } else {
            return Optional.empty();
        }
    }
}
