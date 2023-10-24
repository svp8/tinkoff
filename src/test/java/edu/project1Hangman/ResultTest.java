package edu.project1Hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultTest {
    @Test
    void messageRight() {
        Result result = new Result.RightGuess(0, 0, null);
        Assertions.assertEquals("You guessed right!", result.message());
    }

    @Test
    void messageFailed() {
        Result result = new Result.FailedGuess(5, 1, null);
        Assertions.assertEquals("Missed, mistake 1 out of 5.", result.message());
    }

    @Test
    void messageWin() {
        Result result = new Result.Win(0, 0, null);
        Assertions.assertEquals("You won!", result.message());
    }

    @Test
    void messageLose() {
        Result result = new Result.Lose(0, 0, null);
        Assertions.assertEquals("You lost!", result.message());
    }
}
