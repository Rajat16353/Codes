// You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

// Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

// Note:

// You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
// The transaction fee is only charged once for each stock purchase and sale.
 

// Example 1:

// Input: prices = [1,3,2,8,4,9], fee = 2
// Output: 8
// Explanation: The maximum profit can be achieved by:
// - Buying at prices[0] = 1
// - Selling at prices[3] = 8
// - Buying at prices[4] = 4
// - Selling at prices[5] = 9
// The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
// Example 2:

// Input: prices = [1,3,7,5,10,3], fee = 3
// Output: 6
 

// Constraints:

// 1 <= prices.length <= 5 * 104
// 1 <= prices[i] < 5 * 104
// 0 <= fee < 5 * 104

// Recursion with memoisation
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return recursion(0, 1, prices, fee, dp);
    }

    private int recursion(int i, int canBuy, int[] prices, int fee, int[][] dp) {
        if (i == prices.length) return 0;

        if (dp[i][canBuy] != -1) return dp[i][canBuy];
        if (canBuy == 1) {
            return dp[i][canBuy] = Math.max(-prices[i] + recursion(i + 1, 0, prices, fee, dp), recursion(i + 1, 1, prices, fee, dp));
        } else {
            return dp[i][canBuy] = Math.max(prices[i] - fee + recursion(i + 1, 1, prices, fee, dp), recursion(i + 1, 0, prices, fee, dp));
        }
    }
}

// Tabulation
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2];
        dp[n][0] = dp[n][1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    dp[i][canBuy] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                } else {
                    dp[i][canBuy] = Math.max(prices[i] - fee + dp[i + 1][1], dp[i + 1][0]);
                }
            }
        }
        return dp[0][1];
    }
}

// Tabulation with optmised memory
class Solution {
    public int maxProfit(int[] prices, int fee) {
       int n = prices.length;
        int[] next = new int[2];
        int[] curr = new int[2];
        next[0] = next[1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    curr[canBuy] = Math.max(-prices[i] + next[0], next[1]);
                } else {
                    curr[canBuy] = Math.max(prices[i] - fee + next[1], next[0]);
                }
            }
            next = curr;
        }
        return next[1];
    }
}