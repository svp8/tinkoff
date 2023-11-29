package edu.hw8.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FixedThreadPoolTest {

    @Test
    void execute() throws Exception {
        List<FibThread> fibs = new ArrayList<>();
        fibs.add(new FibThread(1));
        fibs.add(new FibThread(2));
        fibs.add(new FibThread(3));
        fibs.add(new FibThread(4));
        fibs.add(new FibThread(50));
        try (FixedThreadPool threads = FixedThreadPool.create(2)) {
            for (FibThread i : fibs) {
                threads.execute(i);
            }
            threads.execute(() -> {
                throw new RuntimeException();
            });
        }
        Assertions.assertEquals(0, fibs.get(0).getAnswer());
        Assertions.assertEquals(1, fibs.get(1).getAnswer());
        Assertions.assertEquals(1, fibs.get(2).getAnswer());
        Assertions.assertEquals(2, fibs.get(3).getAnswer());
        Assertions.assertEquals(7778742049L, fibs.get(4).getAnswer());
    }
}
