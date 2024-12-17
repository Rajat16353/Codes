// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

// Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

// You may assume that you have an infinite number of each kind of coin.

 

// Example 1:

// Input: coins = [1,2,5], amount = 11
// Output: 3
// Explanation: 11 = 5 + 5 + 1
// Example 2:

// Input: coins = [2], amount = 3
// Output: -1
// Example 3:

// Input: coins = [1], amount = 0
// Output: 0
 

// Constraints:

// 1 <= coins.length <= 12
// 1 <= coins[i] <= 231 - 1
// 0 <= amount <= 104

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int a = 1; a < amount + 1; a++) {
            for (int coin: coins) {
                if (a - coin >= 0) {
                    dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
                }
            }
        }

        return dp[amount] == amount+1 ? -1: dp[amount];
    }
}

// Recursion
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        int res = coinChange(coins, coins.length - 1, amount, dp);
        return res == 100000 ? -1: res;
    }

    private int coinChange(int[] coins, int index, int amount, int[][] dp) {
        if (index == 0) {
            if (amount % coins[0] == 0) return amount/coins[0];
            return 100000;
        }

        if (dp[index][amount] != -1) return dp[index][amount];

        int notTake = coinChange(coins, index - 1, amount, dp);
        int take = 100000;
        if (coins[index] <= amount) {
            take = 1 + coinChange(coins, index, amount - coins[index], dp);
        }
        
        return dp[index][amount] = Math.min(take, notTake);
    }
}

// Tabulation
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int target = 0; target <= amount; target++) {
            dp[0][target] = target % coins[0] == 0 ? target / coins[0] : 100000;
        }

        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= amount; target++) {
                int notTake = dp[index - 1][target];
                int take = 100000;
                if (coins[index] <= target) take = 1 + dp[index][target - coins[index]];
                
                dp[index][target] = Math.min(take, notTake);
            }
        }

        return dp[n - 1][amount] == 100000 ? -1: dp[n - 1][amount];
    }
}

// Space optimised tabulation
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int target = 0; target <= amount; target++) {
            dp[0][target] = target % coins[0] == 0 ? target / coins[0] : 100000;
        }

        for (int index = 1; index < n; index++) {
            for (int target = 0; target <= amount; target++) {
                int notTake = dp[index - 1][target];
                int take = 100000;
                if (coins[index] <= target) take = 1 + dp[index][target - coins[index]];
                
                dp[index][target] = Math.min(take, notTake);
            }
        }

        return dp[n - 1][amount] == 100000 ? -1: dp[n - 1][amount];
    }
}