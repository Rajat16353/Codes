// Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.

// A subarray is a contiguous part of an array.

 

// Example 1:

// Input: nums = [4,5,0,-2,-3,1], k = 5
// Output: 7
// Explanation: There are 7 subarrays with a sum divisible by k = 5:
// [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
// Example 2:

// Input: nums = [5], k = 9
// Output: 0
 

// Constraints:

// 1 <= nums.length <= 3 * 104
// -104 <= nums[i] <= 104
// 2 <= k <= 104

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        int count = 0;
        remainderCount.put(0, 1);
        int sum = 0;

        for(int n: nums) {
            sum += n;
            int remainder = sum % k;
            if (remainder < 0) remainder = remainder % k + k;
            if (remainderCount.containsKey(remainder)) count += remainderCount.get(remainder);

            remainderCount.put(remainder, remainderCount.getOrDefault(remainder, 0) + 1);
        }

        return count;
    }
}