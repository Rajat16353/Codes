// The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagram:

// A chess knight can move as indicated in the chess diagram below:


// We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


// Given an integer n, return how many distinct phone numbers of length n we can dial.

// You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

// As the answer may be very large, return the answer modulo 109 + 7.

 

// Example 1:

// Input: n = 1
// Output: 10
// Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
// Example 2:

// Input: n = 2
// Output: 20
// Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
// Example 3:

// Input: n = 3131
// Output: 136006598
// Explanation: Please take care of the mod.
 

// Constraints:

// 1 <= n <= 5000

// Recursion with memoisation
class Solution {
    final int MOD = 1000000007;
    public int knightDialer(int n) {
        Map<Integer, int[]> dialer = new HashMap<>();
        int[][] dp = new int[10][n + 1];

        dialer.put(0, new int[]{4, 6});
        dialer.put(1, new int[]{6, 8});
        dialer.put(2, new int[]{7, 9});
        dialer.put(3, new int[]{4, 8});
        dialer.put(4, new int[]{0, 3, 9});
        dialer.put(5, new int[0]);
        dialer.put(6, new int[]{0, 1, 7});
        dialer.put(7, new int[]{2, 6});
        dialer.put(8, new int[]{1, 3});
        dialer.put(9, new int[]{2, 4});

        int ways = 0;
        for (int i = 0; i <= 9; i++) {
            ways = (ways + recursion(i, n, 1, dialer, dp)) % MOD;
        }

        return ways;
    }

    private int recursion(int i, int n, int size, Map<Integer, int[]> dialer, int[][] dp) {
        if (size == n) {
            return 1;
        }

        if (dp[i][size] != 0) return dp[i][size];

        int ways = 0;
        for (int num: dialer.get(i)) {
            ways = (ways + recursion(num, n, size + 1, dialer, dp)) % MOD;
        }

        return dp[i][size] = ways;
    }
}

// tabulation
class Solution {
    final int MOD = 1000000007;
    public int knightDialer(int n) {
        Map<Integer, int[]> dialer = new HashMap<>();
        int[][] dp = new int[10][n + 1];

        dialer.put(0, new int[]{4, 6});
        dialer.put(1, new int[]{6, 8});
        dialer.put(2, new int[]{7, 9});
        dialer.put(3, new int[]{4, 8});
        dialer.put(4, new int[]{0, 3, 9});
        dialer.put(5, new int[0]);
        dialer.put(6, new int[]{0, 1, 7});
        dialer.put(7, new int[]{2, 6});
        dialer.put(8, new int[]{1, 3});
        dialer.put(9, new int[]{2, 4});

        for (int i = 0; i < 10; i++) dp[i][n] = 1;

        int totalCount = 0;

        for (int size = n - 1; size > 0; size--) {
            for (int i = 0; i <= 9; i++) {
                int ways = 0;
                
                for (int num: dialer.get(i)) {
                    ways = (ways + dp[num][size + 1]) % MOD;
                }

                dp[i][size] = ways;
            }
        }

        for (int i = 0; i <= 9; i++) {
            totalCount = (totalCount + dp[i][1]) % MOD;
        }

        return totalCount;
    }
}

// tabulation with optimised memory
class Solution {
    final int MOD = 1000000007;
    public int knightDialer(int n) {
        Map<Integer, int[]> dialer = new HashMap<>();
        int[] dp = new int[10];

        dialer.put(0, new int[]{4, 6});
        dialer.put(1, new int[]{6, 8});
        dialer.put(2, new int[]{7, 9});
        dialer.put(3, new int[]{4, 8});
        dialer.put(4, new int[]{0, 3, 9});
        dialer.put(5, new int[0]);
        dialer.put(6, new int[]{0, 1, 7});
        dialer.put(7, new int[]{2, 6});
        dialer.put(8, new int[]{1, 3});
        dialer.put(9, new int[]{2, 4});

        for (int i = 0; i <= 9; i++) dp[i] = 1;

        int totalCount = 0;

        for (int size = n - 1; size > 0; size--) {
            int[] cur = new int[10];
            for (int i = 0; i <= 9; i++) {
                int ways = 0;
                
                for (int num: dialer.get(i)) {
                    ways = (ways + dp[num]) % MOD;
                }

                cur[i] = ways;
            }
            dp = cur;
        }

        for (int i = 0; i <= 9; i++) {
            totalCount = (totalCount + dp[i]) % MOD;
        }

        return totalCount;
    }
}