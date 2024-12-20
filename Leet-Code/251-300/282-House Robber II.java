// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

// Example 1:

// Input: nums = [2,3,2]
// Output: 3
// Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
// Example 2:

// Input: nums = [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
// Total amount you can rob = 1 + 3 = 4.
// Example 3:

// Input: nums = [1,2,3]
// Output: 3
 

// Constraints:

// 1 <= nums.length <= 100
// 0 <= nums[i] <= 1000

class Solution {
    private int robDp(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        int prev2 = nums[0];
        int prev1 = Math.max(prev2, nums[1]);

        for(int i = 2; i < nums.length; i++) {
            int pick = nums[i] + prev2;
            int skip = prev1;

            prev2 = prev1;
            prev1 = Math.max(pick, skip);
        }

        return prev1;
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] pickFirst = new int[nums.length - 1], pickLast = new int[nums.length - 1];
        int j = 0, k = 0;

        for(int i = 0; i < nums.length; i++) {
            if (i != 0) pickLast[j++] = nums[i];
            if (i != nums.length - 1) pickFirst[k++] = nums[i];
        }

        return Math.max(robDp(pickFirst), robDp(pickLast));
    }
}