// Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.

// Note that:

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
// A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).
 

// Example 1:

// Input: nums = [3,6,9,12]
// Output: 4
// Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
// Example 2:

// Input: nums = [9,4,7,2,10]
// Output: 3
// Explanation:  The longest arithmetic subsequence is [4,7,10].
// Example 3:

// Input: nums = [20,1,15,3,10,5,8]
// Output: 4
// Explanation:  The longest arithmetic subsequence is [20,15,10,5].
 

// Constraints:

// 2 <= nums.length <= 1000
// 0 <= nums[i] <= 500

// recursion and memoisation
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        Map<Integer, Integer> dp[] = new HashMap[n];

        int longest = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                longest = Math.max(longest, 2 + recursion(i, nums[j] - nums[i], nums, dp));
            }
        }

        return longest;
    }

    private int recursion(int i, int diff, int[] nums, Map<Integer, Integer> dp[]) {
        if (i < 0) return 0;

        if (dp[i].containsKey(diff)) return dp[i].get(diff);

        int ans = 0;
        for (int j = i - 1; j >= 0; j--) {
            if (diff == nums[i] - nums[j]) {
                ans = Math.max(ans, 1 + recursion(j, diff, nums, dp));
            }
        }

        dp[i].put(diff, ans);
        return ans;
    }
}

// tabulation
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int longest = 2;
        Map<Integer, Integer> dp[] = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = nums[i] - nums[j];
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                longest = Math.max(longest, dp[i].get(diff));
            }   
        }

        return longest;
    }
}