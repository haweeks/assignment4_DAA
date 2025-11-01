# Assignment 4 
**Student:** *Zarina Kossaman*  
**Group:** *SE-2429*

---

## 1. Data Summary  

| Category | Nodes (n) | Description | Variants | Weight model |
|-----------|------------|--------------|-----------|---------------|
| Small | 6–10 | Simple cases, 1–2 cycles or pure DAG | 3 | edge |
| Medium | 10–20 | Mixed structures, several SCCs | 3 | edge |
| Large | 20–50 | Performance and timing tests | 3 | edge |

All datasets are stored in `/data/` as JSON files (`dataset_1.json` … `dataset_9.json`).  
Each graph is **directed**, with weights stored on edges.  
Both cyclic and acyclic examples are included to evaluate SCC detection and DAG path algorithms.

---

## 2. Results  

| Algorithm | Avg. Execution Time (ns) | Typical Operation Count | Observation |
|------------|--------------------------|--------------------------|--------------|
| **SCC (Tarjan)** | 22 166 | 13–200 (DFS + edge checks) | Slowest step; recursion depth grows with graph density |
| **Topological Sort (Kahn)** | 2 995 | 5–100 (queue ops) | Efficient; scales linearly with condensed nodes |
| **DAG Shortest (sum distances)** | 1 584 | 1–50 (relaxations) | Very fast; minimal per-edge overhead |
| **DAG Longest (critical path)** | 1 214 | 1–50 (relaxations) | Similar speed to shortest path |

---

## 3. Analysis  

### SCC (Tarjan)  
- Main bottleneck for dense or cyclic graphs due to repeated DFS traversals.  
- More SCCs appear as edge density rises, increasing condensation size and recursion depth.  

### Topological Sort (Kahn)  
- Almost linear performance relative to number of SCCs.  
- Minimal overhead; ideal for medium-size DAGs.  

### DAG Shortest / Longest Paths  
- Depend on topological order; stable runtime across datasets.  
- Critical path length remains moderate (≈20–30 total weight units).  

### Structural Effects  
- **Dense graphs:** more SCCs → slower SCC and condensation stages.  
- **Sparse DAGs:** fewer SCCs → faster topological and path computation.

---

## 4. Conclusions  

- **SCC (Tarjan):** use when graphs may contain cycles; required before DAG analysis.  
- **Topological Sort (Kahn):** for dependency sequencing in acyclic components.  
- **DAG-SP (Shortest/Longest):** for task scheduling and critical path detection after SCC compression.  

For large graphs, SCC dominates total time; simplifying dense connections improves efficiency.  
Together, Tarjan → Kahn → DAG-SP provide an effective pipeline for dependency-based scheduling.

## 5. References
https://lms.astanait.edu.kz/
https://lms.astanait.edu.kz/mod/resource/view.php?id=18813
