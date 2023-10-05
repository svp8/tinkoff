package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    void isNestable() {
        Assertions.assertTrue(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6}));
        Assertions.assertTrue(Task3.isNestable(new int[] {3, 1}, new int[] {4, 0}));
        Assertions.assertFalse(Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9}));
        Assertions.assertFalse(Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {2, 3}));
    }
}
