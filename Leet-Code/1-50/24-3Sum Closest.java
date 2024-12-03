// Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.

// Return the sum of the three integers.

// You may assume that each input would have exactly one solution.

 

// Example 1:

// Input: nums = [-1,2,1,-4], target = 1
// Output: 2
// Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
// Example 2:

// Input: nums = [0,0,0], target = 1
// Output: 0
 

// Constraints:

// 3 <= nums.length <= 1000
// -1000 <= nums[i] <= 1000
// -104 <= target <= 104

// class Solution:
//     def threeSumClosest(self, nums: List[int], target: int) -> int:
//         nums.sort()
//         closest = 99999
//         res = 0
//         for k, a in enumerate(nums[:-2]):
//             if k > 0 and a == nums[k-1]:
//                 continue
//             i, j = k+1, len(nums)-1
//             while(i<j):
//                 three_sum = a + nums[i] + nums[j]
//                 if three_sum > target:
//                     j -= 1
//                 elif three_sum < target:
//                     i += 1
//                 else:
//                     return three_sum
//                 diff = abs(three_sum - target)
//                 if diff < closest:
//                     closest = diff
//                     res = three_sum
                
        
//         return res

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int minDiff = Integer.MAX_VALUE;

        Arrays.sort(nums);
        for(int k = 0; k < nums.length - 2; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while(i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                int absDiff = Math.abs(sum - target);
                if (minDiff > absDiff) {
                    minDiff = absDiff;
                    result = sum;
                }
                
                if (sum > target) {
                    j--;
                } else if (sum < target) {
                    i++;
                } else {
                    return sum;
                }
            }
        }
        return result;
    }
}