package concurrent.eda;

public interface Channel <E extends Message>{

    void dispatch(E message);
}
