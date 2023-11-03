package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Task3 {
    private Task3() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Map<T, Integer> dict = new HashMap<>();
        for (T l : list) {
            dict.merge(l, 1, Integer::sum);
        }
        return dict;
    }
}
