// You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.

// If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.

// Return the maximum coins you can collect by bursting the balloons wisely.

 

// Example 1:

// Input: nums = [3,1,5,8]
// Output: 167
// Explanation:
// nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
// coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
// Example 2:

// Input: nums = [1,5]
// Output: 10
 

// Constraints:

// n == nums.length
// 1 <= n <= 300
// 0 <= nums[i] <= 100

// Using recurison and memoisation
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Arrays.stream(nums).forEach(num -> list.add(num));
        list.add(1);
        int s = list.size();
        int[][] dp = new int[s][s];

        return recursion(1, n, list, dp);
    }

    private int recursion(int i, int j, List<Integer> list, int[][] dp) {
        if (i > j) return 0;

        if (dp[i][j] != 0) return dp[i][j];
        int maxi = Integer.MIN_VALUE;

        for (int k = i; k <= j; k++) {
            int cost = list.get(i - 1) * list.get(k) * list.get(j + 1) + 
            recursion(i, k - 1, list, dp) + recursion(k + 1, j, list, dp);
            maxi = Math.max(maxi, cost);
        }

        return dp[i][j] = maxi;
    }
}

// Tabulation
class Solution {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        Arrays.stream(nums).forEach(num -> list.add(num));
        list.add(1);
        int s = list.size();
        int[][] dp = new int[s][s];

        for (int i = n; i >= 1; i--) {
            for (int j = i; j <= n; j++) {
                if (i > j) continue;
                int maxi = Integer.MIN_VALUE;

                for (int k = i; k <= j; k++) {
                    int cost = list.get(i - 1) * list.get(k) * list.get(j + 1) + 
                    dp[i][k - 1] + dp[k + 1][j];
                    maxi = Math.max(maxi, cost);
                }

                dp[i][j] = maxi;
            }
        }

        return dp[1][n];
    }
}