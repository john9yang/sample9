package lambda;

import java.util.function.Consumer;

public class LambdaInference {
    public static void main(String[] args) {
        //identify the FI
        //standard syntax
        Consumer<String> c1 = msg -> System.out.println(msg.length());

        //compile-time error : not enough info
        //Object x1 = msg -> System.out.println(msg.length());

        //compile-time error : not enough info
        //Object x2 = (String msg)  -> System.out.println(msg.length());

        //OK: cast added
        Object x3 = (Consumer<String>)((String msg) -> System.out.println(msg.length()));
    }
}