package concurrent.eda;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher implements DynamicRouter<Message>{

    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher(){
        this.routerTable = new HashMap<>();
    }

    @Override
    public void register(Class<? extends Message> messageType, Channel<? extends Message> channel) {
        this.routerTable.put(messageType,channel);
    }

    @Override
    public void dispatch(Message message) {
         if(routerTable.containsKey(message.getType())){
             routerTable.get(message.getType()).dispatch(message);
         }
         else{
             throw new MessageMatcherException("Can't match the channel for ["+message.getType()+"] type");
         }
    }
}
