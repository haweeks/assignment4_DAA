package graph.dagsp;
import java.util.*;
public class DAGSP {
    public static Map<Integer, Long> shortest(int nodes, Map<Integer, List<int[]>> g, int src, List<Integer> topo) {
        Map<Integer, Long> dist = new HashMap<>();
        for (int i = 0; i < nodes; i++) dist.put(i, Long.MAX_VALUE/4);
        dist.put(src, 0L);
        for (int u : topo) {
            long du = dist.getOrDefault(u, Long.MAX_VALUE/4);
            if (du < Long.MAX_VALUE/4 && g.containsKey(u)) {
                for (int[] e : g.get(u)) {
                    int v = e[0], w = e[1];
                    long nd = du + w;
                    if (nd < dist.get(v)) dist.put(v, nd);
                }
            }
        }
        return dist;
    }
    public static Map<Integer, Long> longest(int nodes, Map<Integer, List<int[]>> g, int src, List<Integer> topo) {
        Map<Integer, Long> dist = new HashMap<>();
        for (int i = 0; i < nodes; i++) dist.put(i, Long.MIN_VALUE/4);
        dist.put(src, 0L);
        for (int u : topo) {
            long du = dist.getOrDefault(u, Long.MIN_VALUE/4);
            if (du > Long.MIN_VALUE/4 && g.containsKey(u)) {
                for (int[] e : g.get(u)) {
                    int v = e[0], w = e[1];
                    long nd = du + w;
                    if (nd > dist.get(v)) dist.put(v, nd);
                }
            }
        }
        return dist;
    }
}