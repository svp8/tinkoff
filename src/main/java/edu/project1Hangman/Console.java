package edu.project1Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Console {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) {
        Console console = new Console();
        console.start(new Dictionary(Arrays.asList("notebook", "tinkoff", "cookie", "book", "smartphone")));
    }

    public void start(DictionaryInterface dictionary) {
        String input;
        Result result;
        String word = dictionary.giveWord();
        Game game = new Game(word, MAX_ATTEMPTS);
        result = new Result.RightGuess(MAX_ATTEMPTS, 0, game.getCurrentGuess());
        LOGGER.info("You can quit by typing !");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!(result instanceof Result.Lose || result instanceof Result.Win)) {
                LOGGER.info("The word: " + result.currentGuess());
                LOGGER.info("Guess a letter:");
                input = bufferedReader.readLine();
                if (input.equals("!")) {
                    LOGGER.info("Game stopped");
                    break;
                }
                if (validateInputString(input)) {
                    result = game.play(input.toLowerCase().charAt(0));
                    LOGGER.info(result.message());
                }

            }
            LOGGER.info("The hidden word is " + word);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public boolean validateInputString(String input) {
        String numberWarn = "Input string must be a letter";
        if (input.length() != 1) {
            LOGGER.info("Input string has invalid length");
            LOGGER.info(numberWarn);
            return false;
        } else if (!Character.isLetter(input.charAt(0))) {
            LOGGER.info(numberWarn);
            return false;
        }
        return true;
    }

}
