package collisions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;

import static collisions.BasicDetection.*;

public class CountingTest {
    public static final int XRES = 1920, YRES = 1080, MAXX = 200, MAXY = 200;

    //generate random boxes
    public static List<Box> makeRandom( int n ){
        List<Box> result = new ArrayList<>(n);
        for( int i=0; i<n; i++ )
            result.add(Box.newRandom(XRES,YRES,MAXX,MAXY));

        return result;
    }

    public static void main(String[] args) {
        final int TEST_SIZE = 5_000;
        List<Box> world = makeRandom( TEST_SIZE );

        System.out.println("Counting the number of collisions:");

        final LongAdder atomicCounter = new LongAdder();
        Consumer<Collision> action = c -> atomicCounter.increment();

        System.out.println("Standard imperative");
        //warm up
        forEachCollision1(world,action);
        atomicCounter.reset();
        //Timed run
        Time.timeIt( () -> {foreachCollision2( world,action );} );
        System.out.println("# of collision: "+atomicCounter);

        System.out.println("Standard imperative (no duplicates)");
        //warm up
        foreachCollision2(world,action);
        atomicCounter.reset();
        //Timed run
        Time.timeIt(()->foreachCollision2(world,action));
        System.out.println("# of collision: "+atomicCounter);

        System.out.println("Parallel stream (ForEachCollisionCollector)");
        //Warm up
        forEachCollision3(world,action);
        atomicCounter.reset();
        //Timed run
        Time.timeIt(()->forEachCollision3(world,action));
        System.out.println("# of collision: "+atomicCounter);
    }
}
