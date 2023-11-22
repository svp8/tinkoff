package edu.hw7;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class Task1 {
    private Task1() {

    }

    public static int counter(int threadCount) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger(0);
        Runnable runnable = counter::incrementAndGet;
        ArrayList<Thread> threads = new ArrayList<>(threadCount);
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Thread(runnable));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        return counter.get();
    }
}
