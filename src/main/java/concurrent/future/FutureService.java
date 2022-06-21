package concurrent.future;

public interface FutureService<IN, OUT> {

    Future<?> submit(Runnable runnable);

    Future<OUT> submit(Task<IN, OUT> task, IN input);

    Future<OUT> submit(Task<IN, OUT> task, IN input, CallBack<OUT> callBack);

    static <T, R> FutureService<T, R> newService() {
        return new FutureServiceImpl<>();
    }
}
