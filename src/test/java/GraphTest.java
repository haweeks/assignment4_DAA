import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GraphTest {
    @Test
    public void testAddEdge() {
        List<List<Integer>> g = new ArrayList<>();
        for (int i=0;i<2;i++) g.add(new ArrayList<>());
        g.get(0).add(1);
        assertEquals(1, g.get(0).size());
    }
}
