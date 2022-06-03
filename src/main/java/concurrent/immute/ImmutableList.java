package concurrent.immute;

import java.util.Collections;
import java.util.List;

public final class ImmutableList {
    private final List<String> list;

    public ImmutableList(List<String> list){
        this.list = list;
    }

    public List<String> getList(){
        return Collections.unmodifiableList(this.list);
    }
}
