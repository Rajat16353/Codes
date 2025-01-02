// An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

// Given an integer n, return the nth ugly number.

 

// Example 1:

// Input: n = 10
// Output: 12
// Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
// Example 2:

// Input: n = 1
// Output: 1
// Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 

// Constraints:

// 1 <= n <= 1690

// Using minHeap
class Solution {
    public int nthUglyNumber(int n) {
        int[] multiples = new int[]{2, 3, 5};
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> set = new HashSet<>();

        minHeap.offer(1L);
        set.add(1L);
        n--;
        while(n-- > 0) {
            long cur = minHeap.poll();
            for (int m: multiples) {
                long res = cur * m;
                if (!set.contains(res)) {
                    minHeap.offer(res);
                    set.add(res);
                }
            }
        }

        long res = minHeap.poll();
        return (int)res;
    }
}

// Using tabulation (DP)
class Solution {
    public int nthUglyNumber(int n) {
        int[] multiples = new int[]{2, 3, 5};
        int[] nextIndices = new int[]{0, 0, 0};
        List<Integer> uglyNumbers = new ArrayList<>();

        uglyNumbers.add(1);

        for (int i = 1; i < n; i++) {
            int[] nextUglies = new int[] {
                uglyNumbers.get(nextIndices[0]) * multiples[0],
                uglyNumbers.get(nextIndices[1]) * multiples[1],
                uglyNumbers.get(nextIndices[2]) * multiples[2]
            };

            int minVal = Math.min(nextUglies[0], Math.min(nextUglies[1], nextUglies[2]));
            uglyNumbers.add(minVal);

            for (int j = 0; j < 3; j++) {
                if (nextUglies[j] == minVal) {
                    nextIndices[j] += 1;
                }
            }
        }

        return uglyNumbers.get(uglyNumbers.size() - 1);
    }
}