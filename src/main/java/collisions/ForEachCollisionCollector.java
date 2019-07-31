package collisions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ForEachCollisionCollector {

    private Consumer<Collision> handler;
    //store previously seen boxes
    private List<Box> cache  = new ArrayList<>();

    public ForEachCollisionCollector(Consumer<Collision> handler){
        this.handler = handler;
    }

    //process a new box
    public void update(Box b){
        //for every previously seen box 'a'
        for( Box a: cache ){
            //check if 'a' overlaps with new box 'b'
            if ( Box.areOverlapping(a,b) )
                handler.accept(new Collision(a,b));
        }

        cache.add(b);
    }

    //Merge another Collector with this one
    public void merge(ForEachCollisionCollector other){
        //for every pair of boxes in the two caches
        for( Box a :cache )
            for( Box b : other.cache )
                if ( Box.areOverlapping(a,b) )
                    handler.accept(new Collision(a,b));

        //merge the two caches
        cache.addAll(other.cache);
    }
}
