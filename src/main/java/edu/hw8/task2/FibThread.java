package edu.hw8.task2;

import java.util.concurrent.locks.ReentrantLock;

public class FibThread implements Runnable {
    public static final int THREE = 3;
    private final int n;
    private long answer;

    public long getAnswer() {
        return answer;
    }

    public FibThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        ReentrantLock lock = new ReentrantLock();
        if (n == 1) {
            answer = 0;
        } else if (n == 2) {
            answer = 1;
        } else {
            long l2 = 0;
            long l1 = 1;
            for (int i = THREE; i <= n; i++) {
                long temp = l1;
                l1 = l1 + l2;
                l2 = temp;
            }
            answer = l1;
        }
    }
}
