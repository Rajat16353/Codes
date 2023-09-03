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

// Dynamic Programming
class Solution {
    public int uniquePaths(int m, int n) {
        int[] prev_row = new int[n];
        for (int i = 0; i < n; i++) {
            prev_row[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            int[] curr_row = new int[n];
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    curr_row[j] = prev_row[j] + curr_row[j - 1];
                } else {
                    curr_row[j] = prev_row[j];
                }
            }
            prev_row = curr_row;
        }
        return prev_row[n-1];
    }
}