package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FixedThreadPool implements ThreadPool {
    private final Thread[] pool;
    private final BlockingQueue<Runnable> workerQueue;
    private static final Logger LOGGER = LogManager.getLogger();

    private FixedThreadPool(int n) {
        this.pool = new Thread[n];
        workerQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < n; i++) {
            pool[i] = new Worker("Custom Pool Thread " + i);
            pool[i].start();
        }
    }

    public static FixedThreadPool create(int n) {
        return new FixedThreadPool(n);
    }

    @Override
    public void start() {
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            workerQueue.put(runnable);
        } catch (InterruptedException ignored) {
        }
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
        while (!workerQueue.isEmpty()) {
        }
        for (Thread worker : pool) {
            worker.interrupt();
        }
        while (!isAllInterrupted()) {
        }
        LOGGER.info("Thread pool closed");
    }

    class Worker extends Thread {
        Worker(String name) {
            super(name);
        }

        public void run() {
            while (!interrupted()) {
                try {
                    Runnable r;
                    // Poll a runnable from the queue and execute it
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
