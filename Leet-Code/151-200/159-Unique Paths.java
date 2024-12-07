// There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

// Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

// The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

// Example 1:


// Input: m = 3, n = 7
// Output: 28
// Example 2:

// Input: m = 3, n = 2
// Output: 3
// Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
// 1. Right -> Down -> Down
// 2. Down -> Down -> Right
// 3. Down -> Right -> Down
 

// Constraints:

// 1 <= m, n <= 100

// Using permutaiton and combination
class Solution {
    private double factorial(int n, int limit){    
        if (n == limit) {
            return 1;
        }
        else {
            return (n * factorial(n - 1, limit));
        }
    }

    public int uniquePaths(int m, int n) {
        return (int)((factorial(m + n - 2, (m > n ? m - 1 : n - 1))) / (factorial((m > n ? n - 1 : m - 1), 0)));
    }
}

// Dynamic Programming Top down approach
class Solution {
    private int topDown(int r, int c, int[][] dp) {
        if (r == 0 && c == 0) {
            return 1;
        }
        if (dp[r][c] != -1) return dp[r][c];

        int up = 0;
        int left = 0;
        if (r - 1 > -1) up = topDown(r - 1, c, dp);
        if (c - 1 > -1) left = topDown(r, c - 1, dp);

        return dp[r][c] = up + left;
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach((row) -> Arrays.fill(row, -1));

        return topDown(m - 1, n - 1, dp);
    }
}

// Bottom up approach
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) {
                    dp[r][c] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (r - 1 > -1) up = dp[r - 1][c];
                if (c - 1 > -1) left = dp[r][c - 1];
                dp[r][c] = up + left;
            }
        }

        return dp[m - 1][n - 1];
    }
}

// Space optimised bottom up approach
class Solution {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];

        for (int r = 0; r < m; r++) {
            int[] cur = new int[n];
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) {
                    cur[c] = 1;
                    continue;
                }
                int up = 0;
                int left = 0;
                if (r - 1 > -1) up = dp[c];
                if (c - 1 > -1) left = cur[c - 1];
                cur[c] = up + left;
            }
            dp = cur;
        }

        return dp[n - 1];
    }
}