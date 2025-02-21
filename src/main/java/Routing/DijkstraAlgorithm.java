package Routing;
import java.util.*;

public class DijkstraAlgorithm {
    private final Graph graph;

    public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
    }

    public List<String> findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousNodes = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
            previousNodes.put(node, null);
        }
        distances.put(start, 0);
        queue.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(end)) break;

            for (Graph.Edge edge : graph.getEdges(current)) {
                String neighbor = edge.target;
                int newDist = distances.get(current) + edge.weight;

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previousNodes.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        List<String> path = new LinkedList<>();
        for (String at = end; at != null; at = previousNodes.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
