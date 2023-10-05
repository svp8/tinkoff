package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    void minutesToSeconds() {
        Assertions.assertEquals(60, Task1.minutesToSeconds("01:00"));
        Assertions.assertEquals(836, Task1.minutesToSeconds("13:56"));
        Assertions.assertEquals(-1, Task1.minutesToSeconds("10:60"));
        Assertions.assertNotEquals(-1, Task1.minutesToSeconds("1440:10"));
    }
}
