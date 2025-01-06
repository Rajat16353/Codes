// You are given an array prices where prices[i] is the price of a given stock on the ith day.

// Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

// After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

// Example 1:

// Input: prices = [1,2,3,0,2]
// Output: 3
// Explanation: transactions = [buy, sell, cooldown, buy, sell]
// Example 2:

// Input: prices = [1]
// Output: 0
 

// Constraints:

// 1 <= prices.length <= 5000
// 0 <= prices[i] <= 1000

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return recursion(0, 1, prices, dp);
    }

    private int recursion(int i, int canBuy, int[] prices, int[][] dp) {
        if (i >= prices.length) return 0;

        if (dp[i][canBuy] != -1) return dp[i][canBuy];
        if (canBuy == 1) {
            return dp[i][canBuy] = Math.max(-prices[i] + recursion(i + 1, 0, prices, dp), recursion(i + 1, 1, prices, dp));
        } else {
            return dp[i][canBuy] = Math.max(prices[i] + recursion(i + 2, 1, prices, dp), recursion(i + 1, 0, prices, dp));
        }
    }
}

// Tabulation
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        dp[n][0] = dp[n][1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                if (canBuy == 1) {
                    dp[i][canBuy] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
                } else {
                    dp[i][canBuy] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][0]);
                }
            }
        }
        return dp[0][1];
    }
}

// clean tabulation
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 2][2];
        dp[n][0] = dp[n][1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp[i][1] = Math.max(-prices[i] + dp[i + 1][0], dp[i + 1][1]);
            dp[i][0] = Math.max(prices[i] + dp[i + 2][1], dp[i + 1][0]);
        }
        return dp[0][1];
    }
}