// There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

// A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

// Return all critical connections in the network in any order.

 

// Example 1:


// Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
// Output: [[1,3]]
// Explanation: [[3,1]] is also accepted.
// Example 2:

// Input: n = 2, connections = [[0,1]]
// Output: [[0,1]]
 

// Constraints:

// 2 <= n <= 105
// n - 1 <= connections.length <= 105
// 0 <= ai, bi <= n - 1
// ai != bi
// There are no repeated connections.

class Solution {
    int[] parent;
    int[] disc;
    int[] low;
    int time;
    boolean[] visited;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (List<Integer> con: connections) {
            int u = con.get(0);
            int v = con.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        parent = new int[n];
        disc = new int[n];
        low = new int[n];
        time = 0;
        visited = new boolean[n];
        parent[0] = -1;
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, graph, result);

        return result;
    }

    private void dfs (int u, List<List<Integer>> graph, List<List<Integer>> result) {
        visited[u] = true;
        disc[u] = low[u] = time;
        time += 1;

        for (int v: graph.get(u)) {
            if (parent[u] == v) {
                continue;
            } else if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                parent[v] = u;
                dfs(v, graph, result);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    result.add(new ArrayList<>(Arrays.asList(new Integer[]{u, v})));
                }
            }
        }
    }
}