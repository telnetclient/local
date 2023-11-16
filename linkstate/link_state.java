import java.util.*;
 
public class link_state {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();
 
        List<List<Edge>> graph = new ArrayList<>();
 
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
 
        System.out.println("Enter edge details (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
 
            graph.get(source).add(new Edge(destination, weight));
        }
 
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();
 
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
 
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        minHeap.offer(new Node(source, 0));
 
        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int u = node.vertex;
 
            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;
 
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    minHeap.offer(new Node(v, distance[v]));
                }
            }
        }
 
        // Print the shortest distances from the source vertex
        System.out.println("Shortest distances from the source vertex " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + distance[i]);
        }
    }
}
 
class Edge {
    int destination;
    int weight;
 
    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}
 
class Node {
    int vertex;
    int distance;
 
    Node(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
}
