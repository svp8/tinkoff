package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @ParameterizedTest
    @CsvSource({"1,1", "6,720", "2,2", "0,1"})
    void factorial(int number, int expected) {
        int result = Task2.factorial(number);
        assertEquals(expected, result);
    }

    @Test
    void wrongArgument() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task2.factorial(-1));
    }
}
