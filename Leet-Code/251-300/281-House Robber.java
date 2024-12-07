// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

// Example 1:

// Input: nums = [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
// Total amount you can rob = 1 + 3 = 4.
// Example 2:

// Input: nums = [2,7,9,3,1]
// Output: 12
// Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
// Total amount you can rob = 2 + 9 + 1 = 12.
 

// Constraints:

// 1 <= nums.length <= 100
// 0 <= nums[i] <= 400

// Top Down approach
class Solution {
    private int rob(int[] nums, int index, int[] dp) {
        if (index < 0) return 0;
        if (index == 0) return nums[index];

        if(dp[index] != -1) return dp[index];

        int robHouse = nums[index] + rob(nums, index - 2, dp);
        int skipHouse = rob(nums, index - 1, dp);

        return dp[index] = Math.max(robHouse, skipHouse);
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        return rob(nums, nums.length - 1, dp);
    }
}

// Bottom up approach
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        int[] dp = new int[nums.length + 1];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        
        for(int i = 2; i < nums.length; i++) {
            int robHouse = nums[i] + dp[i - 2];
            int skipHouse = dp[i - 1];

            dp[i] = Math.max(robHouse, skipHouse);
        }

        return dp[nums.length - 1];
    }
}

// Bottom up approach space optimised
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        int prev2 = nums[0];
        int prev1 = Math.max(prev2, nums[1]);
        
        for(int i = 2; i < nums.length; i++) {
            int robHouse = nums[i] + prev2;
            int skipHouse = prev1;

            prev2 = prev1;
            prev1 = Math.max(robHouse, skipHouse);
        }

        return prev1;
    }
}