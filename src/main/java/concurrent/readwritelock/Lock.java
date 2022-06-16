package concurrent.readwritelock;

public interface Lock {

    void lock() throws InterruptedException;

    void unlock();
}
