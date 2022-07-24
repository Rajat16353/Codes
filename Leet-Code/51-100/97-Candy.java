// There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

// You are giving candies to these children subjected to the following requirements:

// Each child must have at least one candy.
// Children with a higher rating get more candies than their neighbors.
// Return the minimum number of candies you need to have to distribute the candies to the children.

 

// Example 1:

// Input: ratings = [1,0,2]
// Output: 5
// Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
// Example 2:

// Input: ratings = [1,2,2]
// Output: 4
// Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
// The third child gets 1 candy because it satisfies the above two conditions.
 

// Constraints:

// n == ratings.length
// 1 <= n <= 2 * 104
// 0 <= ratings[i] <= 2 * 104

class Solution {
    public int candy(int[] ratings) {
        int i = 0, j = 1;
        int candies = 0;
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        
        while(j < ratings.length) {
            if (ratings[j] > ratings[i]) {
                left[j] = left[i]+1;
            }
            i++;
            j++;
        }
        i = ratings.length-1;
        j = i-1;
        
        while(j >= 0) {
            if (ratings[j] > ratings[i]) {
                right[j] = right[i]+1;
            }
            i--;
            j--;
        }
        
        for(int k = 0; k < left.length; k++) {
            candies += Math.max(left[k], right[k]);
        }
        return candies;
    }
}