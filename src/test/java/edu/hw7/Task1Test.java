package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @ParameterizedTest
    @CsvSource({"1", "6", "100", "10000"})
    void counter(int n) throws InterruptedException {
        int counter = Task1.counter(n);
        Assertions.assertEquals(n, counter);
    }
}
