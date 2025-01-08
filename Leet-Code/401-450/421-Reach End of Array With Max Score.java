// You are given an integer array nums of length n.

// Your goal is to start at index 0 and reach index n - 1. You can only jump to indices greater than your current index.

// The score for a jump from index i to index j is calculated as (j - i) * nums[i].

// Return the maximum possible total score by the time you reach the last index.

 

// Example 1:

// Input: nums = [1,3,1,5]

// Output: 7

// Explanation:

// First, jump to index 1 and then jump to the last index. The final score is 1 * 1 + 2 * 3 = 7.

// Example 2:

// Input: nums = [4,3,1,3,2]

// Output: 16

// Explanation:

// Jump directly to the last index. The final score is 4 * 4 = 16.

 

// Constraints:

// 1 <= nums.length <= 105
// 1 <= nums[i] <= 105

// recursion and memoisation
class Solution {
    public long findMaximumScore(List<Integer> nums) {
        long[] dp = new long[nums.size()];
        return recursion(0, nums, dp);
    }

    private long recursion(int i, List<Integer> nums, long[] dp) {
        if (i == nums.size() - 1) return 0;

        if (dp[i] != 0) return dp[i];
        long max = -1;

        for (int j = i + 1; j < nums.size(); j++) {
            long score = (j - i) * nums.get(i) + recursion(j, nums, dp);
            max = Math.max(max, score);
        }

        return dp[i] = max;
    }
}

// tabulation
class Solution {
    public long findMaximumScore(List<Integer> nums) {
        int n = nums.size();
        long[] dp = new long[n];

        for (int i = n - 2; i >= 0; i--) {
            long max = -1;

            for (int j = i + 1; j < n; j++) {
                long score = (j - i) * nums.get(i) + dp[j];
                max = Math.max(max, score);
            }

            dp[i] = max;
        }
        return dp[0];
    }
}

// DP gives TLE
class Solution {
    public long findMaximumScore(List<Integer> nums) {
        long result = 0;
        int maxSeenSoFar = -1;
        
        for (int i = 0; i < nums.size() - 1; i++) {
            maxSeenSoFar = Math.max(maxSeenSoFar, nums.get(i));
            result += maxSeenSoFar;
        }
        return result;
    }
}