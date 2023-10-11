package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;

class Task0Test {

    @Test
    @DisplayName("test System.out")
    void hello() throws Exception {
        String text = tapSystemOut(Task0::hello);
        assertTrue(text.contains("Привет, мир!"));
    }
}
