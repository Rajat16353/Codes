// Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.

// An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.

// We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.

 

// Example 1:

// Input: nums = [3,5,2,6], k = 2
// Output: [2,6]
// Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
// Example 2:

// Input: nums = [2,4,3,3,5,4,9,6], k = 4
// Output: [2,3,3,4]
 

// Constraints:

// 1 <= nums.length <= 105
// 0 <= nums[i] <= 109
// 1 <= k <= nums.length

// Brute force exceeds Time limit
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(new CompetitiveComparator());
        generateAllSubsets(0, nums, k, new ArrayList<>(), minHeap);

        int[] arr = minHeap.peek().stream().mapToInt(i -> i).toArray();
        return arr;
    }

    private class CompetitiveComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> a, List<Integer> b) {
            int size = a.size();
            for (int i = 0; i < size; i++) {
                if (a.get(i) != b.get(i)) return Integer.compare(a.get(i), b.get(i));
            }
            return Integer.compare(a.get(size - 1), b.get(size - 1));
        }
    }

    private void generateAllSubsets(int index, int[] nums, int k, List<Integer> current, PriorityQueue<List<Integer>> minHeap) {
        if (current.size() == k) {
            minHeap.offer(new ArrayList<>(current));
            return;
        }

        if (index >= nums.length) return;

        current.add(nums[index]);
        generateAllSubsets(index + 1, nums, k, current, minHeap);
        current.remove(current.size() - 1);
        generateAllSubsets(index + 1, nums, k, current, minHeap);
    }
}

// Using stack
class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
        int[] res = new int[k];
        int noOfElementsToBeRemoved = nums.length - k;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && noOfElementsToBeRemoved > 0 && nums[i] < stack.peek()) {
                stack.pop();
                noOfElementsToBeRemoved--;
            }
            stack.push(nums[i]);
        }

        while(stack.size() > k) {
            stack.pop();
        }

        for (int i = k - 1; i >=0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}