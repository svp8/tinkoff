package edu.project1Hangman;

import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class Dictionary implements DictionaryInterface {
    private final List<String> words;
    public static final Logger LOGGER = LogManager.getLogger();

    public Dictionary(List<String> words) {
        for (String word : words) {
            if (!validateWord(word)) {
                throw new IllegalArgumentException();
            }
        }
        this.words = words;
    }

    @Override
    public @NotNull String giveWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index).toLowerCase();
    }

    public boolean validateWord(String word) {
        if (word.length() < 1) {
            LOGGER.warn("The word length has invalid length");
            LOGGER.warn("The word length must not have numbers");
            return false;
        }
        if (word.matches(".*\\d.*")) {
            LOGGER.warn("The word length has numbers");
            return false;
        }
        return true;
    }
}
