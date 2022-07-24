// Given an array nums of size n, return the majority element.

// The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

 

// Example 1:

// Input: nums = [3,2,3]
// Output: 3
// Example 2:

// Input: nums = [2,2,1,1,1,2,2]
// Output: 2
 

// Constraints:

// n == nums.length
// 1 <= n <= 5 * 104
// -231 <= nums[i] <= 231 - 1
 
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int a: nums) {
            if (!map.containsKey(a))
                map.put(a,1);
            else
                map.put(a, map.get(a)+1);
        }
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > nums.length/2)
                return entry.getKey();
        }
        return -1;
    }
}

// Follow-up: Could you solve the problem in linear time and in O(1) space?

class Solution {
    public int majorityElement(int[] nums) {
        int count = 1, res = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] == res)
                count++;
            else {
                if (count == 0)
                    res = nums[i];
                else
                    count--;
            }
        }
        
        return res;
    }
}