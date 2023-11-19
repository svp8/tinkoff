package edu.hw5;

public final class Task4 {
    private Task4() {

    }

    public static boolean containsSpecialSymbols(String password) {
        if (password == null) {
            return false;
        }
        return password.matches(".*[@$!%*?&].*");
    }
}
