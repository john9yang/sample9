package concurrent.observable;

@FunctionalInterface
public interface Task<T> {

    T call();
}
