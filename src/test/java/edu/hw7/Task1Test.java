package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void counter() throws InterruptedException {
        int counter = Task1.counter(6);
        Assertions.assertEquals(6, counter);
    }
}
