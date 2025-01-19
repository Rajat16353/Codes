// Given an m x n integers matrix, return the length of the longest increasing path in matrix.

// From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

// Example 1:


// Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
// Output: 4
// Explanation: The longest increasing path is [1, 2, 6, 9].
// Example 2:


// Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
// Output: 4
// Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
// Example 3:

// Input: matrix = [[1]]
// Output: 1
 

// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 200
// 0 <= matrix[i][j] <= 231 - 1

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Integer[][] dp = new Integer[m][n];

        if (matrix == null || m == 0 || n == 0) return 0;
        if (m == 1 && n == 1) return 1;

        int longest = 1;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] == null) {
                    longest = Math.max(longest, dfs(r, c, dp, matrix));
                }
            }
        }

        return longest;
    }

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int dfs(int r, int c, Integer[][] dp, int[][] matrix) {
        if (dp[r][c] != null) return dp[r][c];

        int max = 1;

        for (int[] d: dirs) {
            int nextR = r + d[0];
            int nextC = c + d[1];

            if (nextR < 0 || nextR >= matrix.length || nextC < 0 || nextC >= matrix[0].length) {
                continue;
            }

            if (matrix[nextR][nextC] <= matrix[r][c]) {
                continue;
            }

            max = Math.max(max, dfs(nextR, nextC, dp, matrix) + 1);
        }

        return dp[r][c] = max;
    }
}