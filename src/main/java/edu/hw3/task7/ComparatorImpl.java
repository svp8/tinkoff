package edu.hw3.task7;

import java.util.Comparator;

public class ComparatorImpl implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        return o1.compareTo(o2);
    }
}
