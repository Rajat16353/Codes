// Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

// A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

 

// Example 1:


// Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
// Output: 13
// Explanation: There are two falling paths with a minimum sum as shown.
// Example 2:


// Input: matrix = [[-19,57],[-40,-5]]
// Output: -59
// Explanation: The falling path with a minimum sum is shown.
 

// Constraints:

// n == matrix.length == matrix[i].length
// 1 <= n <= 100
// -100 <= matrix[i][j] <= 100

// Recursion with memoisation
class Solution {
    private int minFallingPathSum(int[][] matrix, int r, int c, int[][] dp) {
        if (c < 0 || c >= matrix[0].length) {
            return Integer.MAX_VALUE - 200;
        }

        if (dp[r][c] != -500) return dp[r][c];

        if (r == 0) {
            return matrix[r][c];
        }

        int up = matrix[r][c] + minFallingPathSum(matrix, r - 1, c, dp);
        int leftDiagonal = matrix[r][c] + minFallingPathSum(matrix, r - 1, c - 1, dp);
        int rightDiagonal = matrix[r][c] + minFallingPathSum(matrix, r - 1, c + 1, dp);

        return dp[r][c] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
    }

    public int minFallingPathSum(int[][] matrix) {
        int minPathSum = Integer.MAX_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -500));

        for(int i = 0; i < matrix[0].length; i++) {
            minPathSum = Math.min(minPathSum, minFallingPathSum(matrix, matrix.length - 1, i, dp));
        }

        return minPathSum;
    }
}

// Tabulation approach
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int c = 0; c < n; c++) {
            dp[0][c] = matrix[0][c];
        }

        for (int r = 1; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int up = matrix[r][c], leftDiagonal = matrix[r][c], rightDiagonal = matrix[r][c];
                up += dp[r - 1][c];
                if (c > 0) {
                    leftDiagonal += dp[r - 1][c - 1];
                } else {
                    leftDiagonal += Integer.MAX_VALUE - 200;
                }
                if (c < n - 1) {
                    rightDiagonal += dp[r - 1][c + 1];
                } else {
                    rightDiagonal += Integer.MAX_VALUE - 200;
                }

                dp[r][c] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
        }
        
        int minPathSum = Integer.MAX_VALUE;

        for (int c = 0; c < n; c++) {
            minPathSum = Math.min(minPathSum, dp[m - 1][c]);
        }

        return minPathSum;
    }
}

// Tabulation with optimised memory
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];

        for (int c = 0; c < n; c++) {
            dp[c] = matrix[0][c];
        }

        for (int r = 1; r < m; r++) {
            int[] curDp = new int[n];
            for (int c = 0; c < n; c++) {
                int leftDiagonal = matrix[r][c], rightDiagonal = matrix[r][c];
                int up = matrix[r][c] + dp[c];
                if (c > 0) {
                    leftDiagonal += dp[c - 1];
                } else {
                    leftDiagonal += Integer.MAX_VALUE - 200;
                }
                if (c < n - 1) {
                    rightDiagonal += dp[c + 1];
                } else {
                    rightDiagonal += Integer.MAX_VALUE - 200;
                }

                curDp[c] = Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
            dp = curDp;
        }
        
        int minPathSum = Integer.MAX_VALUE;

        for (int c = 0; c < n; c++) {
            minPathSum = Math.min(minPathSum, dp[c]);
        }

        return minPathSum;
    }
}