package edu.project1Hangman;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {
    public static final Logger logger = LogManager.getLogger();

    public static void start() {
        Dictionary dictionary = new Dictionary();
        String input;
        Result result;
        Game game = new Game(dictionary.giveWord());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            logger.info("The word: " + game.getCurrentGuess().toString());
            logger.info("Guess a letter:");
            try {
                input = bufferedReader.readLine();
                if (input.equals("!")) {
                    logger.info("Game stopped");
                    break;
                }
                if (validateInputString(input)) {
                    result = game.play(input.toLowerCase().charAt(0));
                    logger.info(result.getMessage());
                    if (result instanceof Result.Lose || result instanceof Result.Win) {
                        break;
                    }
                }

            } catch (IOException e) {
                logger.error(e.getMessage());
            }

        }
        try {
            bufferedReader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static boolean validateInputString(String input) {
        if (input.length() != 1) {
            logger.info("Input string has invalid length");
            return false;
        } else if (!Character.isLetter(input.charAt(0))) {
            logger.info("Input string must be a letter");
            return false;
        }
        return true;
    }
}
