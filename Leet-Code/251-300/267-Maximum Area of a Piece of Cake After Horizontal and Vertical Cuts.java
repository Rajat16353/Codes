// You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

// horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
// verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
// Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.

 

// Example 1:


// Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
// Output: 4 
// Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
// Example 2:


// Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
// Output: 6
// Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
// Example 3:

// Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
// Output: 9
 

// Constraints:

// 2 <= h, w <= 109
// 1 <= horizontalCuts.length <= min(h - 1, 105)
// 1 <= verticalCuts.length <= min(w - 1, 105)
// 1 <= horizontalCuts[i] < h
// 1 <= verticalCuts[i] < w
// All the elements in horizontalCuts are distinct.
// All the elements in verticalCuts are distinct.

class Solution {
    private int getMaxValue(int[] cuts, int border) {
        int[] afterCuts = new int[cuts.length + 1];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= cuts.length; i++) {
            if (i == cuts.length) {
                afterCuts[i] = border - cuts[i - 1];
            } else if (i == 0) {
                afterCuts[i] = cuts[i];
            } else {
                afterCuts[i] = cuts[i] - cuts[i - 1];
            }
            max = Math.max(max, afterCuts[i]);
        }
        return max;
    }

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxH = getMaxValue(horizontalCuts, h);
        int maxV = getMaxValue(verticalCuts, w);

        return (int)(((long)maxH * (long)maxV) % 1000000007);
    }
}