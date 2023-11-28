package edu.hw7;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;
import static java.lang.Math.pow;

public final class Task4 {

    public static final int FOUR = 4;

    private Task4() {

    }

    public static double calculatePi(int n) {
        double totalCount = 0;
        double circleCount = 0;
        SecureRandom random = new SecureRandom();
        while (totalCount < n) {
            double x = random.nextDouble(0, 1);
            double y = random.nextDouble(0, 1);
            if ((pow(x, 2) + pow(y, 2)) < 1) {
                circleCount += 1;
            }
            totalCount++;
        }
        double pi = FOUR * (circleCount / totalCount);
        return pi;
    }

    public static double calculatePiParallel(int n, int threadCount) throws InterruptedException {
        final LongAdder totalCount = new LongAdder();
        final LongAdder circleCount = new LongAdder();
        int left = n % threadCount;
        Runnable task = () -> {
            for (int i = 0; i < n / threadCount; i++) {
                if ((pow(ThreadLocalRandom.current().nextDouble(0, 1), 2)
                    + pow(ThreadLocalRandom.current().nextDouble(0, 1), 2)) < 1) {
                    circleCount.increment();
                }
                totalCount.increment();
            }
        };
        Runnable leftTask = () -> {
            for (int i = 0; i < left; i++) {
                if ((pow(ThreadLocalRandom.current().nextDouble(0, 1), 2)
                    + pow(ThreadLocalRandom.current().nextDouble(0, 1), 2)) < 1) {
                    circleCount.increment();
                }
                totalCount.increment();
            }
        };
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            executor.execute(leftTask);
            for (int i = 0; i < threadCount; i++) {
                executor.execute(task);
            }
        }
        double pi = FOUR * (circleCount.doubleValue() / totalCount.doubleValue());
        return pi;
    }
}
