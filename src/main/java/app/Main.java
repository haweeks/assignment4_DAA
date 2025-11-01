package app;
import graph.scc.SCC;
import graph.topo.Topo;
import graph.dagsp.DAGSP;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import com.fasterxml.jackson.databind.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String path = args.length>0?args[0]:"data/tasks.json";
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        ObjectMapper om = new ObjectMapper();
        Map<?,?> data = om.readValue(bytes, Map.class);
        int n = (Integer)data.get("n");
        List<Map<String,Object>> edges = (List)data.get("edges");
        int source = (Integer)data.get("source");
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) g.add(new ArrayList<>());
        Map<String,Integer> weight = new HashMap<>();
        for (Map<String,Object> e : edges) {
            int u = (Integer)e.get("u");
            int v = (Integer)e.get("v");
            int w = (Integer)e.get("w");
            g.get(u).add(v);
            weight.put(u+"_"+v, w);
        }
        long t0 = System.nanoTime();
        SCC scc = new SCC(n, g);
        long t1 = System.nanoTime();
        List<List<Integer>> comps = scc.getSccs();
        int[] compId = scc.assignComponent();
        int m = comps.size();
        Map<Integer, Set<Integer>> cg = new HashMap<>();
        for (Map.Entry<String,Integer> en : weight.entrySet()) {
            String k = en.getKey();
            String[] parts = k.split("_");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int cu = compId[u], cv = compId[v];
            if (cu != cv) {
                cg.computeIfAbsent(cu, x->new HashSet<>()).add(cv);
            }
        }
        long t2 = System.nanoTime();
        List<Integer> topo = Topo.kahn(m, cg);
        long t3 = System.nanoTime();
        Map<Integer, List<int[]>> dag = new HashMap<>();
        for (Map.Entry<String,Integer> en : weight.entrySet()) {
            String k = en.getKey();
            String[] parts = k.split("_");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            int cu = compId[u], cv = compId[v];
            int w = en.getValue();
            if (cu != cv) dag.computeIfAbsent(cu, x->new ArrayList<>()).add(new int[]{cv,w});
        }
        long t4 = System.nanoTime();
        int srcComp = compId[source];
        List<Integer> topoForAll = new ArrayList<>(topo);
        for (int i = 0; i < m; i++) if (!topoForAll.contains(i)) topoForAll.add(i);
        Map<Integer, Long> dist = DAGSP.shortest(m, dag, srcComp, topoForAll);
        Map<Integer, Long> ldist = DAGSP.longest(m, dag, srcComp, topoForAll);
        long t5 = System.nanoTime();
        System.out.println("SCCs:"); for (int i = 0; i < comps.size(); i++) System.out.println(i+": "+comps.get(i)+" size="+comps.get(i).size());
        System.out.println("Condensation edges:"); for (int u : cg.keySet()) System.out.println(u+" -> "+cg.get(u));
        System.out.println("Topological order:"); System.out.println(topo);
        System.out.println("Shortest distances:"); System.out.println(dist);
        System.out.println("Longest distances:"); System.out.println(ldist);
        System.out.println("Timings (ns): scc="+(t1-t0)+" topo="+(t3-t2)+" dagsp="+(t5-t4));
    }
}