package edu.hw8.task2;

import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadPool {
    private final Thread[] pool;

    private FixedThreadPool(int n) {
        this.pool=new Thread[n];
    }

    public static void main(String[] args) {
        FixedThreadPool threads=FixedThreadPool.create(4);
        int result=0;
        int n=4;
        FibThread fibThread=new FibThread(n,threads);
        FibThread fibThread2=new FibThread(5,threads);
        threads.execute(fibThread);
        threads.execute(fibThread2);
//        System.out.println(fibThread.getAnswer());
    }
    public static FixedThreadPool create(int n){
        return new FixedThreadPool(n);
    }
    @Override
    public void start() {
    }

    @Override
    public void execute(Runnable runnable) {
        boolean flag=false;
        while(!flag){
            for (Thread i:pool) {
                System.out.println("123");
                if(i==null||!i.isAlive()){
                    flag=true;
                    i=new Thread(runnable);
                    i.start();
                    break;
                }
            }
        }
    }

    @Override
    public void close() throws Exception {

    }
}
