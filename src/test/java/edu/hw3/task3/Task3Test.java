package edu.hw3.task3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    @Test
    @DisplayName("Test with string list 1")
    void freqDictString() {
        Map<String, Integer> dict = Task3.freqDict(Arrays.asList("a", "bb", "a", "bb"));
        assertThat(dict).containsEntry("a", 2);
        assertThat(dict).containsEntry("bb", 2);
    }

    @Test
    @DisplayName("Test with string list 2")
    void freqDictString2() {
        Map<String, Integer> dict = Task3.freqDict(Arrays.asList("this", "and", "that", "and"));
        assertThat(dict).containsEntry("this", 1);
        assertThat(dict).containsEntry("that", 1);
        assertThat(dict).containsEntry("and", 2);
    }

    @Test
    @DisplayName("Test with string list 3")
    void freqDictString3() {
        Map<String, Integer> dict = Task3.freqDict(Arrays.asList("код", "код", "код", "bug"));
        assertThat(dict).containsEntry("код", 3);
        assertThat(dict).containsEntry("bug", 1);
    }

    @Test
    @DisplayName("Test with int list")
    void freqDictNumber() {
        Map<Integer, Integer> dict = Task3.freqDict(Arrays.asList(1, 1, 2, 2));
        assertThat(dict).containsEntry(1, 2);
        assertThat(dict).containsEntry(2, 2);
    }
}
