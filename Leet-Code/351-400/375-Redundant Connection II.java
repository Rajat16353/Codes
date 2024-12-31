// In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

// The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.

// The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.

// Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

 

// Example 1:


// Input: edges = [[1,2],[1,3],[2,3]]
// Output: [2,3]
// Example 2:


// Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
// Output: [4,1]
 

// Constraints:

// n == edges.length
// 3 <= n <= 1000
// edges[i].length == 2
// 1 <= ui, vi <= n
// ui != vi

class Solution {
    int[] parent;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length + 1;

        parent = new int[n];
        int[] inDegree = new int[n];
        Arrays.fill(inDegree, -1);

        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }

        int bl1 = -1, bl2 = -1;
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (inDegree[v] != -1) {
                bl1 = i;
                bl2 = inDegree[v];
                break;
            }
            inDegree[v] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            if (i == bl1) continue;
            if (union(edges[i][0], edges[i][1])) {
                if (bl1 == -1) {
                    return edges[i];
                } else {
                    return edges[bl2];
                }
            }
        }

        return edges[bl1];
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private boolean union(int u, int v) {
        int lu = find(u);
        int lv = find(v);

        if (lu != lv) {
            parent[lu] = lv;
            return false;
        } else {
            return true;
        }
    }
}