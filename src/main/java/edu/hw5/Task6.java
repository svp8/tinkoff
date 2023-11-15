package edu.hw5;

public final class Task6 {
    private Task6() {

    }

    public static boolean isSubString(String s, String t) {
        if (t == null) {
            return false;
        }
        String forRegex = t.replace("\\", "\\\\");
        return s.matches(".*" + forRegex + ".*");
    }
}
