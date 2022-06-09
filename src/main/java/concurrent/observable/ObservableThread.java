package concurrent.observable;

import java.util.concurrent.TimeUnit;

public class ObservableThread<T> extends Thread implements Observable{

    private final TaskLifecycle<T> lifecycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task){
        super();

        if (task == null)
            throw new IllegalArgumentException("The task is required");

        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public void run(){
        this.update(Cycle.STARTED, null, null);

        this.update(Cycle.RUNNING, null, null);
        try {
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        }
        catch (Exception e){
            this.update(Cycle.ERROR, null, e);
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    private void update(Cycle cycle, T result, Exception e){
        this.cycle = cycle;

        if (lifecycle == null)
            return;

        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        }
        catch (Exception ex){
            if (cycle == Cycle.ERROR){
                throw ex;
            }
        }
    }

    public static void main(String[] args) {
        Observable observableThread = new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("finished done.");
            return null;
        });

        observableThread.start();
        System.out.println("after thread start");

        final TaskLifecycle<String> lifecycle1 = new TaskLifecycle.EmptyLifecycle<>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };

        Observable observableThread1 = new ObservableThread<>(lifecycle1, () ->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("finished done.");
            return "Observe thread";
        });

        observableThread1.start();
    }
}
