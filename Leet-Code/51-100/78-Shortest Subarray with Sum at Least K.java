// Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.

// A subarray is a contiguous part of an array.

 

// Example 1:

// Input: nums = [1], k = 1
// Output: 1
// Example 2:

// Input: nums = [1,2], k = 4
// Output: -1
// Example 3:

// Input: nums = [2,-1,2], k = 3
// Output: 3
 

// Constraints:

// 1 <= nums.length <= 105
// -105 <= nums[i] <= 105
// 1 <= k <= 109

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        Deque<Pair<Integer, Long>> deque = new LinkedList<>();
        
        int i = 0, shortest = Integer.MAX_VALUE;
        long sum = 0;
        
        while(i < nums.length) {
            sum += nums[i];
            if (sum >= k) shortest = Math.min(shortest, i+1);
            
            Pair<Integer, Long> curr = new Pair<Integer, Long>(Integer.MIN_VALUE, Long.MIN_VALUE);
            
            while (!deque.isEmpty() && (sum - deque.peekFirst().getValue()) >= k) {
                curr = deque.pollFirst();
            }            
            if(curr.getValue() != Long.MIN_VALUE)
                shortest = Math.min(shortest, (i-curr.getKey()));   
            
            while(!deque.isEmpty() && deque.peekLast().getValue() >= sum) {
                deque.pollLast();
            }
            
            deque.offer(new Pair<Integer, Long>(i, sum));                
            i++;
        }
        if (shortest == Integer.MAX_VALUE)
            return -1;
        else
            return shortest;
    }
}