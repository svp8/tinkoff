package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    Logger logger = LogManager.getLogger();

    @ParameterizedTest()
    @ValueSource(ints = {10000, 100000, 10_000_000, 100_000_000})
    void calculatePi(int n) {
        long start = System.nanoTime();
        double pi = Task4.calculatePi(n);
        logger.info(pi);
        long end = System.nanoTime();
        logger.info("Ошибка: " + (3.14159265359 - pi));
        logger.info("Время: " + (end - start));
    }

    //10тыс 0.0040073, 100тыс 0.0089526, 1млн 0.00086 и 10млн -1.8694640999994405E-4 91732
    //test 10mln 1min17sec
    @ParameterizedTest
    @CsvSource({"100003,2", "100003,3", "100003,4", "100003,5", "1000030,2", "1000030,3", "1000030,4"})
    void calculatePiParallel(int n, int threads) throws InterruptedException {
        long start = System.nanoTime();
        logger.info(Task4.calculatePiParallel(n, threads));
        long end = System.nanoTime();
        logger.info((end - start) / 1000000);
    }

    @ParameterizedTest
    @CsvSource({"2,4", "100003,2", "100003,3", "100003,4", "100003,5", "1000030,2", "1000030,3", "1000030,4"})
    void compare(int n, int threads) throws InterruptedException {
        long start = System.nanoTime();
        logger.info(Task4.calculatePi(n));
        long end = System.nanoTime();
        logger.info("Время последовательно: " + (end - start) / 1000000);

        start = System.nanoTime();
        logger.info(Task4.calculatePiParallel(n, threads));
        end = System.nanoTime();
        logger.info("Время параллельно : " + (end - start) / 1000000);
    }
    //1mln
    //9916 9451 5 potokov
    //10032 9560 4 potoka
    //9869 9809 3 potoka
    //11249 9583 2 potoka
}
