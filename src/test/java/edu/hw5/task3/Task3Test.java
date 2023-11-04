package edu.hw5.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    public static Stream<Arguments> parseDate() {
        return Stream.of(
            Arguments.of("2020-10-10","2020-10-10"),
            Arguments.of("2020-12-2","2020-12-02"),
            Arguments.of("1/3/1976","1976-03-01"),
            Arguments.of("1/3/20","2020-03-01"),
            Arguments.of("tomorrow",LocalDate.now().plusDays(1).toString()),
            Arguments.of("today",LocalDate.now().toString()),
            Arguments.of("yesterday",LocalDate.now().minusDays(1).toString()),
            Arguments.of("1 day ago",LocalDate.now().minusDays(1).toString()),
            Arguments.of("2234 days ago",LocalDate.now().minusDays(2234).toString())
        );
    }

    @ParameterizedTest
    @MethodSource
    void parseDate(String date,String expected) {
        Optional<LocalDate> localDate=Task3.parseDate(date);
        Assertions.assertEquals(expected,localDate.get().toString());
    }
    @ParameterizedTest
    @ValueSource(strings = {"","1", "  ","1234/12/12","2020-13-13","20-10-12"})
    void parseDateInvalid(String date){
        Optional<LocalDate> localDate=Task3.parseDate(date);
        Assertions.assertEquals(Optional.empty(),localDate);
    }
}
