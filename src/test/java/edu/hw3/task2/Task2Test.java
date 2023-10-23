package edu.hw3.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    void clusterize() {
        Assertions.assertEquals(Arrays.asList("()", "()", "()"), Task2.clusterize("()()()"));
        Assertions.assertEquals(List.of("((()))"), Task2.clusterize("((()))"));
        Assertions.assertEquals(Arrays.asList("((()))", "(())", "()", "()", "(()())"),
            Task2.clusterize("((()))(())()()(()())"));
        Assertions.assertEquals(Arrays.asList("((())())", "(()(()()))"), Task2.clusterize("((())())(()(()()))"));
    }
}
