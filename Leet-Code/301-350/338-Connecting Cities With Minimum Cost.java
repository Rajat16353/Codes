// There are ‘N’ cities numbered from 1 to ‘N’ and ‘M’ roads. Each road connectss two different cities and described as a two-way road using three integers (‘U’, ‘V’, ‘W’) which represents the cost ‘W’ to connect city ‘U’ and city ‘V’ together.

// Now, you are supposed to find the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together. The cost is the sum of the connection costs used. If the task is impossible, return -1.

// Detailed explanation ( Input/output format, Notes, Images )
// Constraints :
// 1 <= T <= 50
// 1 <= N <= 10^4
// 1 <= M <= 10^4
// 1 <= W <= 10^3
// 1 <= U, V <= N

// Time Limit: 1 sec
// Sample Input 1 :
// 2
// 5 6
// 1 2 6
// 2 3 5
// 3 4 4
// 1 4 1
// 1 3 2
// 3 5 3
// 3 1
// 1 2 4
// Sample output 1 :
// 11
// -1
// Explanation of Sample output 1 :
// For the first test case, the graph below describes the connection between the cities:

// We can choose the following roads to connect all the cities getting minimum cost:

// And its cost is 1 + 2 + 5 + 3 = 11

// For the second test case, there is no way to connect all cities. So print -1.
// Sample Input 2 :
// 2
// 3 3
// 1 2 1
// 2 3 2
// 3 1 3
// 4 3
// 1 2 4
// 2 3 5
// 3 4 1
// Sample output 2 :
// 3
// 10
// Explanation of Sample output 2 :
// For the first test case, the minimum cost will be 3.

// For the second test case, the minimum cost will be 10.

import java.util.* ;
import java.io.*; 
public class Solution {

    public static int getMinimumCost(int n, int m, int[][] connections) {
        List<List<Edge>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] con: connections) {
            int u = con[0];
            int v = con[1];
            int wt = con[2];

            graph.get(u).add(new Edge(v, wt));
            graph.get(v).add(new Edge(u, wt));
        }

        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        visited[0] = true;

        minHeap.add(new Edge(1, 0));
        int cost = 0;

        while(!minHeap.isEmpty()) {
            Edge cur = minHeap.poll();
            int v = cur.v;
            int wt = cur.wt;

            if (visited[v]) continue;

            visited[v] = true;
            cost += wt;

            for (Edge edge: graph.get(v)) {
                if (!visited[edge.v]) {
                    minHeap.offer(edge);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (!visited[i]) return -1;
        }
        return cost;
    }
    
    private static class Edge implements Comparable<Edge> {
        int v;
        int wt;

        public Edge(int v, int wt) {
            this.v = v;
            this.wt = wt;
        }

        @Override
        public int compareTo(Edge b) {
            return Integer.compare(this.wt, b.wt);
        }
    }
}