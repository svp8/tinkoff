package edu.hw1;

public final class Task2 {
    private Task2() {

    }

    private static final int DECIMAL = 10;

    public static int countDigits(int number) {
        int count = 0;
        int number1 = number;
        if (number1 == 0) {
            return 1;
        }
        while (number1 != 0) {
            count++;
            number1 = number1 / DECIMAL;
        }
        return count;
    }
}
