package edu.project1Hangman;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void giveWord() {
        DictionaryInterface dictionary = new Dictionary(List.of("abc"));
        Assertions.assertThat(dictionary.giveWord()).isNotEmpty();
    }

    @Test
    void testWrongDictionary() {
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Dictionary(List.of("abc", "")));
        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Dictionary(List.of("abc1", "f")));
    }
}
