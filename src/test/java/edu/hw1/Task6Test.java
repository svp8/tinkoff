package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class Task6Test {

    @Test
    void countK() {
        Assertions.assertEquals(3, Task6.countK(3524, 0));
        Assertions.assertEquals(5, Task6.countK(6621, 0));
        Assertions.assertEquals(4, Task6.countK(6554, 0));
        Assertions.assertEquals(3, Task6.countK(1234, 0));
    }
}
