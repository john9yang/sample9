package collisions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

public class CollisionSetCollector {
    private List<Box> cache = new ArrayList<>();
    private Set<Collision> result = new HashSet<>();

    public void update(Box b) {
        for (Box a: cache)
            if (Box.areOverlapping(a, b))
                result.add(new Collision(a, b));
        cache.add(b);
    }

    public CollisionSetCollector merge(CollisionSetCollector other) {
        result.addAll(other.result);
        for (Box a: cache)
            for (Box b: other.cache)
                if (Box.areOverlapping(a, b))
                    result.add(new Collision(a, b));
        cache.addAll(other.cache);
        return this;
    }

    public Set<Collision> finish() {
        return result;
    }

    public static Collector<Box,?,Set<Collision>> make(){
        return Collector.<Box,CollisionSetCollector,Set<Collision>>of(
                CollisionSetCollector::new,
                CollisionSetCollector::update,
                CollisionSetCollector::merge,
                CollisionSetCollector::finish,
                Collector.Characteristics.UNORDERED
        );
    }
}
