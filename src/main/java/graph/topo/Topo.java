package graph.topo;
import java.util.*;
public class Topo {
    public static List<Integer> kahn(int nodes, Map<Integer, Set<Integer>> g) {
        int m = nodes;
        int[] indeg = new int[m];
        for (int u : g.keySet()) for (int v : g.get(u)) indeg[v]++;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            if (g.containsKey(u)) for (int v : g.get(u)) {
                indeg[v]--;
                if (indeg[v] == 0) q.add(v);
            }
        }
        return order;
    }
}