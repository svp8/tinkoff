package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {
    private Task1() {

    }

    public static int counter(int threadCount) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Runnable runnable = counter::incrementAndGet;
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < threadCount; i++) {
                executor.execute(runnable);
            }
        }
        return counter.get();
    }
}
