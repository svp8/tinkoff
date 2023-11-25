package edu.hw8.task2;

public class FibThread implements Runnable {
    private int n;
    private int answer;

    public int getAnswer() {
        return answer;
    }

    private FixedThreadPool fixedThreadPool;

    public FibThread(int n, FixedThreadPool fixedThreadPool) {
        this.n = n;
        this.fixedThreadPool = fixedThreadPool;
    }

    @Override
    public void run() {
        if(n==1){
            answer=0;
        }
        else if (n <= 2) {
            answer = 1;
        } else {
//            FibThread x = new FibThread(n - 1, fixedThreadPool);
//            FibThread y = new FibThread(n - 2, fixedThreadPool);
//            fixedThreadPool.execute(x);
//            fixedThreadPool.execute(y);
//            answer = x.getAnswer() + y.getAnswer();
            // Evaluate each term in the series until the nthNum
            int l2=0;
            int l1=1;
            for (int i = 3; i <= n; i++){
                int temp=l1;
                l1 = l1+l2;
                l2=temp;
            }
            answer=l1;
            System.out.println(answer);
        }
    }
}
