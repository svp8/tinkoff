package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    public static Stream<Arguments> averageTime() {
        return Stream.of(
            Arguments.of(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"
            ),"3ч 40м"),
            Arguments.of(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 20:30 - 2022-04-01, 23:50"
            ),"3ч 25м"),
            Arguments.of(List.of(
                "2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 20:30 - 2022-04-02, 05:50",
                "2022-04-04, 20:00 - 2022-04-05, 12:00"
            ),"9ч 36м")
        );
    }

    @ParameterizedTest
    @MethodSource
    void averageTime(List<String> timeList,String expected) {
        Assertions.assertEquals(expected,Task1.averageTime(timeList));
    }
}
