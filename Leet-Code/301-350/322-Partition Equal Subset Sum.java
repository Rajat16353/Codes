// Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

// Example 1:

// Input: nums = [1,5,11,5]
// Output: true
// Explanation: The array can be partitioned as [1, 5, 5] and [11].
// Example 2:

// Input: nums = [1,2,3,5]
// Output: false
// Explanation: The array cannot be partitioned into equal sum subsets.
 

// Constraints:

// 1 <= nums.length <= 200
// 1 <= nums[i] <= 100

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }

        if (sum % 2 != 0) return false;

        int[][] dp = new int[nums.length][sum/2 + 1];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return subsetSumToK(nums, nums.length - 1, sum/2, dp);

    }

    private static boolean subsetSumToK(int[] arr, int index, int target, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return (arr[0] == target);

        if (dp[index][target] != -1) return dp[index][target] == 1;

        boolean take = false;
        if (arr[index] <= target) take = subsetSumToK(arr, index - 1, target - arr[index], dp);
        boolean notTake = subsetSumToK(arr, index - 1, target, dp);

        dp[index][target] = take | notTake ? 1: 0;
        return dp[index][target] == 1;
    }
}

// Tabulation with optimised memory
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }

        if (sum % 2 != 0) return false;
        int k = sum/2;
        boolean[] dp = new boolean[k + 1];
        
        dp[0] = true;
        if (nums[0] < k) dp[nums[0]] = true;

        for (int index = 1; index < nums.length; index++) {
            boolean[] cur = new boolean[k + 1];
            cur[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[target];
                boolean take = false;
                if (nums[index] <= target) take = dp[target - nums[index]];

                cur[target] = take | notTake;
            }
            dp = cur;
        }

        return dp[k];
    }
}