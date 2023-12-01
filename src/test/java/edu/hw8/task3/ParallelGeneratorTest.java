package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

class ParallelGeneratorTest {
    Logger logger = LogManager.getLogger();

    @Test
    void find() {
        int threadCount = 4;
        Generator generator = new ParallelGenerator(threadCount);
        String actual = generator.find("202cb962ac59075b964b07152d234b70", 3);
        Assertions.assertEquals("123", actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 30})
    void findAll(int threadCount) {
        Generator generator = new ParallelGenerator(threadCount);
        Map<String, String> values = Map.of("a.v.petrov", "49f68a5c8493ec2c0bf489821c21fc3b",
            "v.v.belov", "caf1a3dfb505ffed0d024130f58c5cfa"
        );
        Map<String, String> actual = generator.findAll(values, 3);
        Assertions.assertEquals("hi", actual.get("a.v.petrov"));
        Assertions.assertEquals("321", actual.get("v.v.belov"));
    }

    @Test
    void findEmpty() {
        int threadCount = 4;
        Generator generator = new ParallelGenerator(threadCount);
        String actual = generator.find("202cb962ac59075b964b07152d234b70", 2);
        Assertions.assertEquals(null, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    void compare(int threadCount) {
        Generator generator = new ParallelGenerator(threadCount);
        long start = System.nanoTime();
        String actual = generator.find("827ccb0eea8a706c4c34a16891f84e7b", 6);
        long end = System.nanoTime();
        logger.info("ParallelGenerator: " + ((end - start) / 1_000_000));
        Assertions.assertEquals("12345", actual);
    }
    @Test
    void compareSimple() {
        Generator generator = new SimpleGenerator();
        long start = System.nanoTime();
        String actual = generator.find("827ccb0eea8a706c4c34a16891f84e7b", 6);
        long end = System.nanoTime();
        logger.info("ParallelGenerator: " + ((end - start) / 1_000_000));
        Assertions.assertEquals("12345", actual);
    }
}
