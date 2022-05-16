package MultithreadedTokenBucketFilter;

public class MultithreadedTokenBucketFilter extends TokenBucketFilter {
    private int MAX_TOKENS;
    private long currentTokens;
    private int timeInterval;

    public MultithreadedTokenBucketFilter(int MAX_TOKENS) {
        this.MAX_TOKENS = MAX_TOKENS;
        this.currentTokens = 0;
        this.timeInterval = 1000;
    }

    void initialize() {
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                backgroundProcess();
            }
        });

        daemonThread.setDaemon(true);
        daemonThread.start();
    }

    private void backgroundProcess() {
        while(true) {
            synchronized (this) {
                if (currentTokens < MAX_TOKENS)
                    currentTokens++;

                this.notifyAll();
            }
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {};
        }
    }

    public void getTokens() throws InterruptedException {
        synchronized (this) {
            while (currentTokens == 0) {
                this.wait();
            }
            currentTokens--;
        }

        System.out.println(
                "Granting " + Thread.currentThread().getName() + " token at " + System.currentTimeMillis() / 1000);
    }
}
