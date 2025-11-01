import org.junit.jupiter.api.Test;
import graph.scc.SCC;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TarjanSCCTest {
    @Test
    public void testSimpleGraph() {
        int n = 3;
        List<List<Integer>> g = new ArrayList<>();
        for (int i=0;i<n;i++) g.add(new ArrayList<>());
        g.get(0).add(1); g.get(1).add(2); g.get(2).add(0);
        SCC s = new SCC(n, g);
        assertEquals(1, s.getSccs().size());
    }
    @Test
    public void testCondensationGraph() {
        int n = 4;
        List<List<Integer>> g = new ArrayList<>();
        for (int i=0;i<n;i++) g.add(new ArrayList<>());
        g.get(0).add(1); g.get(1).add(0);
        g.get(2).add(3);
        SCC s = new SCC(n, g);
        assertTrue(s.getSccs().size() >= 2);
    }
}
