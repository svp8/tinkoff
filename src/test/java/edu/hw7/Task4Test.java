package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {
    Logger logger = LogManager.getLogger();

    @Test
    void calculatePi() {
        long start = System.currentTimeMillis();
        double pi = Task4.calculatePi(1000);
        logger.info(pi);
        long end = System.currentTimeMillis();
        logger.info(3.14159265359 - pi);
        logger.info(end - start);
    }

    //10тыс 0.0040073, 100тыс 0.0089526, 1млн 0.00086 и 10млн -1.8694640999994405E-4 91732
    //test 10mln 1min17sec
    @Test
    void calculatePiParallel() throws InterruptedException {
        long start = System.currentTimeMillis();
        logger.info(Task4.calculatePiParallel(100000,4));
        long end = System.currentTimeMillis();
        logger.info(end - start);
    }

    @Test
    void compare() throws InterruptedException {
        int n = 1;
        long start = System.currentTimeMillis();
        logger.info(Task4.calculatePi(n));
        long end = System.currentTimeMillis();
        logger.info((end - start));

        start = System.currentTimeMillis();
        logger.info(Task4.calculatePiParallel(n,4));
        end = System.currentTimeMillis();
        logger.info((end - start));
    }
    //1mln
    //9916 9451 5 potokov
    //10032 9560 4 potoka
    //9869 9809 3 potoka
    //11249 9583 2 potoka
}
