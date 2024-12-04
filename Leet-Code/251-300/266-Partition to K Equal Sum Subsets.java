// Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.

 

// Example 1:

// Input: nums = [4,3,2,3,5,2,1], k = 4
// Output: true
// Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
// Example 2:

// Input: nums = [1,2,3,4], k = 3
// Output: false
 

// Constraints:

// 1 <= k <= nums.length <= 16
// 1 <= nums[i] <= 104
// The frequency of each element is in the range [1, 4].

class Solution {
    private boolean canPartitionKSubsets(int[] nums, int target, int index, int k, boolean[] visited, int subsetSum) {
        if (k == 0) return true;

        if (target == subsetSum) {
            return canPartitionKSubsets(nums, target, 0, k - 1, visited, 0);
        }

        for(int i = index; i < nums.length; i++) {
            if (visited[i] || nums[i] + subsetSum > target) continue;

            visited[i] = true;
            if (canPartitionKSubsets(nums, target, i + 1, k, visited, subsetSum + nums[i])) return true;
            visited[i] = false;
        }

        return false;
    }
    
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int n: nums) {
            sum += n;
        }
        if (sum % k != 0) return false;

        boolean[] visited = new boolean[nums.length];

        return canPartitionKSubsets(nums, sum / k, 0, k, visited, 0);
    }
}