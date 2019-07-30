package collisions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionListFunctionalCollector {
    private final List<Box> cache;
    private final List<Collision> collisions;

    public CollisionListFunctionalCollector() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    private CollisionListFunctionalCollector(List<Box> cache, List<Collision> collisions)
    {
        this.cache = cache;
        this.collisions = collisions;
    }

    public CollisionListFunctionalCollector accumulate(Box b){
        List<Collision> newCollisions = cache.stream()
                                        .filter(a -> Box.areOverlapping(a,b))
                                        .map(a -> new Collision(a,b))
                                        .collect(Collectors.toList());

        newCollisions.addAll(collisions);
        List<Box> newCache = new ArrayList<>(cache);
        newCache.add(b);

        return new CollisionListFunctionalCollector(newCache,newCollisions);
    }

    public CollisionListFunctionalCollector combine(CollisionListFunctionalCollector other){
        List<Collision> newCollistions = cache.stream().flatMap(
                a ->
                     other.cache.stream()
                                        .filter(b -> Box.areOverlapping(a,b))
                                        .map(b -> new Collision(a,b))
        ).collect(Collectors.toList());

        List<Box> newCache = new ArrayList<>(cache);
        newCache.addAll(other.cache);
        newCollistions.addAll(collisions);
        newCollistions.addAll(other.collisions);

        return new CollisionListFunctionalCollector(newCache,newCollistions);
    }

    public List<Collision> finish(){
        return collisions;
    }
}
