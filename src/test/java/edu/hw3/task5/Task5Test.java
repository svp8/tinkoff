package edu.hw3.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class Task5Test {
    private static Stream<Arguments> parseContacts() {
        return Stream.of(
            Arguments.of(Arrays.asList("Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"), Arrays.asList(
                "John Locke",
                "Thomas Aquinas",
                "David Hume",
                "Rene Descartes"
            ), "ASC"),
            Arguments.of(Arrays.asList("Carl Gauss", "Leonhard Euler", "Paul Erdos"), Arrays.asList(
                "Paul Erdos", "Leonhard Euler", "Carl Gauss"
            ), "DESC"),
            Arguments.of(new ArrayList<>(), new ArrayList<>(), "DESC"),
            Arguments.of(new ArrayList<>(), null, "DESC")

        );
    }

    @ParameterizedTest
    @MethodSource
    void parseContacts(List<String> expected, List<String> actual, String method) {
        Assertions.assertEquals(expected, Task5.parseContacts(actual, method));
    }

    @Test
    @DisplayName("Test should throw NullPointerException")
    void checkNullException() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add(null);
        arr.add("wq");
        Assertions.assertThrows(NullPointerException.class, () -> Task5.parseContacts(arr, "DESC"));
    }

    @Test
    @DisplayName("Test should throw IllegalArgumentException")
    void checkIllegalException() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("3r");
        arr.add("wq");
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task5.parseContacts(arr, "DESCg"));
    }
}
