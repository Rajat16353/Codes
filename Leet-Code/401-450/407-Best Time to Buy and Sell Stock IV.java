// You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

// Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

// Example 1:

// Input: k = 2, prices = [2,4,1]
// Output: 2
// Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
// Example 2:

// Input: k = 2, prices = [3,2,6,5,0,3]
// Output: 7
// Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 

// Constraints:

// 1 <= k <= 100
// 1 <= prices.length <= 1000
// 0 <= prices[i] <= 1000

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k + 1];
        
        Arrays.stream(dp).forEach(mat -> Arrays.stream(mat).forEach(row -> Arrays.fill(row, -1)));
        return recursion(0, 1, prices, k, dp);
    }

    private int recursion(int i, int canBuy, int[] prices, int budget, int[][][] dp) {
        if (budget == 0) return 0;
        if (i == prices.length) return 0;

        if (dp[i][canBuy][budget] != -1) return dp[i][canBuy][budget];
        if (canBuy == 1) {
            return dp[i][canBuy][budget] = Math.max(-prices[i] + recursion(i + 1, 0, prices, budget, dp), recursion(i + 1, 1, prices, budget, dp));
        } else {
            return dp[i][canBuy][budget] = Math.max(prices[i] + recursion(i + 1, 1, prices, budget - 1, dp), recursion(i + 1, 0, prices, budget, dp));
        }
    }
}

// Tabulation
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][k + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int budget = 1; budget <= k; budget++) {
                    int profit = 0;
                    if (canBuy == 1) {
                        profit = Math.max(-prices[i] + dp[i + 1][0][budget], dp[i + 1][1][budget]);
                    } else {
                        profit = Math.max(prices[i] + dp[i + 1][1][budget - 1], dp[i + 1][0][budget]);
                    }

                    dp[i][canBuy][budget] = profit;
                }
            }
        }
        
        return dp[0][1][k];
    }
}

// Memory Optimised recurison with memoisation
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2 * k + 1];
        
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return recursion(0, prices, 0, k, dp);
    }

    private int recursion(int i, int[] prices, int transaction, int k, int[][] dp) {
        if (transaction == 2 * k || i == prices.length) return 0;

        if (dp[i][transaction] != -1) return dp[i][transaction];
        if (transaction % 2 == 0) {
            return dp[i][transaction] = Math.max(-prices[i] + recursion(i + 1, prices, transaction + 1, k, dp), recursion(i + 1, prices, transaction, k, dp));
        }
        return dp[i][transaction] = Math.max(prices[i] + recursion(i + 1, prices, transaction + 1, k, dp), recursion(i + 1, prices, transaction, k, dp));
    }
}

// Memory optmised tabulation
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n + 1][2 * k + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int transaction = 2 * k - 1; transaction >= 0; transaction--) {
                if (transaction % 2 == 0) {
                    dp[i][transaction] = Math.max(-prices[i] + dp[i + 1][transaction + 1], dp[i + 1][transaction]);
                } else {
                    dp[i][transaction] = Math.max(prices[i] + dp[i + 1][transaction + 1], dp[i + 1][transaction]);
                }
            }
        }

        return dp[0][0];
    }
}