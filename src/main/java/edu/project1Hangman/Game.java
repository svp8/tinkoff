package edu.project1Hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private final String hiddenWord;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;
    private int rightAnswers;
    private final Set<Character> guessedLetters;
    HashMap<Character, ArrayList<Integer>> letters = new HashMap<>();

    public String getCurrentGuess() {
        return String.valueOf(userAnswer);
    }

    public Game(String hiddenWord, int maxAttempts) {
        this.hiddenWord = hiddenWord;
        this.maxAttempts = maxAttempts;
        guessedLetters = new HashSet<>();
        userAnswer = new char[hiddenWord.length()];
        rightAnswers = 0;
        Arrays.fill(userAnswer, '*');
        prepareWord(letters, hiddenWord);
    }

    public Result play(char input) {
        if (letters.containsKey(input)) {
            if (!guessedLetters.contains(input)) {
                for (int i : letters.get(input)) {
                    userAnswer[i] = input;
                    rightAnswers++;
                }
                guessedLetters.add(input);
            } else {
                return new Result.RepeatedGuess(maxAttempts, attempts, String.valueOf(userAnswer));
            }
            if (rightAnswers == hiddenWord.length()) {
                return new Result.Win(maxAttempts, attempts, String.valueOf(userAnswer));
            }
        } else {
            return processFailedGuess(input);
        }
        return new Result.RightGuess(maxAttempts, attempts, String.valueOf(userAnswer));
    }

    public Result processFailedGuess(char input) {
        if (!guessedLetters.add(input)) {
            return new Result.RepeatedGuess(maxAttempts, attempts, String.valueOf(userAnswer));
        }
        attempts++;
        if (attempts == maxAttempts) {
            return new Result.Lose(maxAttempts, attempts, String.valueOf(userAnswer));
        } else {
            return new Result.FailedGuess(maxAttempts, attempts, String.valueOf(userAnswer));
        }
    }

    public static void prepareWord(
        HashMap<Character, ArrayList<Integer>> letters,
        String hiddenWord
    ) {
        for (int i = 0; i < hiddenWord.length(); i++) {
            char curLetter = hiddenWord.charAt(i);
            letters
                .computeIfAbsent(curLetter, ignored -> new ArrayList<>())
                .add(i);
        }
    }

}
