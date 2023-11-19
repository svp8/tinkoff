package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    public static Stream<Arguments> friday13List() {
        return Stream.of(
            Arguments.of(1925,
                List.of("1925-02-13", "1925-03-13", "1925-11-13")),
            Arguments.of(2024,
                List.of("2024-09-13", "2024-12-13"))
        );
    }

    public static Stream<Arguments> closestFriday13() {
        return Stream.of(
            Arguments.of("1925-02-14","1925-03-13"),
            Arguments.of("2024-09-13", "2024-12-13")
        );
    }

    @ParameterizedTest
    @MethodSource
    void friday13List(int year, List<String> fridays) {
        Assertions.assertEquals(fridays,Task2.friday13List(year));
    }

    @ParameterizedTest
    @MethodSource
    void closestFriday13(String date, String expected) {
        Assertions.assertEquals(expected,Task2.closestFriday13(date));
    }

}
