package concurrent.eda;

public interface Message {

    Class<? extends Message> getType();
}
