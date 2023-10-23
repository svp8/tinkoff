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
            if (dict.containsKey(l)) {
                dict.put(l, dict.get(l) + 1);
            } else {
                dict.put(l, 1);
            }
        }
        return dict;
    }
}
