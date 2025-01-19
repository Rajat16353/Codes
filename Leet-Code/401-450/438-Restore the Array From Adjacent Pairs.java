// There is an integer array nums that consists of n unique elements, but you have forgotten it. However, you do remember every pair of adjacent elements in nums.

// You are given a 2D integer array adjacentPairs of size n - 1 where each adjacentPairs[i] = [ui, vi] indicates that the elements ui and vi are adjacent in nums.

// It is guaranteed that every adjacent pair of elements nums[i] and nums[i+1] will exist in adjacentPairs, either as [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can appear in any order.

// Return the original array nums. If there are multiple solutions, return any of them.

 

// Example 1:

// Input: adjacentPairs = [[2,1],[3,4],[3,2]]
// Output: [1,2,3,4]
// Explanation: This array has all its adjacent pairs in adjacentPairs.
// Notice that adjacentPairs[i] may not be in left-to-right order.
// Example 2:

// Input: adjacentPairs = [[4,-2],[1,4],[-3,1]]
// Output: [-2,4,1,-3]
// Explanation: There can be negative numbers.
// Another solution is [-3,1,4,-2], which would also be accepted.
// Example 3:

// Input: adjacentPairs = [[100000,-100000]]
// Output: [100000,-100000]
 

// Constraints:

// nums.length == n
// adjacentPairs.length == n - 1
// adjacentPairs[i].length == 2
// 2 <= n <= 105
// -105 <= nums[i], ui, vi <= 105
// There exists some nums that has adjacentPairs as its pairs.

class Solution {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int first = -1;

        for (int[] pair: adjacentPairs) {
            int u = pair[0];
            int v = pair[1];

            map.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            map.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] result = new int[map.size()];

        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            if (entry.getValue().size() == 1) {
                result[0] = entry.getKey();
                result[1] = entry.getValue().get(0);
                break;
            }
        }
        
        for (int i = 2; i < map.size(); i++) {
            int prev = result[i - 1];
            int last = result[i - 2];
            List<Integer> nbrs = map.get(prev);
            result[i] = nbrs.get(0) != last ? nbrs.get(0) : nbrs.get(1);
        }

        return result;
    }
}