// Alice and Bob have an undirected graph of n nodes and three types of edges:

// Type 1: Can be traversed by Alice only.
// Type 2: Can be traversed by Bob only.
// Type 3: Can be traversed by both Alice and Bob.
// Given an array edges where edges[i] = [typei, ui, vi] represents a bidirectional edge of type typei between nodes ui and vi, find the maximum number of edges you can remove so that after removing the edges, the graph can still be fully traversed by both Alice and Bob. The graph is fully traversed by Alice and Bob if starting from any node, they can reach all other nodes.

// Return the maximum number of edges you can remove, or return -1 if Alice and Bob cannot fully traverse the graph.

 

// Example 1:



// Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
// Output: 2
// Explanation: If we remove the 2 edges [1,1,2] and [1,1,3]. The graph will still be fully traversable by Alice and Bob. Removing any additional edge will not make it so. So the maximum number of edges we can remove is 2.
// Example 2:



// Input: n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
// Output: 0
// Explanation: Notice that removing any edge will not make the graph fully traversable by Alice and Bob.
// Example 3:



// Input: n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
// Output: -1
// Explanation: In the current graph, Alice cannot reach node 4 from the other nodes. Likewise, Bob cannot reach 1. Therefore it's impossible to make the graph fully traversable.
 

 

// Constraints:

// 1 <= n <= 105
// 1 <= edges.length <= min(105, 3 * n * (n - 1) / 2)
// edges[i].length == 3
// 1 <= typei <= 3
// 1 <= ui < vi <= n
// All tuples (typei, ui, vi) are distinct.

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] parentA = new int[n + 1];
        int[] parentB = new int[n + 1];
        int[] rankA = new int[n + 1];
        int[] rankB = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parentA[i] = i;
            parentB[i] = i;
            rankA[i] = 1;
            rankB[i] = 1;
        }

        Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0]));
        int mergedA = 1;
        int mergedB = 1;
        int removeEdge = 0;

        for (int[] edge: edges) {
            if (edge[0] == 3) {
                boolean a = union(parentA, rankA, edge[1], edge[2]);
                boolean b = union(parentB, rankB, edge[1], edge[2]);

                if (a) mergedA += 1;
                if (b) mergedB += 1;

                if (!a && !b) {
                    removeEdge += 1;
                }
            } else if (edge[0] == 1) {
                boolean a = union(parentA, rankA, edge[1], edge[2]);
                
                if (a) {
                    mergedA += 1;
                } else {
                    removeEdge += 1;
                }
            } else {
                boolean b = union(parentB, rankB, edge[1], edge[2]);
                if (b) {
                    mergedB += 1;
                } else {
                    removeEdge += 1;
                }
            }
        }

        if (mergedA != n || mergedB != n) return -1;

        return removeEdge;
    }

    private int[] createParent(int n) {
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
        return parent;
    }

    private int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent, parent[x]);
    }

    private boolean union(int[] parent, int[] rank, int x, int y) {
        int lx = find(parent, x);
        int ly = find(parent, y);

        if (lx != ly) {
            if (rank[lx] > rank[ly]) {
                parent[ly] = lx;
            } else if (rank[lx] < rank[ly]) {
                parent[lx] = ly;
            } else {
                parent[lx] = ly;
                rank[ly] += 1;
            }

            return true;
        }

        return false;
    }
}