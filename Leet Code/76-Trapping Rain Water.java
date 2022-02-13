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