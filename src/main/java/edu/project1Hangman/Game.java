package edu.project1Hangman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private final String hiddenWord;
    private final int MAX_ATTEMPTS = 5;

    public StringBuilder getCurrentGuess() {
        return currentGuess;
    }

    private final StringBuilder currentGuess;
    private final HashMap<Character, ArrayList<Integer>> letters;
    private int guessedLetters = 0;

    public Game(String hiddenWord) {
        this.hiddenWord = hiddenWord;
        currentGuess = new StringBuilder(hiddenWord.length());
        letters = new HashMap<>();
        prepareWord();
    }

    private int wrongGuesses = 0;

    public Result play(char input) {
        //split
        if (letters.containsKey(input)) {
            for (int i : letters.get(input)) {
                currentGuess.setCharAt(i, input);
                guessedLetters++;
            }
            if (guessedLetters == hiddenWord.length()) {
                return new Result.Win();
            }
        } else {
            wrongGuesses++;
            if (wrongGuesses == MAX_ATTEMPTS) {
                return new Result.Lose();
            } else {
                return new Result.FailedGuess(MAX_ATTEMPTS, wrongGuesses);
            }
        }
        return new Result.RightGuess();
    }

    public void prepareWord() {
        for (int i = 0; i < hiddenWord.length(); i++) {
            char curLetter = hiddenWord.charAt(i);
            currentGuess.append('*');
            if (letters.containsKey(curLetter)) {
                letters.get(curLetter).add(i);
            } else {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(i);
                letters.put(curLetter, arr);
            }
        }
    }

}
