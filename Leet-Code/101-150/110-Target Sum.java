// You are given an integer array nums and an integer target.

// You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
// Return the number of different expressions that you can build, which evaluates to target.

 

// Example 1:

// Input: nums = [1,1,1,1,1], target = 3
// Output: 5
// Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
// -1 + 1 + 1 + 1 + 1 = 3
// +1 - 1 + 1 + 1 + 1 = 3
// +1 + 1 - 1 + 1 + 1 = 3
// +1 + 1 + 1 - 1 + 1 = 3
// +1 + 1 + 1 + 1 - 1 = 3
// Example 2:

// Input: nums = [1], target = 1
// Output: 1
 

// Constraints:

// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 1000

class Solution {
    public int findTargetSumWaysCount(int index, int total, Map<Pair<Integer, Integer> , Integer> cache, int[] nums, int target) {
        Pair<Integer, Integer> pair = new Pair(index, total);
        if (index == nums.length) {
            return total == target ? 1: 0;
        }
        if (cache.containsKey(pair)) {
            return cache.get(pair);
        }
        
        cache.put(pair, findTargetSumWaysCount(index+1, total + nums[index], cache, nums, target) + findTargetSumWaysCount(index+1, total - nums[index], cache, nums, target));
        return cache.get(pair);
    }
    public int findTargetSumWays(int[] nums, int target) {
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
        return findTargetSumWaysCount(0, 0, cache, nums, target);
    }
}

// Using the same solution as 355-Partitions With Given Difference.java
class Solution {
    public int countPartitions(int n, int d, int[] arr) {
		int totalSum = 0;
		for (int num: arr) {
			totalSum += num;
		}

		if (totalSum - d < 0 || (totalSum - d) % 2 != 0) return 0;

		int target = (totalSum - d)/2;
		int[] dp = new int[target + 1];
		dp[0] = 1;
		if (arr[0] <= target) dp[arr[0]] = 1;
		if (arr[0] == 0) dp[0] = 2;

		for (int idx = 1; idx < n; idx++) {
			int[] cur = new int[target + 1];
			for (int sum = 0; sum <= target; sum++) {
				int pick = 0;
				if (arr[idx] <= sum) pick = dp[sum - arr[idx]];
				int skip = dp[sum];

				cur[sum] = (skip + pick);
			}
			dp = cur;
		}
		
		return dp[target];
	}

    public int findTargetSumWays(int[] nums, int target) {
        return countPartitions(nums.length, target, nums);
    }
} 