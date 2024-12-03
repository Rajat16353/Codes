// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

// Notice that the solution set must not contain duplicate triplets.

 

// Example 1:

// Input: nums = [-1,0,1,2,-1,-4]
// Output: [[-1,-1,2],[-1,0,1]]
// Example 2:

// Input: nums = []
// Output: []
// Example 3:

// Input: nums = [0]
// Output: []
 

// Constraints:

// 0 <= nums.length <= 3000
// -105 <= nums[i] <= 105

// class Solution:
//     def threeSum(self, nums: List[int]) -> List[List[int]]:
//         out = []
//         nums.sort()
//         for k, a in enumerate(nums):
//             if k > 0 and a == nums[k-1]:
//                 continue
//             i, j = k+1, len(nums)-1
//             while(i<j):
//                 three_sum = a + nums[i] + nums[j]
//                 if three_sum > 0:
//                     j -= 1
//                 elif three_sum < 0:
//                     i += 1
//                 else:
//                     out.append([a,nums[i],nums[j]])
//                     i += 1
//                     while(nums[i] == nums[i-1] and i < j):
//                         i += 1
        
//         return out

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        for(int k = 0; k < nums.length - 2; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while(i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum > 0) {
                    j--;
                } else if (sum < 0) {
                    i++;
                } else {
                    result.add(Arrays.asList(new int[]{nums[k], nums[i], nums[j]}));
                    i++;
                    j--;
                    while(i < j && nums[i] == nums[i - 1] && i < j) i++;
                    while(i < j && nums[j] == nums[j + 1] && i < j) j--;
                }
            }
        }
        return result;
    }
}