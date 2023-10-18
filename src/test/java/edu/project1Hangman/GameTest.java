package edu.project1Hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    Result defaultState;
    StringBuilder currentGuess = new StringBuilder();
    HashMap<Character, ArrayList<Integer>> letters = new HashMap<>();

    void prepare() {
        game = new Game("abc");
        Console.prepareWord(currentGuess, letters, "abc");
        defaultState = new Result.RightGuess(5, 0, currentGuess, letters, 0);
    }

    @Test
    @DisplayName("Should return RightGuess")
    void playRight() {
        prepare();
        Result result = game.play('a', defaultState);
        Assertions.assertTrue(result instanceof Result.RightGuess);
        Assertions.assertEquals("a**", result.currentGuess().toString());
    }

    @Test
    @DisplayName("Should return FailedGuess")
    void playWrong() {
        prepare();
        Result result = game.play('t', defaultState);
        Assertions.assertTrue(result instanceof Result.FailedGuess);
        Assertions.assertEquals("***", result.currentGuess().toString());
        Assertions.assertEquals(1, result.attempts());
    }

    @Test
    @DisplayName("Should return winning")
    void playWin() {
        game = new Game("a");
        Console.prepareWord(currentGuess, letters, "a");
        defaultState = new Result.RightGuess(5, 0, currentGuess, letters, 0);
        Result result = game.play('a', defaultState);
        Assertions.assertTrue(result instanceof Result.Win);
        Assertions.assertEquals("a", result.currentGuess().toString());
    }

    @Test
    @DisplayName("Should return lose")
    void playLose() {
        game = new Game("a");
        Console.prepareWord(currentGuess, letters, "a");
        defaultState = new Result.RightGuess(1, 0, currentGuess, letters, 0);
        Result result = game.play('u', defaultState);
        Assertions.assertTrue(result instanceof Result.Lose);
        Assertions.assertEquals("*", result.currentGuess().toString());
    }
}
