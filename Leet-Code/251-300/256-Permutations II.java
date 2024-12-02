// Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

 

// Example 1:

// Input: nums = [1,1,2]
// Output:
// [[1,1,2],
//  [1,2,1],
//  [2,1,1]]
// Example 2:

// Input: nums = [1,2,3]
// Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

// Constraints:

// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10

class Solution {
    List<List<Integer>> result;

    private void backtrack(int[] nums, List<Integer> perm, Map<Integer, Integer> countMap) {        
        if (perm.size() == nums.length) {
            result.add(new ArrayList<>(perm));
            return;
        }
        
        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()) {
            if (entry.getValue() > 0) {
                countMap.put(entry.getKey(), entry.getValue() - 1);
                perm.add(entry.getKey());
                
                backtrack(nums, perm, countMap);

                countMap.put(entry.getKey(), entry.getValue() + 1);
                perm.remove(perm.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();

        for(int num: nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), countMap);
        return result;
    }
}