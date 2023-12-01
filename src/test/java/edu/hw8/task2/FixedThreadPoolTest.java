package edu.hw8.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FixedThreadPoolTest {

    @Test
    void execute() throws Exception {
        FibThread fibThread;
        try (FixedThreadPool threads = FixedThreadPool.create(10)) {
            fibThread = new FibThread(5, threads);
            threads.execute(fibThread);
        }

        Assertions.assertEquals(3, fibThread.getAnswer());
    }

    @Test
    void executeWithException() throws Exception {
        FibThread fibThread;
        try (FixedThreadPool threads = FixedThreadPool.create(10)) {
            fibThread = new FibThread(5, threads);
            threads.execute(fibThread);
            threads.execute(() -> {
                throw new RuntimeException();
            });
        }
        Assertions.assertEquals(3, fibThread.getAnswer());
    }
}
