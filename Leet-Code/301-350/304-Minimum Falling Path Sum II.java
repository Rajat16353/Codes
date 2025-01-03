// Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.

// A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.

 

// Example 1:


// Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
// Output: 13
// Explanation: 
// The possible falling paths are:
// [1,5,9], [1,5,7], [1,6,7], [1,6,8],
// [2,4,8], [2,4,9], [2,6,7], [2,6,8],
// [3,4,8], [3,4,9], [3,5,7], [3,5,9]
// The falling path with the smallest sum is [1,5,7], so the answer is 13.
// Example 2:

// Input: grid = [[7]]
// Output: 7
 

// Constraints:

// n == grid.length == grid[i].length
// 1 <= n <= 200
// -99 <= grid[i][j] <= 99

// Recursion with memoisation
class Solution {
    private int minFallingPathSum(int[][] grid, int r, int c, int[][] dp) {
        if (c < 0 || c >= grid[0].length) return Integer.MAX_VALUE - 200;

        if (dp[r][c] != Integer.MAX_VALUE) return dp[r][c];

        if (r == 0) return grid[r][c];

        int minPath = Integer.MAX_VALUE;
        for (int i = 0; i < grid[0].length; i++) {
            if (i == c) continue;
            minPath = Math.min(minPath, grid[r][c] + minFallingPathSum(grid, r - 1, i, dp));
        }

        return dp[r][c] = minPath;
    }

    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minPathSum = Integer.MAX_VALUE;
        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));

        for(int c = 0; c < n; c++) {
            minPathSum = Math.min(minPathSum, minFallingPathSum(grid, m - 1, c, dp));
        }

        Arrays.stream(dp).forEach(row -> System.out.println(Arrays.toString(row)));

        return minPathSum;
    }
}

// Tabulation
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minPathSum = Integer.MAX_VALUE;
        int[][] dp = new int[m][n];
        
        for(int c = 0; c < n; c++) {
           dp[0][c] = grid[0][c];
        }

        for (int r = 1; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int minPath = Integer.MAX_VALUE;
                for (int i = 0; i < m; i++) {
                    if (i == c) continue;
                    minPath = Math.min(minPath, grid[r][c] + dp[r - 1][i]);
                }

                dp[r][c] = minPath;
            }
        }

        for(int c = 0; c < n; c++) {
           minPathSum = Math.min(minPathSum, dp[n - 1][c]);
        }

        return minPathSum;
    }
}

// Tabulation with optimised memory
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minPathSum = Integer.MAX_VALUE;
        int[] dp = new int[n];
        
        for(int c = 0; c < n; c++) {
           dp[c] = grid[0][c];
        }

        for (int r = 1; r < m; r++) {
            int[] curDp = new int[n];
            for (int c = 0; c < n; c++) {
                int minPath = Integer.MAX_VALUE;
                for (int i = 0; i < m; i++) {
                    if (i == c) continue;
                    minPath = Math.min(minPath, grid[r][c] + dp[i]);
                }

                curDp[c] = minPath;
            }
            dp = curDp;
        }

        for(int c = 0; c < n; c++) {
           minPathSum = Math.min(minPathSum, dp[c]);
        }

        return minPathSum;
    }
}