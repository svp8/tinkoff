package edu.hw3.task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BackwardIteratorTest {
    @Test
    void testBackwardIteration() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        List<Integer> list = new ArrayList<>();
        while (backwardIterator.hasNext()) {
            list.add(backwardIterator.next());
        }
        Assertions.assertEquals(Arrays.asList(3, 2, 1), list);
    }

    @Test
    @DisplayName("List should has next")
    void testHasNext() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        Assertions.assertTrue(backwardIterator.hasNext());
    }

    @Test
    @DisplayName("List should return last element")
    void testLastElement() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(List.of(1, 2, 3));
        Assertions.assertEquals(3, backwardIterator.next());
    }
}
