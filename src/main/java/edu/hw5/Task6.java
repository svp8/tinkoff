package edu.hw5;

import java.util.regex.Pattern;

public final class Task6 {
    private Task6() {

    }

    public static boolean isSubString(String s, String t) {
        if (t == null) {
            return false;
        }
        return s.matches(".*" + Pattern.quote(t) + ".*");
    }
}
