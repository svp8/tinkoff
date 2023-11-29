package edu.hw7;

import java.util.stream.IntStream;

public final class Task2 {
    private Task2() {

    }

    public static int factorial(int number) {
        if (number == 0) {
            return 1;
        } else if (number < 0) {
            throw new IllegalArgumentException();
        }
        IntStream numbersStream = IntStream.range(1, number + 1);
        return numbersStream.parallel().reduce((x, y) -> x * y).getAsInt();
    }
}
