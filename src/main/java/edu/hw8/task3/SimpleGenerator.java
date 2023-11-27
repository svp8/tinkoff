package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class SimpleGenerator implements Generator {
    private static final char MAX_CHAR_VALUE = 'z';
    private static final char MIN_CHAR_VALUE = '0';

    public String find(String hash, int maxNumChars) {
        boolean done = false;
        char[] guess;
        String result = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int numChars = 1; numChars <= maxNumChars && !done; numChars++) {
            guess = new char[numChars];
            for (int x = 0; x < numChars; x++) {
                guess[x] = MIN_CHAR_VALUE;
            }
            while (canIncrementGuess(guess) && !done) {
                Generator.incrementGuess(guess);
                byte[] bytesOfGuess = new String(guess).getBytes(StandardCharsets.UTF_8);
                md.reset();
                md.update(bytesOfGuess);
                byte[] theMD5digest = md.digest();
                if (hash.equals(Generator.toHexString(theMD5digest))) {
                    done = true;
                    result = new String(guess);
                }

            }
        }
        return result;
    }

    @Override
    public Map<String, String> findAll(Map<String, String> hashes, int maxDigitCount) {
        Map<String, String> result = new HashMap<>(hashes.size());
        hashes.forEach((key, value) -> {
            result.put(key, find(value, maxDigitCount));
        });
        return result;
    }

    public static boolean canIncrementGuess(char[] guess) {
        boolean canIncrement = false;
        for (char c : guess) {
            if (c < MAX_CHAR_VALUE) {
                canIncrement = true;
                break;
            }
        }
        return canIncrement;
    }

}
