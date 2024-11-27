// Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

 

// Example 1:

// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// Example 2:

// Input: nums = [0,1]
// Output: [[0,1],[1,0]]
// Example 3:

// Input: nums = [1]
// Output: [[1]]
 

// Constraints:

// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// All the integers of nums are unique.

// With extra memory
class Solution {
    List<List<Integer>> result;
    private void recursivePermute(int[] nums, List<Integer> permutation, boolean[] visited) {
        if(permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                permutation.add(nums[i]);
                recursivePermute(nums, permutation, visited);
                permutation.remove(permutation.size() - 1);
                visited[i] = false;
            }
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        recursivePermute(nums, new ArrayList<>(), new boolean[nums.length]);
        return result;
    }
}

// Without using extra memory
class Solution {
    List<List<Integer>> result;
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private List<Integer> createList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        return list;
    }

    private void recursivePermute(int[] nums, int index) {
        if (index == nums.length) {
            result.add(createList(nums));
            return;
        }
        for(int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            recursivePermute(nums, index + 1);
            swap(nums, i, index);
        }
    }
    
    public List<List<Integer>> permute(int[] nums) {
        result = new ArrayList<>();
        recursivePermute(nums, 0);
        return result;
    }
}