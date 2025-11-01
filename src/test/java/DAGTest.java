import org.junit.jupiter.api.Test;
import graph.dagsp.DAGSP;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGTest {
    @Test
    public void testShortestPath() {
        int nodes = 3;
        Map<Integer, List<int[]>> g = new HashMap<>();
        g.put(0, Arrays.asList(new int[]{1,2}, new int[]{2,5}));
        g.put(1, Arrays.asList(new int[]{2,1}));
        List<Integer> topo = Arrays.asList(0,1,2);
        Map<Integer, Long> dist = DAGSP.shortest(nodes, g, 0, topo);
        assertEquals(0L, dist.get(0));
        assertEquals(2L, dist.get(1));
    }
    @Test
    public void testLongestPath() {
        int nodes = 3;
        Map<Integer, List<int[]>> g = new HashMap<>();
        g.put(0, Arrays.asList(new int[]{1,2}, new int[]{2,5}));
        g.put(1, Arrays.asList(new int[]{2,10}));
        List<Integer> topo = Arrays.asList(0,1,2);
        Map<Integer, Long> dist = DAGSP.longest(nodes, g, 0, topo);
        assertTrue(dist.get(2) >= 12);
    }
}
