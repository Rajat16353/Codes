// Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

 

// Example 1:

// Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
// Output: 3
// Explanation: The repeated subarray with maximum length is [3,2,1].
// Example 2:

// Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
// Output: 5
// Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 

// Constraints:

// 1 <= nums1.length, nums2.length <= 1000
// 0 <= nums1[i], nums2[i] <= 100

class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    max = Math.max(max, dp[i + 1][j + 1]);
                } else {
                    dp[i + 1][j + 1] = 0;
                }
            }
        }
        return max;
    }
}