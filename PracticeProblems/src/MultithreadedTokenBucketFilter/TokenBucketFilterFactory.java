package MultithreadedTokenBucketFilter;

public class TokenBucketFilterFactory {

    private TokenBucketFilterFactory() {
    }

    static public TokenBucketFilter getTokenBucketFilter(int capacity) {
        MultithreadedTokenBucketFilter tokenBucketFilter = new MultithreadedTokenBucketFilter(capacity);
        tokenBucketFilter.initialize();
        return tokenBucketFilter;
    }
}
