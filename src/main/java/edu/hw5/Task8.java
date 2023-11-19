package edu.hw5;

public final class Task8 {
    private Task8() {

    }

    public static boolean lengthIsNotPrime(String input) {
        return input.matches("(^[0-1](([0-1][0-1])+)?$)");
    }

    public static boolean startsWith0andNotPrimeOr1AndPrime(String input) {
        return input.matches("(^0([0-1][0-1])*$)|(^1[0-1](([0-1][0-1])+)?$)");
    }

    public static boolean zeroesMultipleOf3(String input) {
        return input.matches("^(1*01*01*01*)+$");
    }

    public static boolean not11or111(String input) {
        return input.matches("^((?!^11?1$))[0-1]*$");
    }
}
