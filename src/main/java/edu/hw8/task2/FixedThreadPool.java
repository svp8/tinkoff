package edu.hw8.task2;

import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FixedThreadPool implements ThreadPool {
    private final Thread[] pool;
    private final ConcurrentLinkedQueue<Runnable> workerQueue;
    private static final Logger LOGGER = LogManager.getLogger();

    private FixedThreadPool(int n) {
        this.pool = new Thread[n];
        workerQueue = new ConcurrentLinkedQueue<>();
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

    public boolean isAllInterrupted() {
        for (Thread worker : pool) {
            if (worker.isAlive()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Close start");
        while (!workerQueue.isEmpty()) {
        }
        while (!isAllInterrupted()) {
            for (int i = 0; i < pool.length; i++) {
                pool[i].interrupt();
            }
        }
        LOGGER.info("Thread pool closed");
    }

    class Worker extends Thread {
        Worker(String name) {
            super(name);
        }

        public void run() {
            while (!interrupted() || !workerQueue.isEmpty()) {
                try {
                    Runnable r;
                    while ((r = workerQueue.poll()) != null) {
                        LOGGER.info("Started execution of task");
                        r.run();
                    }
                } catch (Exception e) {
                    LOGGER.warn(e);
                }
            }
            LOGGER.info(this.getName() + " stopped");
        }
    }
}
