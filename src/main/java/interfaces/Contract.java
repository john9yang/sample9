package interfaces;

import java.util.ArrayList;
import java.util.List;

public interface Contract {

    List<String> parseMetaData(Class<?> targetType);

    abstract class BaseContract implements Contract{
        @Override
        public List<String> parseMetaData(Class<?> targetType){
            return new ArrayList<>();
        }
    }

    class Default extends BaseContract{

    }
}
