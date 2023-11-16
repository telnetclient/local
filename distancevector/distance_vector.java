import java.util.Scanner;
 
public class distance_vector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();
 
        int[] dist = new int[V];
 
        // Initialize distances to all vertices as infinity
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
 
        // Create an array to store the edges
        int[][] edges = new int[E][3]; // Each edge is represented as {source, destination, weight}
 
        System.out.println("Enter edge details (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            edges[i][0] = scanner.nextInt(); // Source vertex
            edges[i][1] = scanner.nextInt(); // Destination vertex
            edges[i][2] = scanner.nextInt(); // Weight of the edge
        }
 
        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();
 
        // Set the distance to the source vertex as 0
        dist[source] = 0;
 
        // Relax all edges |V| - 1 times
        for (int i = 1; i < V; i++) {
            for (int j = 0; j < E; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int weight = edges[j][2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }
 
        // Check for negative-weight cycles
        for (int j = 0; j < E; j++) {
            int u = edges[j][0];
            int v = edges[j][1];
            int weight = edges[j][2];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains a negative-weight cycle. Bellman-Ford does not work.");
                return;
            }
        }
 
        // Print the shortest distances from the source vertex
        System.out.println("Shortest distances from the source vertex " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + dist[i]);
        }
    }
}
