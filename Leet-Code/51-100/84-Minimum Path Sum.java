// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

// Note: You can only move either down or right at any point in time.

 

// Example 1:


// Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
// Output: 7
// Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
// Example 2:

// Input: grid = [[1,2,3],[4,5,6]]
// Output: 12
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 200
// 0 <= grid[i][j] <= 100

// Recursion with memoisation
class Solution {
    private int minPathSum(int[][] grid, int r, int c, int[][] dp) {
        if (dp[r][c] != 0) return dp[r][c];
        
        if (r == 0 && c == 0) {
            return grid[r][c];
        }
        int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
        if (r > 0) up = grid[r][c] + minPathSum(grid, r - 1, c, dp);
        if (c > 0) left = grid[r][c] + minPathSum(grid, r, c - 1, dp);

        return dp[r][c] = Math.min(up, left);
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        return minPathSum(grid, m - 1, n - 1, dp);
    }
}

// Tabulation approach
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++){
                if (r == 0 && c == 0) {
                    dp[r][c] = grid[r][c];
                } else {
                    int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
                    if (r > 0) up = grid[r][c] + dp[r - 1][c];
                    if (c > 0) left = grid[r][c] + dp[r][c - 1];
                    dp[r][c] = Math.min(up, left);
                }
            }
        }
        
        return dp[m - 1][n - 1];
    }
}