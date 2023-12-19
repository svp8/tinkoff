package edu.hw10.task2;

public class FibCalculatorImpl implements FibCalculator {
    @Override
    public long fib(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException();
        }
        if (number == 1) {
            return 0;
        } else if (number == 2) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
