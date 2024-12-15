// You are given an integer array nums and an integer goal.

// You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal. That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).

// Return the minimum possible value of abs(sum - goal).

// Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.

 

// Example 1:

// Input: nums = [5,-7,3,5], goal = 6
// Output: 0
// Explanation: Choose the whole array as a subsequence, with a sum of 6.
// This is equal to the goal, so the absolute difference is 0.
// Example 2:

// Input: nums = [7,-9,15,-2], goal = -5
// Output: 1
// Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
// The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
// Example 3:

// Input: nums = [1,2,3], goal = -7
// Output: 7
 

// Constraints:

// 1 <= nums.length <= 40
// -107 <= nums[i] <= 107
// -109 <= goal <= 109

// With inbuilt collections binary search
class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        generateAllSums(nums, 0, n/2, 0, first);
        generateAllSums(nums, n/2, n, 0, second);

        Collections.sort(second);

        int l = 0;
        int minAbsDiff = Integer.MAX_VALUE;
        for (int firstSubSetSum: first) {
            int right = goal - firstSubSetSum;

            if (right < second.get(0)) {
                minAbsDiff = Math.min(minAbsDiff, Math.abs(firstSubSetSum + second.get(0) - goal));
            } else if (right > second.get(second.size() - 1)) {
                minAbsDiff = Math.min(minAbsDiff, Math.abs(firstSubSetSum + second.get(second.size() - 1) - goal));
            } else {
                int pos = Collections.binarySearch(second, right); //If exact match not found, binarySearch in java returns (-(insertionPosition) - 1)

                if (pos >= 0) {
                    return 0;
                }
                pos = (-1 * (pos + 1));
                minAbsDiff = Math.min(minAbsDiff, Math.abs(firstSubSetSum + second.get(pos) - goal));
                minAbsDiff = Math.min(minAbsDiff, Math.abs(firstSubSetSum + second.get(pos - 1) - goal));
            }
        }

        return minAbsDiff;
    }

    private void generateAllSums(int[] nums, int start, int end, int sum, List<Integer> subsetSums) {
        if (start == end) {
            subsetSums.add(sum);
            return;
        }

        generateAllSums(nums, start + 1, end, sum + nums[start], subsetSums);
        generateAllSums(nums, start + 1, end, sum, subsetSums);
    }
}

// with self implemented binarysearch
class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        generateAllSums(nums, 0, n/2, 0, first);
        generateAllSums(nums, n/2, n, 0, second);

        Collections.sort(second);

        int l = 0;
        int minAbsDiff = Integer.MAX_VALUE;
        for (int firstSubSetSum: first) {
            int right = goal - firstSubSetSum;

            int pos = binarySearch(second, right);
            minAbsDiff = Math.min(minAbsDiff, Math.abs(firstSubSetSum + second.get(pos) - goal));
            if (pos > 0) minAbsDiff = Math.min(minAbsDiff, Math.abs(firstSubSetSum + second.get(pos - 1) - goal));
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

    private void generateAllSums(int[] nums, int start, int end, int sum, List<Integer> subsetSums) {
        if (start == end) {
            subsetSums.add(sum);
            return;
        }

        generateAllSums(nums, start + 1, end, sum + nums[start], subsetSums);
        generateAllSums(nums, start + 1, end, sum, subsetSums);
    }
}