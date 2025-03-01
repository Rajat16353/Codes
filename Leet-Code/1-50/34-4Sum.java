// Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

// 0 <= a, b, c, d < n
// a, b, c, and d are distinct.
// nums[a] + nums[b] + nums[c] + nums[d] == target
// You may return the answer in any order.

 

// Example 1:

// Input: nums = [1,0,-1,0,-2,2], target = 0
// Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// Example 2:

// Input: nums = [2,2,2,2,2], target = 8
// Output: [[2,2,2,2]]
 

// Constraints:

// 1 <= nums.length <= 200
// -109 <= nums[i] <= 109
// -109 <= target <= 109

// First approach

// class Solution:
//     def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
//         out = []
//         nums.sort()
//         n = len(nums)
//         for i in range(n-1):
//             if i > 0 and nums[i] == nums[i-1]:
//                 continue
//             for j in range(i+1, n-2):
//                 if j > i+1 and nums[j] == nums[j-1]:
//                     continue
//                 l, r = j+1, n-1
//                 while(l<r):
//                     summ = nums[i] + nums[j] + nums[l] + nums[r]
//                     if summ > target:
//                         r -= 1
//                     elif summ < target :
//                         l += 1
//                     else:
//                         out.append([nums[i], nums[j], nums[l], nums[r]])
//                         l += 1
//                         while(nums[l] == nums[l-1] and l<r):
//                             l += 1
//         return out

class Solution {
    private List<Integer> createList(int[] nums) {
        List<Integer> quad = new ArrayList<>();
        for(int n: nums) {
            quad.add(n);
        }
        return quad;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if ((i > 0 && nums[i] == nums[i - 1])) continue;

            for (int j = i + 1; j < nums.length - 2;j++) {
                if (i != j - 1 && nums[j] == nums[j - 1]) continue;

                int l = j + 1;
                int r = nums.length - 1;

                while (l < r) {
                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[l] + (long)nums[r];
                    if (sum > target) {
                        r--;
                    } else if (sum < target) {
                        l++;
                    } else {
                        System.out.println(sum);
                        result.add(createList(new int[]{nums[i], nums[j], nums[l], nums[r]}));
                        l++;
                        while (l < r && nums[l] == nums[l - 1]) l++;
                    }
                }
            }
        }
        return result;
    }
}