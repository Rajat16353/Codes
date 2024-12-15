// You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.

// Return the minimum possible absolute difference.

 

// Example 1:

// example-1
// Input: nums = [3,9,7,3]
// Output: 2
// Explanation: One optimal partition is: [3,9] and [7,3].
// The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
// Example 2:

// Input: nums = [-36,36]
// Output: 72
// Explanation: One optimal partition is: [-36] and [36].
// The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
// Example 3:

// example-3
// Input: nums = [2,-1,0,4,-2,-9]
// Output: 0
// Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
// The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
 

// Constraints:

// 1 <= n <= 15
// nums.length == 2 * n
// -107 <= nums[i] <= 107

// Doesn't work on leetcode because values in the nums can be negative
class Solution {
    private Map<Integer, Boolean> subsetSumToK(int nums[], int k){
        Map<Integer, Boolean> dp = new HashMap<>();
        dp.put(0, true);

        if (nums[0] < k) dp.put(nums[0], true);

        for (int index = 1; index < nums.length; index++) {
            Map<Integer, Boolean> cur = new HashMap<>();
            cur.put(0, true);
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp.getOrDefault(target, false);
                boolean take = false;
                if (nums[index] <= target) take = dp.getOrDefault(target - nums[index], false);

                cur.put(target, take | notTake);
            }
            dp = cur;
        }
        return dp;
    }

    public int minimumDifference(int[] nums) {
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }

        Map<Integer, Boolean> dp = subsetSumToK(nums, sum);
        int minDiff = Integer.MAX_VALUE;

        for (int i = -sum; i < sum + 1; i++) {
            if (dp.getOrDefault(i, false)) {
                int s1 = i;
                int s2 = sum - i;
                minDiff = Math.min(minDiff, Math.abs(s1 - s2));
            }
        }

        return minDiff;
    }
}

// Using meet in the middle
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length/2;
        int totalSum = 0;

        for (int a: nums) {
            totalSum += a;
        }

        Map<Integer, List<Integer>> firstHalf = new HashMap<>();
        Map<Integer, List<Integer>> secondHalf = new HashMap<>();

        generateSubsetSum(nums, 0, n, 0, firstHalf, 0);
        generateSubsetSum(nums, n, 2 * n, 0, secondHalf, 0);

        secondHalf.forEach((k, v) -> Collections.sort(v));

        int minAbsDiff = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            minAbsDiff = Math.min(minAbsDiff, solve(firstHalf.get(i), secondHalf.get(n - i), totalSum));
        }

        return minAbsDiff;
    }

    private int solve(List<Integer> first, List<Integer> second, int goal) {
        int minAbsDiff = Integer.MAX_VALUE;
        for(int s1: first) {
            int pos = binarySearch(second, goal/2 - s1);

            minAbsDiff = Math.min(minAbsDiff, Math.abs(goal - 2 * (s1 + second.get(pos))));
            if (pos > 0) minAbsDiff = Math.min(minAbsDiff, Math.abs(goal - 2 *(s1 + second.get(pos - 1))));
        }
        return minAbsDiff;
    }

    private int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;

        while (low < high) {
            int mid = low + (high - low)/2;
            if (list.get(mid) < target) {
                low = mid + 1;
            } else if (list.get(mid) > target) {
                high = mid;
            } else {
                return mid;
            }
        }

        return low;
    }

    private void generateSubsetSum(int[] nums, int start, int end, int sum, Map<Integer, List<Integer>> subset, int numsTaken) {
        if (end == start) {
            subset.computeIfAbsent(numsTaken, k -> new ArrayList<>()).add(sum);
            return;
        }

        generateSubsetSum(nums, start + 1, end, sum + nums[start], subset, numsTaken + 1);
        generateSubsetSum(nums, start + 1, end, sum, subset, numsTaken);
    }
}