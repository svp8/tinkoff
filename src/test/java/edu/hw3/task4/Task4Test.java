package edu.hw3.task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    void convertToRoman() {
        Assertions.assertEquals("MMMCMXCIX", Task4.convertToRoman(3999));
        Assertions.assertEquals("XII", Task4.convertToRoman(12));
        Assertions.assertEquals("II", Task4.convertToRoman(2));
        Assertions.assertEquals("XVI", Task4.convertToRoman(16));
    }

    @Test
    @DisplayName("Test numbers out of range")
    void convertToRomanException() {
        Assertions.assertThrows(IllegalArgumentException.class,()->Task4.convertToRoman(0));
        Assertions.assertThrows(IllegalArgumentException.class,()->Task4.convertToRoman(-4));
        Assertions.assertThrows(IllegalArgumentException.class,()->Task4.convertToRoman(4000));

    }
}
