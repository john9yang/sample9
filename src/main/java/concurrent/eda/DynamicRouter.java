package concurrent.eda;

public interface DynamicRouter <E extends Message>{

    void register(Class<? extends E> messageType,Channel<? extends E> channel);

    void dispatch(E message);
}
