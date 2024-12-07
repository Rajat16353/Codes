// A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

// Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

// If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

 

// Example 1:

// Input: stones = [0,1,3,5,6,8,12,17]
// Output: true
// Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
// Example 2:

// Input: stones = [0,1,2,3,4,8,9,11]
// Output: false
// Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.
 

// Constraints:

// 2 <= stones.length <= 2000
// 0 <= stones[i] <= 231 - 1
// stones[0] == 0
// stones is sorted in a strictly increasing order.

// Top down approach
class Solution {
    private boolean canCross(int[] stones, int index, int k, Map<Pair<Integer, Integer>, Boolean> dp) {
        if (k == 0) {
            return false;
        }

        Pair<Integer, Integer> indexK = new Pair(index, k);

        if (dp.containsKey(indexK)) return dp.get(indexK);

        if (index == stones.length - 1) {
            return true;
        }

        for(int i = k - 1; i <= k + 1; i++) {
            int j = 1;
            while(index + j < stones.length && (stones[index + j] - stones[index]) < i) j++;
            if (index + j < stones.length && (stones[index + j] - stones[index]) == i && canCross(stones, index + j, i, dp)) {
                dp.put(indexK, true);
                return true;
            }
        }

        dp.put(indexK, false);
        return false;
    }
    
    public boolean canCross(int[] stones) {
        if (stones[1] - stones[0] > 1) {
            return false;
        }

        Map<Pair<Integer, Integer>, Boolean> dp = new HashMap<>();

        return canCross(stones, 1, 1, dp);
    }
}

// Bottom Up approach
class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] - stones[0] > 1) {
            return false;
        }
        
        Map<Integer, Set<Integer>> dp = new HashMap<>();
        for(int pos: stones) {
            dp.put(pos, new HashSet<>());
        }

        dp.get(stones[0]).add(1);

        for (int i = 0; i < stones.length; i++) {
            Set<Integer> jumps = dp.get(stones[i]);
            for(int jump: jumps) {
                int pos = jump + stones[i];
                if (pos == stones[stones.length - 1]) return true;

                if (dp.containsKey(pos)) {
                    dp.get(pos).add(jump);
                    dp.get(pos).add(jump + 1);
                    if (jump - 1 > 0) dp.get(pos).add(jump - 1);
                }
            }
        }

        return false;
    }
}