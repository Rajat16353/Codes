// Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

// If target is not found in the array, return [-1, -1].

// You must write an algorithm with O(log n) runtime complexity.

 

// Example 1:

// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
// Example 2:

// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]
// Example 3:

// Input: nums = [], target = 0
// Output: [-1,-1]
 

// Constraints:

// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums is a non-decreasing array.
// -109 <= target <= 109

// First approach
// class Solution:
//     def searchRange(self, nums: List[int], target: int) -> List[int]:
//         l = 0
//         r = len(nums)-1
//         out = [-1, -1]
//         while(l<=r):
//             mid = (l+r)//2          
//             if nums[mid] == target:
//                 out[0] = mid
//                 if mid < r and nums[mid+1] == target:
//                     while(mid < len(nums) and nums[mid] == target):
//                         mid += 1
//                     mid -= 1
//                     out[1] = mid
//                 if mid > l and nums[mid-1] == target:
//                     while(mid >= 0 and nums[mid] == target):
//                         mid -= 1
//                     mid += 1
//                     if out[1] == -1:
//                         out[1] = out[0]
//                         out[0] = mid
//                     else:
//                         out[0] = mid
//                 else:
//                     out[1] = mid
//                 break
//             if target < nums[mid]:
//                 r = mid - 1
//             else:
//                 l = mid + 1
//         return out

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int lb = lowerBound(nums, n, target);

        if (lb == n || nums[lb] != target) return new int[]{-1, -1};

        return new int[]{lb, upperBound(nums, n, target) - 1};
    }

    private int lowerBound(int[] nums, int n, int target) {
        int low = 0, high = n - 1;
        int ans = n;

        while(low <= high) {
            int mid = (low + high)/2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private int upperBound(int[] nums, int n, int target) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high)/2;

            if (nums[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }
}