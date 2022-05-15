package BlockingQueue;

public class BlockingQueue<T> {
    T[] queue;
    int capacity;
    int size;
    int head;
    int tail;

    Object lock = new Object();

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new Object[capacity];
        size = 0;
        head = 0;
        tail = 0;
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized (lock) {
            while (size == capacity) {
                lock.wait();
            }

            if (tail == capacity)
                tail = 0;

            queue[tail] = item;
            size++;
            tail++;
            lock.notifyAll();
        }
    }

    public T dequeue() throws InterruptedException {
        T item = null;
        synchronized (lock) {
            while (size == 0) {
                lock.wait();
            }

            if (head == capacity)
                head = 0;

            item = queue[head];
            queue[head] = null;
            head++;
            size--;
            lock.notifyAll();
        }

        return item;
    }

    public int getSize() {
        return size;
    }

}
