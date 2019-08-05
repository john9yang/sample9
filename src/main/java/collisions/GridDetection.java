package collisions;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GridDetection {
    public static final GridSpec gridSpec = new GridSpec();

    static {
        //size of the grid
        gridSpec.rows = 4;
        gridSpec.cols = 6;
        gridSpec.rowHeight = CountingTest.YRES / gridSpec.rows;
        gridSpec.colWidth = CountingTest.XRES / gridSpec.cols;
    }

    public static List<List<Box>> broadPhase( Collection<Box> c,GridSpec gridSpec ){
        return c.parallelStream().collect(GridCollector.of(gridSpec));
    }

    /**
     * BUilds the set of all collisions
     * Based on broad phase / narrow phase
     * Duplicate removal is performed by the final "toSet" collector
     *
     * @param c the collection  of boxes to be checked for collisions
     * @return  the set of all collisions
     */
    public static Set<Collision> allCollisions(Collection<Box> c ){
        //Broad phase
        List<List<Box>> gridCells = broadPhase(c,GridDetection.gridSpec);
        //Narrow phase
        return gridCells.stream()    //stream of List<Box>
                        .flatMap(
                                (List<Box> l) -> l.stream()
                                                  .collect(CollisionListCollector.make())// List<Collision>
                                                  .stream()            // stream of collisons
                        )  //stream of collisions
                        .collect(Collectors.toSet());
    }
}
