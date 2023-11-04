package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @ParameterizedTest
    @ValueSource(strings = {"123!", "~ ! @ # $ % ^ & * |", "@fdsd", "re45$fetgth"})
    void containsSpecialSymbols(String password) {
        Assertions.assertTrue(Task4.containsSpecialSymbols(password));
    }
    @ParameterizedTest
    @ValueSource(strings = {"123", "", " ", "wefewfrw"})
    void notContainsSpecialSymbols(String password) {
        Assertions.assertFalse(Task4.containsSpecialSymbols(password));
    }
}
