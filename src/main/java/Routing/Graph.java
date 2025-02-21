package Routing;
import java.util.*;

public class Graph {
    public static class Edge {
        String target;
        int weight;

        public Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    private final Map<String, List<Edge>> adjList = new HashMap<>();

    public void addNode(String node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        adjList.get(from).add(new Edge(to, weight));
        adjList.get(to).add(new Edge(from, weight));
    }

    public List<Edge> getEdges(String node) {
        return adjList.getOrDefault(node, Collections.emptyList());
    }

    public Set<String> getNodes() {
        return adjList.keySet();
    }
}
