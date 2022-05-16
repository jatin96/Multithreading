package MultithreadedTokenBucketFilter;

public abstract class TokenBucketFilter {
    public abstract void getTokens() throws InterruptedException;
}
