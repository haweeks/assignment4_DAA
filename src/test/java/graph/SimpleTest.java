package graph;
import graph.scc.SCC;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SimpleTest {
    @Test
    public void testSccSimple() {
        int n = 3;
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        g.get(0).add(1); g.get(1).add(2); g.get(2).add(0);
        SCC s = new SCC(n, g);
        assertEquals(1, s.getSccs().size());
    }
}