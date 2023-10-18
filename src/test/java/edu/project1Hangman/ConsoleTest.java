package edu.project1Hangman;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleTest {

    @Test
    @DisplayName("Test word with 0 length")
    void wordIsEmpty() {
        Console console = new Console();
        Assertions.assertFalse(console.start(new DictionaryInterface() {
            @Override
            public @NotNull String giveWord() {
                return "";
            }
        }));
    }

    @Test
    @DisplayName("Test word with numbers")
    void wordHasNumbers() {
        Console console = new Console();
        Assertions.assertFalse(console.start(new DictionaryInterface() {
            @Override
            public @NotNull String giveWord() {
                return "abc23ere";
            }
        }));
    }

    @Test
    void prepareWord() {
        String word = "abc";
        Console console = new Console();
        StringBuilder currentGuess = new StringBuilder();
        HashMap<Character, ArrayList<Integer>> letters = new HashMap<>();
        console.prepareWord(currentGuess, letters, word);
        Assertions.assertEquals(3, currentGuess.length());
        Assertions.assertEquals("***", currentGuess.toString());
    }

    @Test
    @DisplayName("Test if letters in hashmap after prepareWord() are valid")
    void checkLetters() {
        String word = "abc";
        Console console = new Console();
        StringBuilder currentGuess = new StringBuilder();
        HashMap<Character, ArrayList<Integer>> letters = new HashMap<>();
        console.prepareWord(currentGuess, letters, word);
        Assertions.assertEquals(0, letters.get('a').get(0));
        Assertions.assertEquals(1, letters.get('b').get(0));
        Assertions.assertEquals(2, letters.get('c').get(0));
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
