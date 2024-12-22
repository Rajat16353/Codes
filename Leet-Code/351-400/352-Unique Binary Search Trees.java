// Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

 

// Example 1:


// Input: n = 3
// Output: 5
// Example 2:

// Input: n = 1
// Output: 1
 

// Constraints:

// 1 <= n <= 19

// Recursion
class Solution {
    public int numTrees(int n) {
        return catalan(n);
    }

    private int catalan(int n) {
        if (n <= 1) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalan(i) * catalan(n - i - 1);
        }

        return res;
    }
}

// Recursion with memoization
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        return catalan(n, dp);
    }

    private int catalan(int n, int[] dp) {
        if (n <= 1) {
            return 1;
        }

        if (dp[n] != 0) return dp[n];

        int res = 0;
        for (int i = 0; i < n; i++) {
            res += catalan(i, dp) * catalan(n - i - 1, dp);
        }

        return dp[n] = res;
    }
}

// Using tabulation
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int j = 2; j <= n; j++) {
            for (int i = 0; i < j; i++) {
                dp[j] += dp[i] * dp[j - i - 1];
            }
        }
        return dp[n];
    }
}