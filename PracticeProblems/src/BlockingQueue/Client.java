package BlockingQueue;

public class Client {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> queue = new BlockingQueue<Integer>(5);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 50; i++) {
                        queue.enqueue(i);
                        System.out.println("enqueued : " + i + " current size: " + queue.getSize());
                    }
                } catch (InterruptedException e) {

                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("thread 2 dequeued : " + queue.dequeue() + " current size: " + queue.getSize());
                    }
                } catch (InterruptedException e) {}
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 25; i++) {
                        System.out.println("thread 3 dequeued : " + queue.dequeue() + " current size: " + queue.getSize());
                    }
                } catch (InterruptedException e) {}
            }
        });

        thread1.start();
        Thread.sleep(4000);
        thread2.start();

        thread2.join();

        thread3.start();
        thread1.join();
        thread3.join();
    }
}
