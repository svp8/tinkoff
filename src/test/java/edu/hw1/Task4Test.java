package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    void fixString() {
        Assertions.assertEquals(
            "This is a mixed up string.",
            Task4.fixString("hTsii  s aimex dpus rtni.g")
        );
        Assertions.assertEquals(
            "214365",
            Task4.fixString("123456")
        );
        Assertions.assertEquals(
            "abcde",
            Task4.fixString("badce")
        );
        Assertions.assertEquals(
            "a",
            Task4.fixString("a")
        );
    }
}
