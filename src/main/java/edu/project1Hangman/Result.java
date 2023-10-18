package edu.project1Hangman;

public interface Result {
    String getMessage();
    record FailedGuess(int maxAttempts,int wrongGuesses) implements Result {

        @Override
        public String getMessage() {
            return String.format("Missed, mistake %d out of %d.", wrongGuesses, maxAttempts);
        }
    }
    record Win() implements Result {

        @Override
        public String getMessage() {
            return "You won!";
        }
    }
    record Lose() implements Result {

        @Override
        public String getMessage() {
            return "You lost!";
        }
    }
    record RightGuess() implements Result {

        @Override
        public String getMessage() {
            return "You guessed right!";
        }
    }
}
