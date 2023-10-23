package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public final class Task1 {

    private Task1() {
    }

    public static HashMap<Character, Character> getLetters() {
        HashMap<Character, Character> letters = new HashMap<>();
        letters.put('a', 'z');
        letters.put('b', 'y');
        letters.put('c', 'x');
        letters.put('d', 'w');
        letters.put('e', 'v');
        letters.put('f', 'u');
        letters.put('g', 't');
        letters.put('h', 's');
        letters.put('i', 'r');
        letters.put('j', 'q');
        letters.put('k', 'p');
        letters.put('l', 'o');
        letters.put('m', 'n');
        return letters;
    }

    public static String atbash(String str) {
        HashMap<Character, Character> letters = getLetters();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            boolean flag = false;
            char letter = str.charAt(i);
            boolean isUpperCase = Character.isUpperCase(letter);
            letter = Character.toLowerCase(letter);
            if (letters.containsKey(Character.toLowerCase(letter))) {
                if (isUpperCase) {
                    stringBuilder.append(Character.toUpperCase(letters.get(letter)));
                } else {
                    stringBuilder.append(letters.get(letter));
                }
                flag = true;
            } else {
                for (Map.Entry<Character, Character> entry : letters.entrySet()) {
                    if (entry.getValue().equals(Character.toLowerCase(letter))) {
                        if (isUpperCase) {
                            stringBuilder.append(Character.toUpperCase(entry.getKey()));
                        } else {
                            stringBuilder.append(entry.getKey());
                        }
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                stringBuilder.append(str.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
}
