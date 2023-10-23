package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private int currentIndex;
    private final List<T> list;

    public BackwardIterator(List<T> list) {
        this.list = list;
        this.currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        if (currentIndex >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(currentIndex--);
    }
}
