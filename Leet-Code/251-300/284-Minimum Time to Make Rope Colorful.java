// Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the color of the ith balloon.

// Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the ith balloon from the rope.

// Return the minimum time Bob needs to make the rope colorful.

 

// Example 1:


// Input: colors = "abaac", neededTime = [1,2,3,4,5]
// Output: 3
// Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
// Bob can remove the blue balloon at index 2. This takes 3 seconds.
// There are no longer two consecutive balloons of the same color. Total time = 3.
// Example 2:


// Input: colors = "abc", neededTime = [1,2,3]
// Output: 0
// Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.
// Example 3:


// Input: colors = "aabaa", neededTime = [1,2,3,4,1]
// Output: 2
// Explanation: Bob will remove the balloons at indices 0 and 4. Each balloons takes 1 second to remove.
// There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.
 

// Constraints:

// n == colors.length == neededTime.length
// 1 <= n <= 105
// 1 <= neededTime[i] <= 104
// colors contains only lowercase English letters.

// Swapping max O(n)
class Solution {
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int minCost(String colors, int[] neededTime) {
        if (colors.length() == 1) {
            return 0;
        }

        int minCost = 0;
        
        for(int i = 1; i < colors.length(); i++) {
            char prev = colors.charAt(i - 1);
            char curr = colors.charAt(i);
            if (prev == curr) {
                if (neededTime[i] < neededTime[i - 1]) swap(neededTime, i, i - 1);
                minCost += neededTime[i - 1];
            }
        }

        return minCost;
    }
}

// Adding all and deducting max O(n)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        if (colors.length() == 1) {
            return 0;
        }

        int minCost = 0, sumCost = 0, maxCost = 0;
        
        for(int i = 0; i < colors.length(); i++) {
            if (i > 0 && colors.charAt(i - 1) != colors.charAt(i)) {
                minCost += sumCost - maxCost;
                sumCost = maxCost = 0;
            }
            sumCost += neededTime[i];
            maxCost = Math.max(maxCost, neededTime[i]);
        }

        minCost += sumCost - maxCost;

        return minCost;
    }
}