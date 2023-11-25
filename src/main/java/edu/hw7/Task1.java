package edu.hw7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {
    private Task1() {

    }

    public static int counter(int threadCount) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Callable<Integer> runnable = counter::incrementAndGet;
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            List<Callable<Integer>> tasks = new ArrayList<>(threadCount);
            for (int i = 0; i < threadCount; i++) {
                tasks.add(runnable);
            }
            executor.invokeAll(tasks);
            return counter.get();
        }
    }
}
