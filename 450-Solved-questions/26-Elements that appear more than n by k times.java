// Given an array of size n and a number k, find all elements that appear more than n/k times
// Difficulty Level : Medium
// Last Updated : 11 Feb, 2022
// Given an array of size n, find all elements in array that appear more than n/k times. For example, if the input arrays is {3, 1, 2, 2, 1, 2, 3, 3} and k is 4, then the output should be [2, 3]. Note that size of array is 8 (or n = 8), so we need to find all e

class Solution {
    public List<Integer> majorityElement(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for(int a: nums) {
            if (!map.containsKey(a))
                map.put(a,1);
            else
                map.put(a, map.get(a)+1);
        }
        
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > nums.length/k)
                list.add(entry.getKey());
        }
        return list;
    }
}