package lambda;

import java.util.function.Supplier;

public class MethodReferences {
    interface ThreadSupplier{
        Thread giveMeThread();
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        //static method
        Supplier<Thread> s1 = Thread::currentThread;

        //nothing special about 'supplier'
        ThreadSupplier ts = Thread::currentThread;
    }
}
