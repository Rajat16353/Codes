// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

// Example 1:


// Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
// Output: 6
// Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
// Example 2:

// Input: height = [4,2,0,3,2,5]
// Output: 9
 

// Constraints:

// n == height.length
// 1 <= n <= 2 * 104
// 0 <= height[i] <= 105

class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length-1;
        int maxL = height[l];
        int maxR = height[r];
        int water = 0;
        
        while(l < r) {
            if (maxL <= maxR) {
                water += maxL-height[l] > 0 ? maxL-height[l]: 0;
                l++;
                if (maxL < height[l])
                    maxL = height[l];
            } else {
                water += maxR-height[r] > 0 ? maxR-height[r]: 0;
                r--;
                if (maxR < height[r])
                    maxR = height[r];
            }
        }
        return water;
    }
}

// Using DP
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.max(Math.min(leftMax[i], rightMax[i]) - height[i], 0);
        }

        return water;
    }
}