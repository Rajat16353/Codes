// You are given an array prices where prices[i] is the price of a given stock on the ith day.

// Find the maximum profit you can achieve. You may complete at most two transactions.

// Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

 

// Example 1:

// Input: prices = [3,3,5,0,0,3,1,4]
// Output: 6
// Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
// Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
// Example 2:

// Input: prices = [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
// Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
// Example 3:

// Input: prices = [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.
 

// Constraints:

// 1 <= prices.length <= 105
// 0 <= prices[i] <= 105

// Recursion with memoisation Exceeds time limit
class Solution {
    private int maxProfitBacktrack(int[] prices, int index, boolean canBuy, int budget, Map<Key, Integer> dp) {
        if (budget == 0 && canBuy) return 0;

        if (index == prices.length) return 0;

        Key key = new Key(index, budget, canBuy);
        if (dp.containsKey(key)) return dp.get(key);
        int profit = 0;

        if (canBuy) {
            profit = Math.max(maxProfitBacktrack(prices, index + 1, false, budget - 1, dp) - prices[index], maxProfitBacktrack(prices, index + 1, true, budget, dp));
        } else {
            profit = Math.max(maxProfitBacktrack(prices, index + 1, true, budget, dp) + prices[index], maxProfitBacktrack(prices, index + 1, false, budget, dp));
        }

        dp.put(key, profit);
        return profit;
    }

    public int maxProfit(int[] prices) {
        Map<Key, Integer> dp = new HashMap<>();
        return maxProfitBacktrack(prices, 0, true, 2, dp);
    }

    class Key {
        public int index;
        public int budget;
        public boolean canBuy;

        public Key(int index, int budget, boolean canBuy) {
            this.index = index;
            this.budget = budget;
            this.canBuy = canBuy;
        }
    }
}

// Tabulation
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        int min = prices[0];
        int max = 0;
        dp1[0] = 0;

        for(int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        max = prices[n - 1];
        dp2[n - 1] = 0;

        for (int i = n - 2; i >= 0; i--) {
            dp2[i] = Math.max(dp2[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp1[i] + dp2[i]);
        }

        return max;
    }
}