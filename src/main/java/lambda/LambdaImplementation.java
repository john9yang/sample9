package lambda;

import java.util.function.Consumer;

public class LambdaImplementation {
    public static void main(String[] args) {
        // Anonymous class,multiple instances
        for( int i=0;i<5;i++ ){
            Consumer<String> myPrinter1 = new Consumer<String>() {
                @Override
                public void accept(String msg) {
                    System.out.println("Consuming "+msg);
                }
            };
            myPrinter1.accept(myPrinter1.toString());
        }

        //non-capture lambda,one instance
        System.out.println("Non-capturing lambda:");
        for( int i=0;i<5;i++ ){
            Consumer<String> myPrinter2 = msg -> System.out.println("Consuming "+msg);
            myPrinter2.accept(myPrinter2.toString());
        }

        //Constant-capturing lambda,one instance
        System.out.println("Constant-capturing lambda");
        final int secret = 42;
        for( int i=0;i<5;i++ ){
            Consumer<String> myPrinter3 = msg -> System.out.println("Consuming "+msg+", "+secret);
            myPrinter3.accept(myPrinter3.toString());
        }

        (new LambdaImplementation()).foo();
    }

    private int id = 1;
    public void foo(){
        System.out.println("Instance-capturing lambda:");
        for (int i = 0; i < 5; i++) {
            //this-capturing lambda ,many instance!
            Consumer<String> myPrinter4 = msg -> System.out.println("Consuming "+msg+", "+id);
            myPrinter4.accept(myPrinter4.toString());
        }
    }
}
