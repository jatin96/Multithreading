package TokenBucketFilter;

import java.util.HashSet;
import java.util.Set;

public class Client {
    public static void main(String[] args) throws Exception {
        Set<Thread> allThreads = new HashSet<>();
        TokenBucketFilter tokenBucketFilter = new TokenBucketFilter(1);

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        tokenBucketFilter.getToken();
                    } catch (InterruptedException e) { }
                }
            });

            thread.setName("Thread_" + (i + 1));
            allThreads.add(thread);
        }

        for (Thread thread : allThreads) thread.start();

        for (Thread thread : allThreads) thread.join();
    }
}
