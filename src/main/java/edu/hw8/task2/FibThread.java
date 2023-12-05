package edu.hw8.task2;

public class FibThread implements Runnable {
    private final int n;
    private volatile long answer = -1;
    private final FixedThreadPool fixedThreadPool;

    public long getAnswer() {
        return answer;
    }

    public FibThread(int n, FixedThreadPool fixedThreadPool) {
        this.n = n;
        this.fixedThreadPool = fixedThreadPool;
    }

    @Override
    public void run() {
        if (n == 1) {
            answer = 0;
        } else if (n == 2) {
            answer = 1;
        } else {
            FibThread fibThread2 = new FibThread(n - 2, fixedThreadPool);
            FibThread fibThread1 = new FibThread(n - 1, fixedThreadPool);
            fixedThreadPool.execute(fibThread1);
            fixedThreadPool.execute(fibThread2);
            while (fibThread1.getAnswer() == -1 || fibThread2.getAnswer() == -1) {
            }
            long l2 = fibThread1.getAnswer();
            long l1 = fibThread2.getAnswer();
            answer = l1 + l2;
        }
    }
}
