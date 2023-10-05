package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {

    @Test
    void knightBoardCapture() {
        Assertions.assertTrue(Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        }));
        Assertions.assertFalse(Task8.knightBoardCapture(new int[][]{
  {1, 0, 1, 0, 1, 0, 1, 0},
  {0, 1, 0, 1, 0, 1, 0, 1},
  {0, 0, 0, 0, 1, 0, 1, 0},
  {0, 0, 1, 0, 0, 1, 0, 1},
  {1, 0, 0, 0, 1, 0, 1, 0},
  {0, 0, 0, 0, 0, 1, 0, 1},
  {1, 0, 0, 0, 1, 0, 1, 0},
  {0, 0, 0, 1, 0, 1, 0, 1}
}));
        Assertions.assertFalse(Task8.knightBoardCapture( new int[][]{
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        }));
    }
}
