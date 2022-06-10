package concurrent.immute;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulator {
    private int init;

    public IntegerAccumulator(int init){
        this.init = init;
    }

    public int add(int i){
        this.init += i;
        return this.init;
    }

    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulator accumulator = new IntegerAccumulator(0);
        IntStream.range(0,5).forEach(i -> new Thread(()->{
            int inc = 0;
            while(true){
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc);
                System.out.println("Normal:"+oldValue + "+" + inc + "="+ result);
                if(inc + oldValue != result){
                    System.err.println("ERROR:" + oldValue + "+" + inc + "="+ result);
                    System.exit(-1);
                }
                inc++;
            }
        }).start());
    }

    private static void slowly(){
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}