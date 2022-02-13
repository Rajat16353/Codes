// Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

 

// Example 1:

// Input: nums = [3,2,3]
// Output: [3]
// Example 2:

// Input: nums = [1]
// Output: [1]
// Example 3:

// Input: nums = [1,2]
// Output: [1,2]
 

// Constraints:

// 1 <= nums.length <= 5 * 104
// -109 <= nums[i] <= 109

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for(int a: nums) {
            if (!map.containsKey(a))
                map.put(a,1);
            else
                map.put(a, map.get(a)+1);
        }
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            // System.out.println(entry.getKey()+": "+entry.getValue());
            if (entry.getValue() > nums.length/3)
                list.add(entry.getKey());
        }
        return list;
    }
} 

// Follow up: Could you solve the problem in linear time and in O(1) space?

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int c1 = 0, c2 = 0, n1 = 0, n2 = 0;
        for(int i = 0; i< nums.length; i++) {
            if (nums[i] == n1)
                c1++;
            else if(nums[i] == n2)
                c2++;
            else if (c1 == 0) {
                n1 = nums[i];
                c1++;
            } else if (c2 == 0) {
                n2 = nums[i];
                c2++;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = 0;
        c2 = 0;
        for(int a: nums) {
            if (n1 == a)
                c1++;
            else if (n2 == a)
                c2++;
        }
        int l = nums.length;
        if (c1 > l/3 && c2 > l/3)
            return Arrays.asList(n1, n2);
        else if (c1 > l/3)
            return Arrays.asList(n1);
        else if (c2 > l/3)
            return Arrays.asList(n2);
        else
            return Arrays.asList();
    }
}