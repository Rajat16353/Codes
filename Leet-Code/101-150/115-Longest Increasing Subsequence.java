// Given an integer array nums, return the length of the longest strictly increasing subsequence.

// A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

// Example 1:

// Input: nums = [10,9,2,5,3,7,101,18]
// Output: 4
// Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
// Example 2:

// Input: nums = [0,1,0,3,2,3]
// Output: 4
// Example 3:

// Input: nums = [7,7,7,7,7,7,7]
// Output: 1
 

// Constraints:

// 1 <= nums.length <= 2500
// -104 <= nums[i] <= 104
 

// Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);
        
        for (int i = nums.length-1; i > -1; i--) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]  < nums[j]) {
                    lis[i] = Math.max(lis[i], 1 + lis[j]);
                }
            }
        }
        return Arrays.stream(lis).summaryStatistics().getMax();
    }
}

// using recursion and memoization
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        return recursion(0, -1, nums, dp);
    }

    private int recursion(int idx, int prevIdx, int[] nums, int[][] dp) {
        if (idx == nums.length) {
            return 0;
        }

        if (dp[idx][prevIdx + 1] != 0) return dp[idx][prevIdx + 1];

        int take = 0;
        if (prevIdx == -1 || nums[idx] > nums[prevIdx]) take = 1 + recursion(idx + 1, idx, nums, dp);
        int notTake = recursion(idx + 1, prevIdx, nums, dp);

        return dp[idx][prevIdx + 1] = Math.max(take, notTake);
    }
}

// Using binary search
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        List<Integer> sortedList = new ArrayList<>();
        sortedList.add(nums[0]);

        for (int i = 1; i < n; i++) {
            if (nums[i] > sortedList.get(sortedList.size() - 1)) {
                sortedList.add(nums[i]);
            } else {
                int idx = lowerBound(nums[i], sortedList);
                sortedList.set(idx, nums[i]);   
            }
        }

        return sortedList.size();
    }

    private int lowerBound(int num, List<Integer> list) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            if (list.get(mid) >= num) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}