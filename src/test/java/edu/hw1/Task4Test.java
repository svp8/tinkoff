package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class Task4Test {

    private static Stream<Arguments> provideStrings() {
        return Stream.of(
            Arguments.of("This is a mixed up string.", "hTsii  s aimex dpus rtni.g"),
            Arguments.of("214365", "123456"),
            Arguments.of("abcde", "badce"),
            Arguments.of("a", "a")
        );
    }

    @ParameterizedTest(name = "#{index} - Test with String: {1}")
    @MethodSource("provideStrings")
    void fixString(String expected, String str) {
        Assertions.assertEquals(
            expected,
            Task4.fixString(str)
        );
    }
}
