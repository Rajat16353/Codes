// There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

// A province is a group of directly or indirectly connected cities and no other cities outside of the group.

// You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

// Return the total number of provinces.

 

// Example 1:


// Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
// Output: 2
// Example 2:


// Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
// Output: 3
 

// Constraints:

// 1 <= n <= 200
// n == isConnected.length
// n == isConnected[i].length
// isConnected[i][j] is 1 or 0.
// isConnected[i][i] == 1
// isConnected[i][j] == isConnected[j][i]

// By creating adjacency list
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);
                count += 1;
            }
        }
        return count;
    }

    private void dfs (int u, List<List<Integer>> graph, boolean[] visited) {
        visited[u] = true;

        for (int v: graph.get(u)) {
            if (!visited[v]) {
                dfs(v, graph, visited);
            }
        }
    }
}

// By directly checking neighbours bu traversing the matrix. (Better solution)
class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        int count = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, isConnected, visited);
                count += 1;
            }
        }
        return count;
    }

    private void dfs (int u, int[][] isConnected, boolean[] visited) {
        visited[u] = true;

        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[u][i] == 1 && !visited[i]) {
                dfs(i, isConnected, visited);
            }
        }
    }
}