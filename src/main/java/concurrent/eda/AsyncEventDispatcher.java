package concurrent.eda;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncEventDispatcher implements DynamicRouter<Event>{

    private final Map<Class<? extends Message>, AsyncChannel> routerTable;

    public AsyncEventDispatcher(){
        this.routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void register(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        if ( !(channel instanceof AsyncChannel)){
            throw new IllegalArgumentException("The channel must be AsyncChannel type.");
        }
        this.routerTable.put(messageType,(AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
         if(routerTable.containsKey(message.getType())){
             routerTable.get(message.getType()).dispatch(message);
         }
         else{
             throw new MessageMatcherException("Can't match the channel for ["+message.getType()+"] type");
         }
    }

    public void shutdown(){
        routerTable.values().forEach(AsyncChannel::stop);
    }
}
