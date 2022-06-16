package concurrent.readwritelock;

public interface ReadWriteLock {

    Lock readLock();

    Lock writeLock();

    //获取当前有多少线程正在执行写操作
    int getWritingWriters();

    //获取当前有多少线程正在等待获取写入锁
    int getWaitingWriters();

    //获取当前有多少线程正在等待获取reader锁
    int getReadingReaders();

    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }
}
