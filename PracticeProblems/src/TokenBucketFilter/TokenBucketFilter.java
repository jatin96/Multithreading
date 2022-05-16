package TokenBucketFilter;

public class TokenBucketFilter {
    private int MAX_TOKENS;
    private long currentTokens;
    private long lastRequestTime;

    public TokenBucketFilter(int MAX_TOKENS) {
        this.MAX_TOKENS = MAX_TOKENS;
        currentTokens = 0;
        lastRequestTime = System.currentTimeMillis();
    }

    synchronized void getToken() throws InterruptedException {
        currentTokens += (System.currentTimeMillis() - lastRequestTime) / 1000;

        if (currentTokens > MAX_TOKENS)
            currentTokens = MAX_TOKENS;

        if (currentTokens == 0)
            Thread.sleep(1000);
        else
            currentTokens--;

        lastRequestTime = System.currentTimeMillis();

        System.out.println("Granting " + Thread.currentThread().getName() + " token at " + (lastRequestTime/1000));
    }
}
