package edu.hw5;

public final class Task6 {
    private Task6() {

    }

    public static boolean isSubString(String s, String t) {
        return s.matches(".*" + t + ".*");
    }
}
