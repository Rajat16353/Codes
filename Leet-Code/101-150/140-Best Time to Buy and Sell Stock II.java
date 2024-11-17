// You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

// On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

// Find and return the maximum profit you can achieve.

 

// Example 1:

// Input: prices = [7,1,5,3,6,4]
// Output: 7
// Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
// Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
// Total profit is 4 + 3 = 7.
// Example 2:

// Input: prices = [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
// Total profit is 4.
// Example 3:

// Input: prices = [7,6,4,3,1]
// Output: 0
// Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 

// Constraints:

// 1 <= prices.length <= 3 * 104
// 0 <= prices[i] <= 104

class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        for(int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }

        return maxProfit;
    }
}

// Using recursion and memoisation
class Solution {
    Map<Pair<Integer, Boolean>, Integer> dp;
    private int maxProfit(int[] prices, int index, Boolean canBuy) {
        if (index == prices.length) {
            return 0;
        }
        Pair key = new Pair(index, canBuy);
        if (dp.containsKey(key)) return dp.get(key);
        int profit = 0;

        if (canBuy) {
            profit = Math.max(maxProfit(prices, index + 1, false) - prices[index], maxProfit(prices, index + 1, true) + 0);
        } else {
            profit = Math.max(maxProfit(prices, index + 1, true) + prices[index], maxProfit(prices, index + 1, false) + 0);
        }
        dp.put(key, profit);
        return profit;
    }

    public int maxProfit(int[] prices) {
        dp = new HashMap<>();
        return maxProfit(prices, 0, true);
    }
}

// Dynamic Programming
class Solution {
    Map<Pair<Integer, Boolean>, Integer> dp;
    private void maxProfit(int[] prices, int index, Boolean canBuy) {
        Pair key = new Pair(index, canBuy);
        int profit = 0;
        if (canBuy) {
            profit = Math.max(dp.get(new Pair(index + 1, false)) - prices[index], dp.get(new Pair(index + 1, true)) + 0);
        } else {
            profit = Math.max(dp.get(new Pair(index + 1, true)) + prices[index], dp.get(new Pair(index + 1, false)) + 0);
        }
        dp.put(key, profit);
    }

    public int maxProfit(int[] prices) {
        dp = new HashMap<>();
        dp.put(new Pair(prices.length, false), 0);
        dp.put(new Pair(prices.length, true), 0);

        for (int index = prices.length - 1; index > -1; index--) {
            maxProfit(prices, index, true);
            maxProfit(prices, index, false);
        }
        return dp.get(new Pair(0, true));
    }
}

// Dynamic Programming with optimised memory
class Solution {
    int[] ahead;
    int[] cur;
    private void maxProfit(int[] prices, int index, int canBuy) {
        int profit = 0;
        if (canBuy != 0) {
            profit = Math.max(ahead[0] - prices[index], ahead[1] + 0);
        } else {
            profit = Math.max(ahead[1] + prices[index], ahead[0] + 0);
        }
        cur[canBuy] = profit;
    }

    public int maxProfit(int[] prices) {
        ahead = new int[2];
        cur = new int[2];
        ahead[0] = 0;
        ahead[1] = 0;

        for (int index = prices.length - 1; index > -1; index--) {
            maxProfit(prices, index, 1);
            maxProfit(prices, index, 0);
            ahead = cur;
        }
        return ahead[1];
    }
}