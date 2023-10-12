package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class Task7Test {
    Task7 task=new Task7();
    @Test
    public void rotateRight() {
        Assertions.assertEquals(4,task.rotateRight(8,1));
        Assertions.assertEquals(4,task.rotateRight(8,5));
    }
    @Test
    public void rotateLeft() {
        Assertions.assertEquals(1,task.rotateLeft(16,1));
        Assertions.assertEquals(6,task.rotateLeft(17,2));
    }
}
