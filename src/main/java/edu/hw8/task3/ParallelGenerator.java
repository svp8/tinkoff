package edu.hw8.task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelGenerator implements Generator {
    private static final char MAX_CHAR_VALUE = 'z';
    private static final char MIN_CHAR_VALUE = '0';
    public static final int THREAD_COUNT = 4;
    public static final int CHARS_COUNT = 36;
    public static final int MAX_NUMBER_VALUE = 9;
    static boolean found = false;

    public String find(String hash, int maxNumChars) {
        char[] guess;
        found = false;
        List<Callable<String>> tasks = new ArrayList<>(THREAD_COUNT);
        try (ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT)) {
            for (int numChars = 1; numChars <= maxNumChars; numChars++) {
                guess = new char[numChars];
                for (int x = 0; x < numChars; x++) {
                    guess[x] = MIN_CHAR_VALUE;
                }
                int add = CHARS_COUNT / THREAD_COUNT;
                char start = '0';
                //поток 1 - 100000-900000  поток 2-900000-h00000 и т.д.
                for (int i = 0; i < CHARS_COUNT; i += add) {
                    char[] chars = Arrays.copyOf(guess, guess.length);
                    chars[0] = start;
                    if (start == '9') {
                        start = 'h';
                    } else {
                        start += MAX_NUMBER_VALUE;
                    }
                    tasks.add(checkParallel(chars, start, hash));
                }

            }
            List<Future<String>> results = executorService.invokeAll(tasks);
            for (Future<String> i : results) {
                String temp = i.get();
                if (temp != null) {
                    return temp;
                }
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, String> findAll(Map<String, String> hashes, int maxDigitCount) {
        Map<String, String> result = new HashMap<>(hashes.size());
        hashes.forEach((key, value) -> {
            result.put(key, find(value, maxDigitCount));
        });
        return result;
    }

    public static Callable<String> checkParallel(char[] guess, char maxCharForThread, String hash) {
        return () -> {
            String result = null;
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            while (canIncrementGuessParallel(guess, maxCharForThread) && !found) {
                Generator.incrementGuess(guess);
                byte[] bytesOfGuess = new String(guess).getBytes(StandardCharsets.UTF_8);
                md.reset();
                md.update(bytesOfGuess);
                byte[] theMD5digest = md.digest();
                if (hash.equals(Generator.toHexString(theMD5digest))) {
                    found = true;
                    result = new String(guess);
                }
            }
            return result;
        };
    }

    public static boolean canIncrementGuessParallel(char[] guess, char maxCharForThread) {
        boolean canIncrement = false;
        for (char c : guess) {
            if (c < MAX_CHAR_VALUE) {
                canIncrement = true;
                break;
            }
        }
        if (guess[0] == maxCharForThread) {
            return false;
        }
        return canIncrement;
    }
}
