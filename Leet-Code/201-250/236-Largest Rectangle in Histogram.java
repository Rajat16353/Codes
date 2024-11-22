// Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

// Example 1:


// Input: heights = [2,1,5,6,2,3]
// Output: 10
// Explanation: The above is a histogram where width of each bar is 1.
// The largest rectangle is shown in the red area, which has an area = 10 units.
// Example 2:


// Input: heights = [2,4]
// Output: 4
 

// Constraints:

// 1 <= heights.length <= 105
// 0 <= heights[i] <= 104


// Brute Force
class Solution {
    private int[] getPreviousSmallerElement(int[] heights) {
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        int[] pse = new int[heights.length];

        for(int i = 0; i < heights.length; i++) {
            if (stack.isEmpty()) {
                pse[i] = -1;
            } else {
                while(!stack.isEmpty() && stack.peek().getValue() >= heights[i]) stack.pop();
                pse[i] = stack.isEmpty() ? -1: stack.peek().getKey();
            }
            stack.push(new Pair(i, heights[i]));
        }

        return pse;
    }

    private int[] getNextSmallerElement(int[] heights) {
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        int[] nse = new int[heights.length];

        for(int i = heights.length - 1; i > -1; i--) {
            if (stack.isEmpty()) {
                nse[i] = heights.length;
            } else {
                while(!stack.isEmpty() && stack.peek().getValue() >= heights[i]) stack.pop();
                nse[i] = stack.isEmpty() ? heights.length: stack.peek().getKey();
            }
            stack.push(new Pair(i, heights[i]));
        }

        return nse;
    }

    public int largestRectangleArea(int[] heights) {
        int[] pse = getPreviousSmallerElement(heights);
        int[] nse = getNextSmallerElement(heights);
        
        int maxArea = Integer.MIN_VALUE;

        for(int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (nse[i] - pse[i] - 1));
        }

        return maxArea;
    }
}

// Optimised approach
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> index = new Stack<>();
        Stack<Integer> value = new Stack<>();
        
        int maxArea = Integer.MIN_VALUE;

        for(int i = 0; i < heights.length; i++) {
            int area = 0;
            if (index.isEmpty() || (!value.isEmpty() && value.peek() <= heights[i])) {
                
            } else {
                while (!value.isEmpty() && heights[i] < value.peek()) {
                    index.pop();
                    int lse = index.isEmpty() ? -1: index.peek();
                    maxArea = Math.max(maxArea, value.pop() * (i - lse - 1));
                }
                value.push(heights[i]);
                index.push(i);
            }
            index.push(i);
            value.push(heights[i]);
        }

        while(!index.isEmpty()) {
            index.pop();
            int lse = index.isEmpty() ? -1: index.peek();
            maxArea = Math.max(maxArea, value.pop() * (heights.length - lse - 1));
        }

        return maxArea;
    }
}