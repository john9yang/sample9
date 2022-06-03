package concurrent.eda;

import java.util.concurrent.TimeUnit;

public class AsyncEventDispatcherExample {

    static class AsyncInputEventHandler extends AsyncChannel{
        private final AsyncEventDispatcher dispatcher;

        public AsyncInputEventHandler(AsyncEventDispatcher dispatcher){
            this.dispatcher = dispatcher;
        }
        @Override
        protected void handle(Event message) {
            EventDispatcherExample.InputEvent inputEvent = (EventDispatcherExample.InputEvent) message;
            System.out.printf("X:%d,Y:%d\n",inputEvent.getX(),inputEvent.getY());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            int result = inputEvent.getX() + inputEvent.getY();
            dispatcher.dispatch(new EventDispatcherExample.ResultEvent(result));
        }
    }

    static class AsyncResultEventHandler extends AsyncChannel{

        @Override
        protected void handle(Event message) {
            EventDispatcherExample.ResultEvent resultEvent = (EventDispatcherExample.ResultEvent) message;

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("The result is:" + resultEvent.getResult());
        }
    }

    public static void main(String[] args) {
        AsyncEventDispatcher dispatcher = new AsyncEventDispatcher();
        dispatcher.register(EventDispatcherExample.InputEvent.class, new AsyncInputEventHandler(dispatcher));
        dispatcher.register(EventDispatcherExample.ResultEvent.class,new AsyncResultEventHandler());

        dispatcher.dispatch(new EventDispatcherExample.InputEvent(3,4));
    }
}
