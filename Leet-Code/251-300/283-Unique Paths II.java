// You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

// An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

// Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

// The testcases are generated so that the answer will be less than or equal to 2 * 109.

 

// Example 1:


// Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
// Output: 2
// Explanation: There is one obstacle in the middle of the 3x3 grid above.
// There are two ways to reach the bottom-right corner:
// 1. Right -> Right -> Down -> Down
// 2. Down -> Down -> Right -> Right
// Example 2:


// Input: obstacleGrid = [[0,1],[0,0]]
// Output: 1
 

// Constraints:

// m == obstacleGrid.length
// n == obstacleGrid[i].length
// 1 <= m, n <= 100
// obstacleGrid[i][j] is 0 or 1.

// Top down approach
class Solution {
    private int topDown(int[][] mat, int r, int c, int[][] dp) {
        if (r < 0 || c < 0) return 0;

        if (mat[r][c] == 1) return 0;

        if (dp[r][c] != -1) return dp[r][c];

        if (r == 0 && c == 0) return 1;

        int up = topDown(mat, r - 1, c, dp);
        int left = topDown(mat, r, c -1, dp);

        return dp[r][c] = up + left;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));

        return topDown(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1, dp);
    }
}

// Bottom up approach with memory optimisation
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;

        int[] dp = new int[n];

        for(int r = 0; r < m; r++) {
            int[] cur = new int[n];
            for(int c = 0; c < n; c++) {
                if (obstacleGrid[r][c] == 1) {
                    cur[c] = 0;
                } else if (r == 0 && c == 0) {
                    cur[c] = 1;
                } else {
                    int left = 0, up = 0;
                    if (r > 0) up = dp[c];
                    if (c > 0) left = cur[c - 1];

                    cur[c] = (up + left);
                }
            }
            dp = cur;
        }
        return dp[n - 1];
    }
}