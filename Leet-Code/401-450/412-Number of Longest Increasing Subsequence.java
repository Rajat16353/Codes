// Given an integer array nums, return the number of longest increasing subsequences.

// Notice that the sequence has to be strictly increasing.

 

// Example 1:

// Input: nums = [1,3,5,4,7]
// Output: 2
// Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
// Example 2:

// Input: nums = [2,2,2,2,2]
// Output: 5
// Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.
 

// Constraints:

// 1 <= nums.length <= 2000
// -106 <= nums[i] <= 106
// The answer is guaranteed to fit inside a 32-bit integer.

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int max = 1;
        Arrays.fill(dp, 1);

        int[] count = new int[n];
        Arrays.fill(count, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && 1 + dp[j] > dp[i]) {
                    dp[i] = dp[j] + 1;
                    count[i] = count[j];
                } else if (nums[j] < nums[i] && 1 + dp[j] == dp[i]) {
                    count[i] += count[j];
                }
            }
            max = Math.max(max, dp[i]);
        }

        int nos = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == max) nos += count[i];
        }

        return nos;
    }
}