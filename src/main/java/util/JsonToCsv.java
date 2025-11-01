package util;
import com.fasterxml.jackson.databind.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonToCsv {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.err.println("Usage: JsonToCsv <path/to/dataset.json>");
            System.exit(1);
        }
        Path p = Paths.get(args[0]);
        ObjectMapper om = new ObjectMapper();
        Map<?,?> data = om.readValue(p.toFile(), Map.class);
        String base = p.getFileName().toString().replace(".json","");
        Path dir = p.getParent();
        List<Map<String,Object>> edges = (List)data.get("edges");
        try (BufferedWriter bw = Files.newBufferedWriter(dir.resolve("edges_" + base + ".csv"))) {
            bw.write("u,v,w"); bw.newLine();
            for (Map<String,Object> e : edges) {
                bw.write(String.format("%s,%s,%s", e.get("u"), e.get("v"), e.get("w"))); bw.newLine();
            }
        }
        try (BufferedWriter bw = Files.newBufferedWriter(dir.resolve("meta_" + base + ".csv"))) {
            bw.write("n,source,weight_model"); bw.newLine();
            bw.write(String.format("%s,%s,%s", data.get("n"), data.get("source"), data.get("weight_model"))); bw.newLine();
        }
        System.out.println("Wrote CSV for " + base);
    }
}