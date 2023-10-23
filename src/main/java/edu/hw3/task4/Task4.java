package edu.hw3.task4;

import java.util.LinkedHashMap;
import java.util.Map;

public final class Task4 {
    private Task4() {
    }

    private static final int THOUSAND = 1000;
    private static final int HUNDRED = 100;
    private static final int NINEHUNDRED = 900;
    private static final int FIVEHUNDRED = 500;
    private static final int FOURHUNDRED = 400;
    private static final int NINETY = 90;
    private static final int FIFTY = 50;
    private static final int FOURTY = 40;
    private static final int TEN = 10;
    private static final int NINE = 9;
    private static final int FIVE = 5;
    private static final int FOUR = 4;

    public static String convertToRoman(int num) {
        LinkedHashMap<String, Integer> romanNumerals = new LinkedHashMap<String, Integer>();
        romanNumerals.put("M", THOUSAND);
        romanNumerals.put("CM", NINEHUNDRED);
        romanNumerals.put("D", FIVEHUNDRED);
        romanNumerals.put("CD", FOURHUNDRED);
        romanNumerals.put("C", HUNDRED);
        romanNumerals.put("XC", NINETY);
        romanNumerals.put("L", FIFTY);
        romanNumerals.put("XL", FOURTY);
        romanNumerals.put("X", TEN);
        romanNumerals.put("IX", NINE);
        romanNumerals.put("V", FIVE);
        romanNumerals.put("IV", FOUR);
        romanNumerals.put("I", 1);
        StringBuilder roman = new StringBuilder();
        int number = num;
        for (Map.Entry<String, Integer> entry : romanNumerals.entrySet()) {
            while (number >= entry.getValue()) {
                number = number - entry.getValue();
                roman.append(entry.getKey());
            }
        }
        return roman.toString();
    }
}
