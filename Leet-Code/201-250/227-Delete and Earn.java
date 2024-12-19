// You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:

// Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
// Return the maximum number of points you can earn by applying the above operation some number of times.

 

// Example 1:

// Input: nums = [3,4,2]
// Output: 6
// Explanation: You can perform the following operations:
// - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
// - Delete 2 to earn 2 points. nums = [].
// You earn a total of 6 points.
// Example 2:

// Input: nums = [2,2,3,3,3,4]
// Output: 9
// Explanation: You can perform the following operations:
// - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
// - Delete a 3 again to earn 3 points. nums = [3].
// - Delete a 3 once more to earn 3 points. nums = [].
// You earn a total of 9 points.
 

// Constraints:

// 1 <= nums.length <= 2 * 104
// 1 <= nums[i] <= 104

class Solution {
    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int n: nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        Set<Integer> numSet = freqMap.keySet();
        int[] sortedNums = new int[numSet.size()];
        int k = 0;
        for (int n: numSet) {
            sortedNums[k++] = n;
        }
        if (sortedNums.length == 1) {
            return sortedNums[0] * freqMap.get(sortedNums[0]);
        }

        Arrays.sort(sortedNums);
        int[] cache = new int[2];
        int points = Integer.MIN_VALUE;

        cache[0] = sortedNums[0] * freqMap.get(sortedNums[0]);
        if (sortedNums[1] - 1 != sortedNums[0]) {
            cache[1] = sortedNums[1] * freqMap.get(sortedNums[1]) + cache[0];
        } else {
            cache[1] = Math.max(sortedNums[1] * freqMap.get(sortedNums[1]), cache[0]);
        }

        for(int i = 2; i < numSet.size(); i++) {
            int adj = cache[1];
            if (sortedNums[i] - 1 != sortedNums[i - 1]) {
                cache[1] = sortedNums[i] * freqMap.get(sortedNums[i]) + adj;
            } else {
                cache[1] = Math.max(sortedNums[i] * freqMap.get(sortedNums[i]) + cache[0], adj);
            }
            cache[0] = adj;
        }

        return cache[1];
    }
}

// Recursive
class Solution {
    Map<Integer, Integer> freqMap;
    public int deleteAndEarn(int[] nums) {
        freqMap = new HashMap<>();
        
        for (int n: nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        int[] dp = new int[freqMap.size()];
        List<Integer> list = new ArrayList<>(freqMap.keySet());
        Collections.sort(list);
        return recursion(list, freqMap.keySet().size() - 1, dp);
    }

    private int recursion(List<Integer> nums, int index, int[] dp) {
        if (index < 0) return 0;
        if (index == 0) return nums.get(index) * freqMap.get(nums.get(index));

        if (dp[index] != 0) return dp[index];

        int pick = 0;
        if (nums.get(index) - nums.get(index - 1) == 1) {
            pick = nums.get(index) * freqMap.get(nums.get(index)) + recursion(nums, index - 2, dp);
        } else {
            pick = nums.get(index) * freqMap.get(nums.get(index)) + recursion(nums, index - 1, dp);
        }
        int skip = recursion(nums, index - 1, dp);

        return dp[index] = Math.max(pick, skip);
    }
}

// Tabulation
class Solution {
    Map<Integer, Integer> freqMap;
    public int deleteAndEarn(int[] nums) {
        freqMap = new HashMap<>();
        
        for (int n: nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        int[] dp = new int[freqMap.size()];
        List<Integer> list = new ArrayList<>(freqMap.keySet());
        Collections.sort(list);
        dp[0] = list.get(0) * freqMap.get(list.get(0));
        
        for (int index = 1; index < list.size(); index++) {
            int pick = list.get(index) * freqMap.get(list.get(index));
            if (list.get(index) - list.get(index - 1) == 1) {
                if (index > 1) pick += dp[index - 2];
            } else {
                pick += dp[index - 1];
            }
            int skip = dp[index - 1];
            dp[index] = Math.max(pick, skip);
        }

        return dp[freqMap.size() - 1];
    }
}

// Tabulation with optimised memory
class Solution {
    Map<Integer, Integer> freqMap;
    public int deleteAndEarn(int[] nums) {
        freqMap = new HashMap<>();
        
        for (int n: nums) {
            freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
        }

        List<Integer> list = new ArrayList<>(freqMap.keySet());
        Collections.sort(list);
        int prev = list.get(0) * freqMap.get(list.get(0));
        int prev2 = 0;

        for (int index = 1; index < list.size(); index++) {
            int pick = list.get(index) * freqMap.get(list.get(index));
            if (list.get(index) - list.get(index - 1) == 1) {
                if (index > 1) pick += prev2;
            } else {
                pick += prev;
            }
            int skip = prev;

            prev2 = prev;
            prev = Math.max(pick, skip);
        }

        return prev;
    }
}