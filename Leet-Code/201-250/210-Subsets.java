// Given an integer array nums of unique elements, return all possible 
// subsets
//  (the power set).

// The solution set must not contain duplicate subsets. Return the solution in any order.

 

// Example 1:

// Input: nums = [1,2,3]
// Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// Example 2:

// Input: nums = [0]
// Output: [[],[0]]
 

// Constraints:

// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10
// All the numbers of nums are unique.

// Using recursion
class Solution {
    List<List<Integer>> subSets; 
    List<Integer> subset;
    private void recursiveAdd(int[] nums, int curr) {
        if (curr >= nums.length) {
            subSets.add(new ArrayList<>(subset));
            return;
        }
        subset.add(nums[curr]);
        recursiveAdd(nums, curr + 1);
        subset.remove(subset.size() - 1);
        recursiveAdd(nums, curr + 1);
    }

    public List<List<Integer>> subsets(int[] nums) {
        subSets = new ArrayList<>();
        subset = new ArrayList<>();
        recursiveAdd(nums, 0);
        return subSets;
    }
}

// Using bit manipulation
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> subSets = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < Math.pow(2, n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (((int)(Math.pow(2, j)) & i) != 0) {
                    subset.add(nums[j]);
                }
            }
            subSets.add(subset);
        }
        return subSets;
    }
}