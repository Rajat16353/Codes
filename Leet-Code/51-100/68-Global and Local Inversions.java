// You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].

// The number of global inversions is the number of the different pairs (i, j) where:

// 0 <= i < j < n
// nums[i] > nums[j]
// The number of local inversions is the number of indices i where:

// 0 <= i < n - 1
// nums[i] > nums[i + 1]
// Return true if the number of global inversions is equal to the number of local inversions.

 

// Example 1:

// Input: nums = [1,0,2]
// Output: true
// Explanation: There is 1 global inversion and 1 local inversion.
// Example 2:

// Input: nums = [1,2,0]
// Output: false
// Explanation: There are 2 global inversions and 1 local inversion.
 

// Constraints:

// n == nums.length
// 1 <= n <= 105
// 0 <= nums[i] < n
// All the integers of nums are unique.
// nums is a permutation of all the numbers in the range [0, n - 1].

class Solution {
    private int merge(int arr[], int left, int mid, int right) {
        int i = 0, j = 0, k = left;
        int inv_count = 0;
        
        int left_arr[] = Arrays.copyOfRange(arr, left, mid);
        int right_arr[] = Arrays.copyOfRange(arr, mid, right+1);
        
        while(i < left_arr.length && j < right_arr.length) {
            if (left_arr[i] <= right_arr[j])
                arr[k++] = left_arr[i++];
            else {
                arr[k++] = right_arr[j++];
                
                inv_count += mid - (left+i);
            }
        }
        
        while(i < left_arr.length)
            arr[k++] = left_arr[i++];
        
        while (j < right_arr.length)
            arr[k++] = right_arr[j++];
        
        return inv_count;
    }
    
    private int merge_sort(int arr[], int left, int right) {
        int inv_count = 0;
        
        if (left < right) {
            int mid = (left + right)/2;
            
            inv_count += merge_sort(arr, left, mid);
            inv_count += merge_sort(arr, mid+1, right);
            
            inv_count += merge(arr, left, mid+1, right);
        }
        return inv_count;
    }
    
    public boolean isIdealPermutation(int[] nums) {
        
        int local_inv_count = 0;
        
        for(int i = 0; i+1< nums.length; i++) {
            if (nums[i] > nums[i+1]) {
                local_inv_count++;
            }
        }
        
        int global_inv_count = merge_sort(nums, 0, nums.length - 1);     
        
        return global_inv_count == local_inv_count;     
    }
}