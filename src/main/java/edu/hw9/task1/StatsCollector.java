package edu.hw9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class StatsCollector {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    List<Stat> stats = new ArrayList<>();

    public void push(Stat stat) {
        lock.writeLock().lock();
        try {
            stats.add(stat);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public List<Stat> getStats() {
        lock.readLock().lock();
        try {
            return stats;
        } finally {
            lock.readLock().unlock();
        }
    }
}
