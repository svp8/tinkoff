package edu.project1Hangman;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final String hiddenWord;

    public Game(String hiddenWord) {
        this.hiddenWord = hiddenWord;
    }

    public Result play(char input, Result result) {
        StringBuilder currentGuess = result.currentGuess();
        HashMap<Character, ArrayList<Integer>> letters = result.letters();
        int maxAttempts = result.maxAttempts();
        int attempts = result.attempts();
        int guessedLetters = result.guessedLetters();
        if (letters.containsKey(input)) {
            for (int i : letters.get(input)) {
                currentGuess.setCharAt(i, input);
                guessedLetters++;
            }
            if (guessedLetters == hiddenWord.length()) {
                return new Result.Win(maxAttempts, attempts, currentGuess, letters, guessedLetters);
            }
        } else {
            attempts++;
            if (attempts == maxAttempts) {
                return new Result.Lose(maxAttempts, attempts, currentGuess, letters, guessedLetters);
            } else {
                return new Result.FailedGuess(maxAttempts, attempts, currentGuess, letters, guessedLetters);
            }
        }
        return new Result.RightGuess(maxAttempts, attempts, currentGuess, letters, guessedLetters);
    }

}
