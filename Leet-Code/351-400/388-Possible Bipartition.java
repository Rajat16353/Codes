// We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and they should not go into the same group.

// Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

 

// Example 1:

// Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
// Output: true
// Explanation: The first group has [1,4], and the second group has [2,3].
// Example 2:

// Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
// Output: false
// Explanation: We need at least 3 groups to divide them. We cannot put them in two groups.
 

// Constraints:

// 1 <= n <= 2000
// 0 <= dislikes.length <= 104
// dislikes[i].length == 2
// 1 <= ai < bi <= n
// All the pairs of dislikes are unique.

class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] dislike: dislikes) {
            graph.get(dislike[0] - 1).add(dislike[1] - 1);
            graph.get(dislike[1] - 1).add(dislike[0] - 1);
        }

        int[] colorNodes = new int[n];
        for (int i = 0; i < n; i++) {
            if (colorNodes[i] == 0 && !dfs(graph, colorNodes, i, 1)) return false;
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> graph, int[] colorNodes, int curNode, int color) {
        colorNodes[curNode] = color;

        for (int nbr: graph.get(curNode)) {
            if (colorNodes[nbr] == 0) {
                if (!dfs(graph, colorNodes, nbr, color * -1)) return false;
            } else if (colorNodes[nbr] == color) {
                return false;
            }
        }

        return true;
    }
}