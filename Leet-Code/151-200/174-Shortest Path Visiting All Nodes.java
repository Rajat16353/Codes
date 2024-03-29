// You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a list of all the nodes connected with node i by an edge.

// Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

// Example 1:


// Input: graph = [[1,2,3],[0],[0],[0]]
// Output: 4
// Explanation: One possible path is [1,0,2,0,3]
// Example 2:


// Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
// Output: 4
// Explanation: One possible path is [0,1,4,2,3]
 

// Constraints:

// n == graph.length
// 1 <= n <= 12
// 0 <= graph[i].length < n
// graph[i] does not contain i.
// If graph[a] contains b, then graph[b] contains a.
// The input graph is always connected.

class Solution {
    class Node {
        int node;
        int mask;
        int cost;

        Node(int node, int mask, int cost) {
            this.node = node;
            this.mask = mask;
            this.cost = cost;
        }
    }

    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int all = (1 << n) - 1;
        Set<Pair<Integer, Integer>> vis = new HashSet<>();
        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            int maskValue = (1 << i);
            Node thisNode = new Node(i, maskValue, 1);
            q.add(thisNode);
            vis.add(new Pair<>(i, maskValue));
        }

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.mask == all) {
                return curr.cost - 1;
            }

            for (int adj : graph[curr.node]) {
                int bothVisitedMask = curr.mask | (1 << adj);
                Node thisNode = new Node(adj, bothVisitedMask, curr.cost + 1);

                if (!vis.contains(new Pair<>(adj, bothVisitedMask))) {
                    vis.add(new Pair<>(adj, bothVisitedMask));
                    q.add(thisNode);
                }
            }
        }

        return -1;
    }
}