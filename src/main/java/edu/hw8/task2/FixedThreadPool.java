package edu.hw8.task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FixedThreadPool implements ThreadPool {
    private final Thread[] pool;
    private static final Logger LOGGER = LogManager.getLogger();

    private FixedThreadPool(int n) {
        this.pool = new Thread[n];
    }

    public static FixedThreadPool create(int n) {
        return new FixedThreadPool(n);
    }

    @Override
    public void start() {
    }

    @Override
    public void execute(Runnable runnable) {
        boolean flag = false;
        while (!flag) {
            synchronized (pool) {
                for (int i = 0; i < pool.length; i++) {
                    if (pool[i] == null || !pool[i].isAlive()) {
                        flag = true;
                        pool[i] = new Thread(runnable);
                        pool[i].start();
                        break;
                    }
                }
            }
            if (!flag) {
                LOGGER.info("Thread is waiting");
            }
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread task : pool) {
            if (task != null) {
                task.join();
            }
        }
        LOGGER.info("Thread pool closed");
    }
}
