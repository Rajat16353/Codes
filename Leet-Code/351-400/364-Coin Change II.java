// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

// Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

// You may assume that you have an infinite number of each kind of coin.

// The answer is guaranteed to fit into a signed 32-bit integer.

 

// Example 1:

// Input: amount = 5, coins = [1,2,5]
// Output: 4
// Explanation: there are four ways to make up the amount:
// 5=5
// 5=2+2+1
// 5=2+1+1+1
// 5=1+1+1+1+1
// Example 2:

// Input: amount = 3, coins = [2]
// Output: 0
// Explanation: the amount of 3 cannot be made up just with coins of 2.
// Example 3:

// Input: amount = 10, coins = [10]
// Output: 1
 

// Constraints:

// 1 <= coins.length <= 300
// 1 <= coins[i] <= 5000
// All the values of coins are unique.
// 0 <= amount <= 5000

// recursion with memoization
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        Arrays.stream(dp).forEach((row) -> Arrays.fill(row, -1));
        return recursion(n - 1, amount, coins, dp);
    }

    private int recursion(int idx, int amount, int[] coins, int[][] dp) {
        if (idx == 0) {
            return amount % coins[0] == 0 ? 1 : 0;
        }

        if (dp[idx][amount] != -1) return dp[idx][amount];

        int take = 0;
        if (amount >= coins[idx]) take = recursion(idx, amount - coins[idx], coins, dp);
        int notTake = recursion(idx - 1, amount, coins, dp);

        return dp[idx][amount] = take + notTake;
    }
}

// Tabulation
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        for (int a = 0; a <= amount; a++) {
            dp[0][a] = a % coins[0] == 0 ? 1 : 0;
        }
        
        for (int idx = 1; idx < n; idx++) {
            for (int tar = 0; tar <= amount; tar++) {
                int take = 0;
                if (tar >= coins[idx]) take = dp[idx][tar - coins[idx]];
                int notTake = dp[idx - 1][tar];

                dp[idx][tar] = take + notTake;
            }
        }
        return dp[n - 1][amount];
    }
}

// Tabulation with space optimisation
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        for (int a = 0; a <= amount; a++) {
            dp[a] = a % coins[0] == 0 ? 1 : 0;
        }
        
        for (int idx = 1; idx < n; idx++) {
            int[] cur = new int[amount + 1];
            for (int tar = 0; tar <= amount; tar++) {
                int take = 0;
                if (tar >= coins[idx]) take = cur[tar - coins[idx]];
                int notTake = dp[tar];

                cur[tar] = take + notTake;
            }
            dp = cur;
        }
        return dp[amount];
    }
}

// Optimised tabulation
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];

        dp[0] = 1;
        
        for (int coin: coins) {
            for (int amt = 1; amt < dp.length; amt++) {
                if (amt >= coin) {
                    dp[amt] += dp[amt - coin];
                }
            }
        }

        return dp[amount];
    }
}