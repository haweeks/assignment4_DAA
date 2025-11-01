import org.junit.jupiter.api.Test;
import graph.topo.Topo;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {
    @Test
    public void testSimpleDAG() {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        g.put(0, new HashSet<>(Arrays.asList(1,2)));
        g.put(1, new HashSet<>(Arrays.asList(3)));
        g.put(2, new HashSet<>(Arrays.asList(3)));
        List<Integer> order = Topo.kahn(4, g);
        assertEquals(4, order.size());
    }
    @Test
    public void testCycleDetection() {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        g.put(0, new HashSet<>(Arrays.asList(1)));
        g.put(1, new HashSet<>(Arrays.asList(0)));
        List<Integer> order = Topo.kahn(2, g);
        assertTrue(order.size() < 2);
    }
}
