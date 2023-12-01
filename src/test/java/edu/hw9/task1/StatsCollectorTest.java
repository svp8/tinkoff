package edu.hw9.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class StatsCollectorTest {
    Logger logger= LogManager.getLogger();

    @Test
    void push() {
    }

    @Test
    void getStats() {
        StatsCollector statsCollector=new StatsCollector();
        StatConsumer statConsumer=new StatConsumer();
        Runnable collect=()->{
            logger.info(statConsumer.processStat(statsCollector,"123"));
        };
        Runnable push=()->{
            statsCollector.push(new Stat("123",new double[]{1,2,3,4,5,6,7,8,9,10}));
        };
        try(ExecutorService executorService=Executors.newFixedThreadPool(10)){
            for(int i=0;i<10;i++){
                executorService.execute(push);
            }
            for(int i=0;i<10;i++){
                executorService.execute(collect);
            }
        }
        Assertions.assertEquals(10,statsCollector.getStats().size());

    }
}
