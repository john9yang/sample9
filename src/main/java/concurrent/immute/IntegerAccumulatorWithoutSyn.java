package concurrent.immute;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class IntegerAccumulatorWithoutSyn {
    private int init;

    public IntegerAccumulatorWithoutSyn(int init){
        this.init = init;
    }

    public IntegerAccumulatorWithoutSyn(IntegerAccumulatorWithoutSyn accumulator, int init){
        this.init = accumulator.getValue() + init;
    }

    public IntegerAccumulatorWithoutSyn add(int i){
        return new IntegerAccumulatorWithoutSyn(this,i);
    }

    public int getValue(){
        return this.init;
    }

    public static void main(String[] args) {
        IntegerAccumulatorWithoutSyn accumulator = new IntegerAccumulatorWithoutSyn(0);
        IntStream.range(0,5).forEach(i -> new Thread(()->{
            int inc = 0;
            while(true){
                int oldValue = accumulator.getValue();
                int result = accumulator.add(inc).getValue();
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
