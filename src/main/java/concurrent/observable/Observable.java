package concurrent.observable;

public interface Observable {

    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }

    Cycle getCycle();

    //定义启动线程的方法，主要作用是屏蔽Thread的其他方法
    void start();

    //同上
    void interrupt();
}
