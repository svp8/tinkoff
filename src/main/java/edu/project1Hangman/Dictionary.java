package edu.project1Hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Dictionary implements DictionaryInterface {
    private final ArrayList<String> words;

    public Dictionary() {
        words = new ArrayList<>(Arrays.asList("notebook", "tinkoff", "cookie", "book", "smartphone"));
    }

    @Override
    public @NotNull String giveWord() {
        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index).toLowerCase();
    }
}
