package org.programming.mitra.exercises.algos.graph.apsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Floyd-Warshall all pair shortest path (apsp) algorithm for directed graph,
 * It works only on positive weight edges,
 * It is based on dynamic programming.
 * <p>
 * Time complexity = O(E.logV), In worst case E = V^2, Which wor case time complexity = O(V^2.logV)
 * Space complexity = O(E+V)
 *
 * @author Naresh Joshi
 */
/*
Algorithm :-

dist = |V| × |V| array of minimum distances initialized to ∞ (infinity)
for each vertex v do
    dist[v][v] = 0
for each edge (u, v) do
    dist[u][v] = w(u, v)  // The weight of the edge (u, v)

for k from 1 to |V|
    for i from 1 to |V|
        for j from 1 to |V|
            if dist[i][j] > dist[i][k] + dist[k][j]
                dist[i][j] = dist[i][k] + dist[k][j]
            end if
 */
public class FloydWarshallAlgorithm {

    private static final int INFINITY = 999999;

    private static int[][] runFloydWarshall(Graph graph) {
        int[][] dist = new int[graph.V][graph.V];
        for (int i = 0; i < graph.V; i++) {
            dist[i][i] = 0;
            for (int j = 0; j < graph.V; j++) {
                dist[i][j] = INFINITY;
            }
        }

        for (Edge edge : graph.edges) {
            dist[edge.from][edge.to] = edge.weight;
        }

        for (int k = 0; k < graph.V; k++) {
            for (int i = 0; i < graph.V; i++) {
                for (int j = 0; j < graph.V; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 13);
        graph.addEdge(1, 4, 11);
        graph.addEdge(2, 3, 12);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 15);
        graph.addEdge(4, 5, 14);

        System.out.println("For below tree:");
        System.out.println(
                "               /0\\\n" +
                        "              /   \\ \n" +
                        "            4/     \\7\n" +
                        "            /   8   \\\n" +
                        "           1 ------- 2\n" +
                        "           | \\11  12/|\n" +
                        "         13|   \\  /  | 10\n" +
                        "           |   / \\   |\n" +
                        "           |  /   \\  |\n" +
                        "           | /     \\ |\n" +
                        "           3 ------- 4\n" +
                        "            \\   9   /\n" +
                        "           15\\     /14\n" +
                        "              \\   /\n" +
                        "               \\5/"
        );

        int[][] dist = runFloydWarshall(graph);

        System.out.println("All pair shortest distances generated by FloydWarshallAlgorithm are:");
        for (int i = 0; i < graph.V; i++) {
            for (int j = 0; j < graph.V; j++) {
                if (dist[i][j] != INFINITY)
                    System.out.println(String.format("Distance of path %d --> %d is %d", i, j, dist[i][j]));
            }
        }
    }

    private static class Graph {
        public int V, E;
        public List<Edge> edges;

        public Graph(int V) {
            this.V = V;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int weight) {
            edges.add(new Edge(from, to, weight));
            E++;
        }
    }

    private static class Edge {
        public int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
