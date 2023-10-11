package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task6Test {

    @ParameterizedTest(name = "#{index} - Test with Number: {1}")
    @CsvSource({"3, 3524","5,6621", "4,6554", "3,1234"})
    void countK(int expected,int number) {
        Assertions.assertEquals(expected, Task6.countK(number, 0));
    }
}
