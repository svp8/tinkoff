package edu.project1Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Console {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;

   /* public static void main(String[] args) {
        Console console = new Console();
        console.start(new Dictionary());
    }*/

    public boolean start(DictionaryInterface dictionary) {
        String input;
        Result result;
        String word = dictionary.giveWord();
        if (!validateWord(word)) {
            return false;
        }
        StringBuilder currentGuess = new StringBuilder();
        HashMap<Character, ArrayList<Integer>> letters = new HashMap<>();
        prepareWord(currentGuess, letters, word);
        Game game = new Game(word);
        result = new Result.RightGuess(MAX_ATTEMPTS, 0, currentGuess, letters, 0);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            LOGGER.info("The word: " + result.currentGuess().toString());
            LOGGER.info("Guess a letter:");
            try {
                input = bufferedReader.readLine();
                if (input.equals("!")) {
                    LOGGER.info("Game stopped");
                    break;
                }
                if (validateInputString(input)) {
                    result = game.play(input.toLowerCase().charAt(0), result);
                    LOGGER.info(result.message());
                    if (result instanceof Result.Lose || result instanceof Result.Win) {
                        break;
                    }
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }

        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    public static void prepareWord(
        StringBuilder currentGuess,
        HashMap<Character, ArrayList<Integer>> letters,
        String hiddenWord
    ) {
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

    public boolean validateInputString(String input) {
        if (input.length() != 1) {
            LOGGER.info("Input string has invalid length");
            return false;
        } else if (!Character.isLetter(input.charAt(0))) {
            LOGGER.info("Input string must be a letter");
            return false;
        }
        return true;
    }

    public boolean validateWord(String word) {
        if (word.length() < 1) {
            LOGGER.warn("The word length has invalid length");
            return false;
        } else if (word.matches(".*\\d.*")) {
            LOGGER.warn("The word length has numbers");
            return false;
        }
        return true;
    }
}
