import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class EdgeCaseTest {
    @Test
    public void testEmptyGraph() {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        List<Integer> order = graph.topo.Topo.kahn(0, g);
        assertEquals(0, order.size());
    }
    @Test
    public void testSingleVertex() {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        List<Integer> order = graph.topo.Topo.kahn(1, g);
        assertEquals(1, order.size());
    }
}
