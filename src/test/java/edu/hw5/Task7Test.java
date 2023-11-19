package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @ParameterizedTest
    @ValueSource(strings = {"100", "100110"})
    void symbolsWith3length(String input) {
        Assertions.assertTrue(Task7.symbolsWith3length(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "101", "121", "101110"})
    void notSymbolsWith3length(String input) {
        Assertions.assertFalse(Task7.symbolsWith3length(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1110", "111111", "121"})
    void notBetween1and3(String input) {
        Assertions.assertFalse(Task7.between1and3(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"100", "11", "1"})
    void between1and3(String input) {
        Assertions.assertTrue(Task7.between1and3(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"000", "1", "0", "1111110101001"})
    void sameStartEnd(String input) {
        Assertions.assertTrue(Task7.sameStartEnd(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0001", "10", "01", "11111101010010"})
    void notSameStartEnd(String input) {
        Assertions.assertFalse(Task7.sameStartEnd(input));
    }
}
