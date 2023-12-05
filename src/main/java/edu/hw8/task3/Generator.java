package edu.hw8.task3;

import java.util.Map;

public interface Generator {

    int INT = 0xFF;

    String find(String hash, int maxDigitCount);

    Map<String, String> findAll(Map<String, String> hashes, int maxDigitCount);

    static void incrementGuess(char[] guess) {
        char maxCharValue = 'z';
        char maxCharValueNumber = '9';
        char minCharValue = '0';
        for (int x = (guess.length - 1); x >= 0; x--) {
            if (guess[x] < maxCharValue) {
                if (guess[x] == maxCharValueNumber) {
                    guess[x] = 'a';
                } else {
                    guess[x]++;
                }
                //правое значение обнуляем
                if (x < (guess.length - 1)) {
                    for (int i = x; i < guess.length - 1; i++) {
                        guess[x + 1] = minCharValue;
                    }
                }
                break;
            }
        }
    }

    static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(INT & aByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
