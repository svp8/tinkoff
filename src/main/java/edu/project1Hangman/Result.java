package edu.project1Hangman;

import org.jetbrains.annotations.NotNull;

public interface Result {
    int maxAttempts();

    int attempts();

    String currentGuess();

    @NotNull String message();

    record FailedGuess(int maxAttempts, int attempts, String currentGuess) implements Result {

        @Override
        public @NotNull String message() {
            return String.format("Missed, mistake %d out of %d.", attempts, maxAttempts);
        }
    }

    record Win(int maxAttempts, int attempts, String currentGuess) implements Result {

        @Override
        public @NotNull String message() {
            return "You won!";
        }
    }

    record Lose(int maxAttempts, int attempts, String currentGuess) implements Result {

        @Override
        public @NotNull String message() {
            return "You lost!";
        }
    }

    record RightGuess(int maxAttempts, int attempts, String currentGuess) implements Result {

        @Override
        public @NotNull String message() {
            return "You guessed right!";
        }
    }

    record RepeatedGuess(int maxAttempts, int attempts, String currentGuess) implements Result {

        @Override
        public @NotNull String message() {
            return "Letter have been already guessed";
        }
    }
}
