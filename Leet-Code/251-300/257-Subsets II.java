// Given an integer array nums that may contain duplicates, return all possible 
// subsets
//  (the power set).

// The solution set must not contain duplicate subsets. Return the solution in any order.

 

// Example 1:

// Input: nums = [1,2,2]
// Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
// Example 2:

// Input: nums = [0]
// Output: [[],[0]]
 

// Constraints:

// 1 <= nums.length <= 10
// -10 <= nums[i] <= 10

class Solution {
    private void backtrack(int[] nums, int curr, List<Integer> subset,List<List<Integer>> result) {
        if (curr >= nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        subset.add(nums[curr]);
        backtrack(nums, curr + 1, subset, result);
        subset.remove(subset.size() - 1);

        while (curr + 1 < nums.length && nums[curr] == nums[curr + 1]) curr++;
        backtrack(nums, curr + 1, subset, result);

    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }
}