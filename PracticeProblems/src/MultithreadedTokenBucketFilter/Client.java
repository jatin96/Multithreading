package MultithreadedTokenBucketFilter;

import java.util.HashSet;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws Exception {
        Set<Thread> threads = new HashSet<>();
        TokenBucketFilter tokenBucketFilter = TokenBucketFilterFactory.getTokenBucketFilter(1);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tokenBucketFilter.getTokens();
                    } catch (InterruptedException e) {}
                }
            });
            thread.setName("Thread_" + (i + 1));
            threads.add(thread);
        }

        for (Thread thread : threads) thread.start();

        for (Thread thread : threads) thread.join();
    }
}
