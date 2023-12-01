package edu.hw8.task2;

import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FixedThreadPool implements ThreadPool {
    public static final int TIMEOUT = 5000;
    private final Thread[] pool;
    private final LinkedBlockingQueue<Runnable> workerQueue;
    private static final Logger LOGGER = LogManager.getLogger();

    private FixedThreadPool(int n) {
        this.pool = new Thread[n];
        workerQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < n; i++) {
            pool[i] = new Worker("Custom Pool Thread " + i);
        }
        start();
    }

    public static FixedThreadPool create(int n) {
        return new FixedThreadPool(n);
    }

    @Override
    public void start() {
        for (Thread thread : pool) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        LOGGER.info("put");
        workerQueue.add(runnable);

    }

    public boolean isAllStopped() {
        for (Thread worker : pool) {
            if (worker.isAlive() || !worker.isInterrupted()) {
                worker.interrupt();
                return false;
            }
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Close start");
        long start = System.currentTimeMillis();
        long timeOfExecution = 0;
        while (!workerQueue.isEmpty() && timeOfExecution < TIMEOUT) {
            timeOfExecution = System.currentTimeMillis() - start;
        }
        for (int i = 0; i < pool.length; i++) {
            pool[i].interrupt();
        }
        start = System.currentTimeMillis();
        while (!isAllStopped() && timeOfExecution < TIMEOUT) {
            timeOfExecution = System.currentTimeMillis() - start;
        }
        LOGGER.info("Thread pool closed");
    }

    class Worker extends Thread {
        Worker(String name) {
            super(name);
        }

        public void run() {
            while (!isInterrupted()) {
                Runnable r;
                try {
                    r = workerQueue.take();
                    r.run();
                } catch (InterruptedException e) {
                    LOGGER.warn(e);
                }
            }
            LOGGER.info(this.getName() + " stopped");
            LOGGER.info(workerQueue.size());
        }
    }
}
