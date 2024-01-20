// You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:


// You should build the array arr which has the following properties:

// arr has exactly n integers.
// 1 <= arr[i] <= m where (0 <= i < n).
// After applying the mentioned algorithm to arr, the value search_cost is equal to k.
// Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

// Example 1:

// Input: n = 2, m = 3, k = 1
// Output: 6
// Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
// Example 2:

// Input: n = 5, m = 2, k = 3
// Output: 0
// Explanation: There are no possible arrays that satisfy the mentioned conditions.
// Example 3:

// Input: n = 9, m = 1, k = 1
// Output: 1
// Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]
 

// Constraints:

// 1 <= n <= 50
// 1 <= m <= 100
// 0 <= k <= n

class Solution {
    int MOD = (int) 1e9 + 7;
    int[][][] memo;

    public int numOfArrays(int n, int m, int k) {
        memo = new int[n][m + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dp(0, 0, k, n, m);

    }

    public int dp(int i, int maxSoFar, int remain, int n, int m) {

        if (i == n) {
            if (remain == 0) {
                return 1;
            }
            return 0;
        }
        
        if (remain < 0) {
            return 0;
        }
        
        if (memo[i][maxSoFar][remain] != -1) {
            return memo[i][maxSoFar][remain];
        }
        
        int ans = 0;
        for (int num = 1; num <= maxSoFar; num++) {
            ans = (ans + dp(i + 1, maxSoFar, remain, n, m)) % MOD;
        }

        for (int num = maxSoFar + 1; num <= m; num++) {
            ans = (ans + dp(i + 1, num, remain - 1, n, m)) % MOD;
        }
        
        memo[i][maxSoFar][remain] = ans;
        return ans;
    }
}