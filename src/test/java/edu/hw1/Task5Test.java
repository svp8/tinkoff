package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


class Task5Test {

    @ParameterizedTest(name = "#{index} - Test with Number: {0}")
    @ValueSource(ints = {11211230, 13001120, 23336014, 11})
    void isPalindromeDescendant(int number) {
        Assertions.assertTrue(Task5.isPalindromeDescendant(number));
    }

    @Test
    @DisplayName("Test with a non-palindrome")
    void testNotPalindrome() {
        Assertions.assertFalse(Task5.isPalindromeDescendant(3456));
    }
}
