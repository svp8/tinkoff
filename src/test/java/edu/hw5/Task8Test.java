package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {

    @ParameterizedTest
    @ValueSource(strings = {"10101","0","111"})
    void lengthIsNotPrime(String input) {
        Assertions.assertTrue(Task8.lengthIsNotPrime(input));
    }
    @ParameterizedTest
    @ValueSource(strings = {"1010","01","1111",""})
    void lengthIsPrime(String input) {
        Assertions.assertFalse(Task8.lengthIsNotPrime(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1010","0","11","010"})
    void startsWith0andNotPrimeOr1AndPrime(String input) {
        Assertions.assertTrue(Task8.startsWith0andNotPrimeOr1AndPrime(input));
    }
    @ParameterizedTest
    @ValueSource(strings = {"10101","01","110",""})
    void notStartsWith0andNotPrimeOr1AndPrime(String input) {
        Assertions.assertFalse(Task8.startsWith0andNotPrimeOr1AndPrime(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"101010","0100","11000","000"})
    void zeroesMultipleOf3(String input) {
        Assertions.assertTrue(Task8.zeroesMultipleOf3(input));
    }
    @ParameterizedTest
    @ValueSource(strings = {"1010100","00100","1100",""})
    void notZeroesMultipleOf3(String input) {
        Assertions.assertFalse(Task8.zeroesMultipleOf3(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1010100","0010011","11100","1",""})
    void not11or111(String input) {
        Assertions.assertTrue(Task8.not11or111(input));
    }
    @ParameterizedTest
    @ValueSource(strings = {"11","111"})
    void is11or111(String input) {
        Assertions.assertFalse(Task8.not11or111(input));
    }
}
