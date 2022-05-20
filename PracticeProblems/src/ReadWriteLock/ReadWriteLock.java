package ReadWriteLock;

public class ReadWriteLock {
    int readLocks = 0;
    boolean isWriteLock = false;
    public synchronized void acquireReadLock() throws InterruptedException {
        while (isWriteLock) {
            this.wait();
        }

        readLocks++;
    }
    public synchronized void releaseReadLock() {
        readLocks--;
        notify();
    }
    public synchronized void acquireWriteLock() throws InterruptedException {
        while (isWriteLock || readLocks != 0) {
            this.wait();
        }

        isWriteLock = true;
    }
    public synchronized void releaseWriteLock() {
        isWriteLock = false;
        notify();
    }
}
