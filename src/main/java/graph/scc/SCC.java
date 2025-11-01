package graph.scc;
import java.util.*;
public class SCC {
    private final int n;
    private final List<List<Integer>> g;
    private int index = 0;
    private int[] idx;
    private int[] low;
    private boolean[] onStack;
    private Deque<Integer> stack = new ArrayDeque<>();
    private List<List<Integer>> sccs = new ArrayList<>();
    public SCC(int n, List<List<Integer>> g) {
        this.n = n;
        this.g = g;
        idx = new int[n];
        Arrays.fill(idx, -1);
        low = new int[n];
        onStack = new boolean[n];
        for (int v = 0; v < n; v++) if (idx[v] == -1) dfs(v);
    }
    private void dfs(int v) {
        idx[v] = index;
        low[v] = index;
        index++;
        stack.push(v);
        onStack[v] = true;
        for (int w : g.get(v)) {
            if (idx[w] == -1) {
                dfs(w);
                low[v] = Math.min(low[v], low[w]);
            } else if (onStack[w]) {
                low[v] = Math.min(low[v], idx[w]);
            }
        }
        if (low[v] == idx[v]) {
            List<Integer> comp = new ArrayList<>();
            while (true) {
                int w = stack.pop();
                onStack[w] = false;
                comp.add(w);
                if (w == v) break;
            }
            sccs.add(comp);
        }
    }
    public List<List<Integer>> getSccs() { return sccs; }
    public int[] assignComponent() {
        int m = sccs.size();
        int[] comp = new int[n];
        for (int i = 0; i < m; i++) for (int v : sccs.get(i)) comp[v] = i;
        return comp;
    }
}