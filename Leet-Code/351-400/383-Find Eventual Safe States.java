// There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

// A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

// Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

 

// Example 1:

// Illustration of graph
// Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
// Output: [2,4,5,6]
// Explanation: The given graph is shown above.
// Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
// Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
// Example 2:

// Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
// Output: [4]
// Explanation:
// Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 

// Constraints:

// n == graph.length
// 1 <= n <= 104
// 0 <= graph[i].length <= n
// 0 <= graph[i][j] <= n - 1
// graph[i] is sorted in a strictly increasing order.
// The graph may contain self-loops.
// The number of edges in the graph will be in the range [1, 4 * 104].

// Using BFS/ Topological sort
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int v = graph.length;
        List<List<Integer>> adjRev = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            adjRev.add(new ArrayList<>());
        }

        int[] inDegree = new int[v];
        for (int i = 0; i < v; i++) {
            for (int v2: graph[i]) {
                adjRev.get(v2).add(i);
                inDegree[i] += 1;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        List<Integer> safe = new ArrayList<>();

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            safe.add(cur);
            for(int nbr: adjRev.get(cur)) {
                inDegree[nbr] -= 1;
                if (inDegree[nbr] == 0) {
                    queue.offer(nbr);
                }
            }
        }

        Collections.sort(safe);
        return safe;
    }
}

// Better solution
// Using dfs to detect a cycle
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int v = graph.length;
        boolean[] visited = new boolean[v];
        boolean[] pathVisited = new boolean[v];
        boolean[] safe = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(i, visited, pathVisited, graph, safe);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (safe[i]) safeNodes.add(i);
        }

        return safeNodes;
    }

    private boolean dfs(int node, boolean[] visited, boolean[] pathVisited, int[][] graph, boolean[] safe) {
        visited[node] = true;
        pathVisited[node] = true;

        for (int nbr: graph[node]) {
            if (!visited[nbr]) {
                if (dfs(nbr, visited, pathVisited, graph, safe)) return true;
            } else if (pathVisited[nbr]) {
                return true;
            }
        }

        safe[node] = true;
        pathVisited[node] = false;
        return false;
    }
}