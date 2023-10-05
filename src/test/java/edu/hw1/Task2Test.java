package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    void countDigits() {
        Assertions.assertEquals(3, Task2.countDigits(123));
        Assertions.assertEquals(1, Task2.countDigits(0));
        Assertions.assertEquals(5, Task2.countDigits(59873));
    }
}
