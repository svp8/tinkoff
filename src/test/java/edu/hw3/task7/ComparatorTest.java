package edu.hw3.task7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ComparatorTest {

    @Test
    void compare() {
        TreeMap<String, String> tree = new TreeMap<>(new ComparatorImpl());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Test after adding another null")
    void compareAfterDoubleAdd() {
        TreeMap<String, String> tree = new TreeMap<>(new ComparatorImpl());
        tree.put(null, "test");
        tree.put("a", "test");
        tree.put(null, "test2");
        assertThat(tree.containsKey(null)).isTrue();
        assertThat(tree.get(null)).isEqualTo("test2");
    }
}
