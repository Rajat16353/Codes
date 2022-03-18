// Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

// We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

// You must solve this problem without using the library's sort function.

 

// Example 1:

// Input: nums = [2,0,2,1,1,0]
// Output: [0,0,1,1,2,2]
// Example 2:

// Input: nums = [2,0,1]
// Output: [0,1,2]
 

// Constraints:

// n == nums.length
// 1 <= n <= 300
// nums[i] is either 0, 1, or 2.
 

// Follow up: Could you come up with a one-pass algorithm using only constant extra space?

class Solution {
    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for(int num: nums) {
            count[num] += 1;
        }
        
        for(int i=0; i<nums.length; i++) {
            if(count[0] > 0){
                nums[i] = 0;
                count[0] -= 1;
            }
            else if (count[1] > 0) {
                nums[i] = 1;
                count[1] -= 1;
            } else {
                nums[i] = 2;
                count[2] -= 1;
            }
        }
    }
}

//alternate
class Solution {
    
    private void swap(int nums[], int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void sortColors(int[] nums) {
        int l = 0, r = nums.length-1, i = 0;
        while (i <= r) {
            if (nums[i] >= 2) {
                swap(nums, i, r);
                r--;
            } else if (nums[i] < 1) {
                swap(nums, l, i);
                l++;
                i++;
            } else {
                i++;
            }
        }
    }
}