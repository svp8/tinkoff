package edu.hw8.task3;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Generator {
    private static final char max_char_value = 'z';
    private static final char max_char_value_number = '9';
    private static final char min_char_value = '0';
    static boolean found=false;

    public static void main(String[] args) {
        char[] guess = new char[3];
        guess[0] = 'a';
        guess[1] = 'a';
        guess[2] = 'a';
        for (int i = 0; i < 5; i++) {
            System.out.println(new String(incrementGuess(guess)));
        }
//        System.out.println(crack("e10adc3949ba59abbe56e057f20f883e", 6));
//        System.out.println(crack("d8578edf8458ce06fbc5bb76a58c5ca4", 6));
//        System.out.println(crackParallel("58238e9ae2dd305d79c2ebc8c1883422", 3));
        System.out.println(crackParallel("d8578edf8458ce06fbc5bb76a58c5ca4", 6));

    }

    public static String crack(String hash, int max_num_chars) {
        boolean done = false;
        String guess_hash;
        char[] guess = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int num_chars = 1; num_chars <= max_num_chars && !done; num_chars++) {
            guess = new char[num_chars];
            for (int x = 0; x < num_chars; x++) {
                guess[x] = min_char_value;
            }
            //поток 1 - 100000-900000  поток 2-а00000-f00000
            while (canIncrementGuess(guess) && !done) {
                incrementGuess(guess);
                //check
                byte[] bytesOfGuess = new String(guess).getBytes(StandardCharsets.UTF_8);
                md.reset();
                md.update(bytesOfGuess);
                byte[] theMD5digest = md.digest();
                if (hash.equals(toHexString(theMD5digest))) {
                    done = true;
                }

            }
        }
        return new String(guess);
    }

    public static String crackParallel(String hash, int max_num_chars) {
        boolean done = false;
        String guess_hash;
        char[] guess = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        for (int num_chars = 1; num_chars <= max_num_chars && !done; num_chars++) {
            guess = new char[num_chars];
            for (int x = 0; x < num_chars; x++) {
                guess[x] = min_char_value;
            }
            int lastChar = 0;
            int charsNum = 36;
            char start = '0';
            for (int i = 0; i < charsNum; i += 9) {
                char[] chars = Arrays.copyOf(guess, guess.length);
                chars[0] = start;
                if (start == '9') {
                    start = 'h';
                } else {
                    start += 9;
                }
                checkParallel(chars,start,hash);
            }
            //поток 1 - 100000-900000  поток 2-а00000-f00000

        }
        return null;
    }

    public static void checkParallel(char[] guess, char maxCharForThread, String hash) {
        Runnable runnable = () -> {
            System.out.println("Thread created");
            MessageDigest md = null;
            boolean done=false;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            while (canIncrementGuessParallel(guess, maxCharForThread) && !done&&!found) {
                incrementGuess(guess);
                //check
                byte[] bytesOfGuess = new String(guess).getBytes(StandardCharsets.UTF_8);
                md.reset();
                md.update(bytesOfGuess);
                byte[] theMD5digest = md.digest();
                if (hash.equals(toHexString(theMD5digest))) {
                    done = true;
                    found=true;
                    System.out.println(guess);
                }
            }
        };
        new Thread(runnable).start();
    }

    public static boolean canIncrementGuessParallel(char[] guess, char maxCharForThread) {
        boolean canIncrement = false;

        for (int x = 0; x < guess.length; x++) {
            if (guess[x] < max_char_value) {
                canIncrement = true;
            }
        }
        if (guess[0] == maxCharForThread) {
            return false;
        }
        return canIncrement;
    }

    public static boolean canIncrementGuess(char[] guess) {
        boolean canIncrement = false;

        for (int x = 0; x < guess.length; x++) {
            if (guess[x] < max_char_value) {
                canIncrement = true;
            }
        }
        return canIncrement;
    }

    public static char[] incrementGuess(char[] guess) {
        for (int x = (guess.length - 1); x >= 0; x--) {
            if (guess[x] < max_char_value) {
                if (guess[x] == max_char_value_number) {
                    guess[x] = 'a';
                } else {
                    guess[x]++;
                }
                //правое значение обнуляем
                if (x < (guess.length - 1)) {
                    for (int i = x; i < guess.length - 1; i++) {
                        guess[x + 1] = min_char_value;
                    }
//                    guess[x+1] = min_char_value;
                }
                break;
            }
        }
        return guess;
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
//    protected String hashToString(byte[] hash)
//    {
//        StringBuffer sb = new StringBuffer();
//
//        for(int i = 0; i < hash.length; i++)
//        {
//            sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
//        }
//        return sb.toString();
//    }
}
