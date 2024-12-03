// You are given an array nums consisting of integers. You are also given a 2D array queries, where queries[i] = [posi, xi].

// For query i, we first set nums[posi] equal to xi, then we calculate the answer to query i which is the maximum sum of a 
// subsequence
//  of nums where no two adjacent elements are selected.

// Return the sum of the answers to all queries.

// Since the final answer may be very large, return it modulo 109 + 7.

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

// Example 1:

// Input: nums = [3,5,9], queries = [[1,-2],[0,-3]]

// Output: 21

// Explanation:
// After the 1st query, nums = [3,-2,9] and the maximum sum of a subsequence with non-adjacent elements is 3 + 9 = 12.
// After the 2nd query, nums = [-3,-2,9] and the maximum sum of a subsequence with non-adjacent elements is 9.

// Example 2:

// Input: nums = [0,-1], queries = [[0,-5]]

// Output: 0

// Explanation:
// After the 1st query, nums = [-5,-1] and the maximum sum of a subsequence with non-adjacent elements is 0 (choosing an empty subsequence).

 

// Constraints:

// 1 <= nums.length <= 5 * 104
// -105 <= nums[i] <= 105
// 1 <= queries.length <= 5 * 104
// queries[i] == [posi, xi]
// 0 <= posi <= nums.length - 1
// -105 <= xi <= 105

//Top down approach using recursion. Time limit exceeds error
class Solution {
    private int maximumSumSubsequence(int[] nums, int i, int[] dp) {
        if (i < 0) {
            return 0;
        }

        if (dp[i] != 0) return dp[i];

        if (i == 0) {
            return nums[i] < 0 ? 0: nums[i];
        }

        int pickCurrent = nums[i] + maximumSumSubsequence(nums, i - 2, dp);
        int skipCurrent = maximumSumSubsequence(nums, i - 1, dp);

        return dp[i] = Math.max(pickCurrent, skipCurrent);
    }

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int sum = 0;
        int modulo = 1000000007;
        int[] dp = new int[nums.length];

        for(int[] query: queries) {
            nums[query[0]] = query[1];
            Arrays.fill(dp, query[0], dp.length, 0);
            sum = (sum + maximumSumSubsequence(nums, nums.length - 1, dp)) % modulo;
        }

        return sum;
    }
}

// Bottom up approach using tabulation. Time limit exceeds
class Solution {
    private int maximumSumSubsequence(int[] nums, int[] dp, int index) {
        if (index == 0) {
            dp[0] = Math.max(nums[0], 0);
            index = 1;
        }
        for(int i = index; i < nums.length; i++) {
            int pickCurrent = i > 1 ? nums[i] + dp[i - 2]: nums[i];
            int skipCurrent = dp[i - 1];

            dp[i] = Math.max(pickCurrent, skipCurrent);
        }

        return dp[nums.length - 1];
    }

    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int sum = 0;
        int modulo = 1000000007;
        int[] dp = new int[nums.length + 1];
        dp[0] = Math.max(0, nums[0]);

        maximumSumSubsequence(nums, dp, 1);

        for(int[] query: queries) {
            nums[query[0]] = query[1];
            sum = (sum + maximumSumSubsequence(nums, dp, query[0])) % modulo;
        }

        return sum;
    }
}