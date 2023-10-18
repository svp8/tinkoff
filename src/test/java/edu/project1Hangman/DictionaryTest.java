package edu.project1Hangman;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void giveWord() {
        DictionaryInterface dictionary = new Dictionary();
        Assertions.assertThat(dictionary.giveWord()).isNotEmpty();
    }
}
