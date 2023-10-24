package edu.project1Hangman;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    @DisplayName("Test word with 0 length")
    void wordIsEmpty() {
        Console console = new Console();
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> console.start(new Dictionary(Arrays.asList("", "fsrf")))
        );
    }

    @Test
    @DisplayName("Test word with numbers")
    void wordHasNumbers() {
        Console console = new Console();
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> console.start(new Dictionary(Arrays.asList("123", "fsrf")))
        );
    }

    @Test
    @DisplayName("ValidateInputString should prohibit string with numbers")
    void validateInputStringNumbers() {
        Console console = new Console();
        Assertions.assertFalse(console.validateInputString("1"));
    }

    @Test
    @DisplayName("ValidateInputString should prohibit string with invalid length")
    void validateInputStringMoreThanOne() {
        Console console = new Console();
        Assertions.assertFalse(console.validateInputString("abc"));
    }

    @Test
    @DisplayName("ValidateInputString should approve valid string")
    void validateInputString() {
        Console console = new Console();
        Assertions.assertTrue(console.validateInputString("c"));
    }
}
