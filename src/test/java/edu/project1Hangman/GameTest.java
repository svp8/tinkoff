package edu.project1Hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void prepare() {
        game = new Game("abc", 5);
    }

    @Test
    @DisplayName("Should return RightGuess")
    void playRight() {
        Result result = game.play('a');
        Assertions.assertTrue(result instanceof Result.RightGuess);
        Assertions.assertEquals("a**", result.currentGuess());
    }

    @Test
    @DisplayName("Should return FailedGuess")
    void playWrong() {
        Result result = game.play('t');
        Assertions.assertTrue(result instanceof Result.FailedGuess);
        Assertions.assertEquals("***", result.currentGuess());
        Assertions.assertEquals(1, result.attempts());
    }

    @Test
    @DisplayName("Should return RepeatedGuess after typing right guess multiple times")
    void playRepeatRight() {
        Result result = game.play('a');
        Assertions.assertTrue(result instanceof Result.RightGuess);
        result = game.play('a');
        Assertions.assertTrue(result instanceof Result.RepeatedGuess);
        result = game.play('a');
        Assertions.assertTrue(result instanceof Result.RepeatedGuess);
        Assertions.assertEquals("a**", result.currentGuess());
    }
    @Test
    @DisplayName("Should return RepeatedGuess after typing wrong guess multiple times")
    void playRepeatWrong() {
        Result result = game.play('o');
        Assertions.assertTrue(result instanceof Result.FailedGuess);
        result = game.play('o');
        Assertions.assertTrue(result instanceof Result.RepeatedGuess);
        result = game.play('o');
        Assertions.assertTrue(result instanceof Result.RepeatedGuess);
        Assertions.assertEquals("***", result.currentGuess());
        Assertions.assertEquals(1, result.attempts());
    }

    @Test
    @DisplayName("Should return winning")
    void playWin() {
        game = new Game("a", 5);
        Result result = game.play('a');
        Assertions.assertTrue(result instanceof Result.Win);
        Assertions.assertEquals("a", result.currentGuess());
    }

    @Test
    @DisplayName("Should return lose")
    void playLose() {
        game = new Game("a", 1);
        Result result = game.play('u');
        Assertions.assertTrue(result instanceof Result.Lose);
        Assertions.assertEquals("*", result.currentGuess());
    }

    @Test
    void prepareWord() {
        String word = "abcc";
        HashMap<Character, ArrayList<Integer>> letters = new HashMap<>();
        Game.prepareWord(letters, word);
        Assertions.assertEquals(0, letters.get('a').get(0));
        Assertions.assertEquals(1, letters.get('b').get(0));
        Assertions.assertEquals(Arrays.asList(2, 3), letters.get('c'));
    }
}
