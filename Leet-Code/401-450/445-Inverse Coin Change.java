// Variation of coin change.
// we are given dp array that we created while finding number of ways to make the target sum. We need to find the coins array, using which this dp array is created.
// Example:


// target = 10
// number of ways to make 10: 3
// Input: [1, 0, 1, 0, 1, 1, 2, 1, 2, 1, 3]
// Output: [2, 5, 6]

// brute force by generating dp array for each coin found
public class Main {
    public static void main(String[] args) {
        int[][] dp = new int[][]{{1, 0, 1, 0, 1, 1, 2, 1, 2, 1, 3},
                             {1, 1, 2, 2, 3, 4},
                             {1, 0, 1, 0}, 
                             {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};
        
        for (int i = 0; i < dp.length; i++) {
            List<Integer> ans = findCoinArray(dp[i].length - 1, dp[i]);
            System.out.println(ans);
        }
    }
    
    private static List<Integer> findCoinArray(int target, int[] dp) {
        List<Integer> coins = new ArrayList<>();
        
        int n = dp.length;
        for (int i = 1; i < n; i++) {
            if (dp[i] >= 1) {
                if (coins.size() == 0) {
                    coins.add(i);
                } else {
                    int currentWays = coinChange(coins, i);
                    if (dp[i] - currentWays > 0) {
                        coins.add(i);
                    }
                }
            }
        }
        
        return coins;
    }
    
    public static int coinChange(List<Integer> coins, int amount) {
        int n = coins.size();
        int[] dp = new int[amount + 1];

        for (int a = 0; a <= amount; a++) {
            dp[a] = a % coins.get(0) == 0 ? 1 : 0;
        }
        
        for (int idx = 1; idx < n; idx++) {
            int[] cur = new int[amount + 1];
            for (int tar = 0; tar <= amount; tar++) {
                if (tar >= coins.get(idx)) {
                    cur[tar] = dp[tar] + cur[tar - coins.get(idx)];
                }
            }
            dp = cur;
        }
        return dp[amount];
    }
}


// Optimised solution
public class Main {
    public static void main(String[] args) {
        int[][] dp = new int[][]{{1, 0, 1, 0, 1, 1, 2, 1, 2, 1, 3},
                             {1, 1, 2, 2, 3, 4},
                             {1, 0, 1, 0}, 
                             {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};
        
        for (int i = 0; i < dp.length; i++) {
            List<Integer> ans = constructOriginalArray(dp[i]);
            System.out.println(ans);
        }
    }
    
    public static List<Integer> constructOriginalArray(int dp[]) {
        int target = dp.length - 1;
        List<Integer> originalArr = new ArrayList<>();
        int coin, amount, n = dp.length;
        for (coin = 1; coin < n; coin++) {
            if (dp[coin] == 1) {
                for (amount = target; amount >= coin; amount--) {
                    dp[amount] = dp[amount] - dp[amount - coin];
                }
                originalArr.add(coin);
            }
        }
        return originalArr;
    }
}