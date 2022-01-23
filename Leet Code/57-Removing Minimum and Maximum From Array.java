// You are given a 0-indexed array of distinct integers nums.

// There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.

// A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.

// Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.

 

// Example 1:

// Input: nums = [2,10,7,5,4,1,8,6]
// Output: 5
// Explanation: 
// The minimum element in the array is nums[5], which is 1.
// The maximum element in the array is nums[1], which is 10.
// We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
// This results in 2 + 3 = 5 deletions, which is the minimum number possible.
// Example 2:

// Input: nums = [0,-4,19,1,8,-2,-3,5]
// Output: 3
// Explanation: 
// The minimum element in the array is nums[1], which is -4.
// The maximum element in the array is nums[2], which is 19.
// We can remove both the minimum and maximum by removing 3 elements from the front.
// This results in only 3 deletions, which is the minimum number possible.
// Example 3:

// Input: nums = [101]
// Output: 1
// Explanation:  
// There is only one element in the array, which makes it both the minimum and maximum element.
// We can remove it with 1 deletion.
 

// Constraints:

// 1 <= nums.length <= 105
// -105 <= nums[i] <= 105
// The integers in nums are distinct.

class Solution {
    public int minimumDeletions(int[] nums) {
        if (nums.length == 1)
            return 1;
        int min, max, i, j, len = nums.length;
        if (len % 2 == 0) {
            if (nums[0] > nums[1]){
                min = 1;
                max = 0;
            } else {
                min = 0;
                max = 1;
            }
            i = 2;
            j = 3;
        } else {
            min = max = 0;
            i = 1;
            j = 2;
        }
        for (; j < len;) {
            if (nums[i] > nums[j]) {
                if (nums[i] > nums[max]) {
                    max = i;
                }
                if (nums[j] < nums[min]) {
                    min = j;
                }
            } else {
                if (nums[j] > nums[max]) {
                    max = j;
                }
                if (nums[i] < nums[min]) {
                    min = i;
                }
            }
            i += 2;
            j += 2;
        }
        
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        
        return Math.min(max+1, Math.min(len-min, min + 1 + len - max));
    }
}