package edu.project1Hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dictionary implements DictionaryInterface {
    private ArrayList<String> words;

    public Dictionary() {
        words = new ArrayList<>(Arrays.asList("notebook", "tinkoff", "cookie", "book", "smartphone"));
    }
    @Override
    public String giveWord() {
        Random random=new Random();
        int index= random.nextInt(words.size());
        return words.get(index).toLowerCase();
    }
}
