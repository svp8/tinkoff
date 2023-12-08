package edu.hw9.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class StatsCollectorTest {
    Logger logger = LogManager.getLogger();

    @Test
    void push() {
    }

    @Test
    void getStats() {
        StatsCollector statsCollector = new StatsCollector(4);
        Runnable push = () -> statsCollector.push("123", new double[] {1, 2, 3});
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            for (int i = 0; i < 10000; i++) {
                executorService.execute(push);
            }
        }
        Assertions.assertEquals(10000, statsCollector.getStats().size());

    }
}
