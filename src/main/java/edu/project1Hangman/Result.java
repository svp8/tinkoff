package edu.project1Hangman;

import java.util.ArrayList;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public interface Result {
    int maxAttempts();

    int attempts();

    StringBuilder currentGuess();

    HashMap<Character, ArrayList<Integer>> letters();

    int guessedLetters();

    @NotNull String message();

    record FailedGuess(int maxAttempts, int attempts, StringBuilder currentGuess,
                       HashMap<Character, ArrayList<Integer>> letters, int guessedLetters) implements Result {

        @Override
        public @NotNull String message() {
            return String.format("Missed, mistake %d out of %d.", attempts, maxAttempts);
        }
    }

    record Win(int maxAttempts, int attempts, StringBuilder currentGuess,
               HashMap<Character, ArrayList<Integer>> letters, int guessedLetters) implements Result {

        @Override
        public @NotNull String message() {
            return "You won!";
        }
    }

    record Lose(int maxAttempts, int attempts, StringBuilder currentGuess,
                HashMap<Character, ArrayList<Integer>> letters, int guessedLetters) implements Result {

        @Override
        public @NotNull String message() {
            return "You lost!";
        }
    }

    record RightGuess(int maxAttempts, int attempts, StringBuilder currentGuess,
                      HashMap<Character, ArrayList<Integer>> letters, int guessedLetters) implements Result {

        @Override
        public @NotNull String message() {
            return "You guessed right!";
        }
    }
}
