// You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.

// For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].

// Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

 

// Example 1:

// Input: arr = [1,2,3,5], k = 3
// Output: [2,5]
// Explanation: The fractions to be considered in sorted order are:
// 1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
// The third fraction is 2/5.
// Example 2:

// Input: arr = [1,7], k = 1
// Output: [1,7]
 

// Constraints:

// 2 <= arr.length <= 1000
// 1 <= arr[i] <= 3 * 104
// arr[0] == 1
// arr[i] is a prime number for i > 0.
// All the numbers of arr are unique and sorted in strictly increasing order.
// 1 <= k <= arr.length * (arr.length - 1) / 2
 

// Follow up: Can you solve the problem with better than O(n2) complexity?

// Using maxHeap
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<Tuple> maxHeap = new PriorityQueue<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                maxHeap.offer(new Tuple(((double)arr[i]/arr[j]), arr[i], arr[j]));
                if (maxHeap.size() > k) maxHeap.poll();
            }
        }
        return new int[]{maxHeap.peek().i, maxHeap.peek().j};
    }

    private class Tuple implements Comparable<Tuple> {
        double fraction;
        int i;
        int j;

        public Tuple(double fraction, int i, int j) {
            this.fraction = fraction;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Tuple b) {
            return Double.compare(b.fraction, this.fraction);
        }
    }
}

// Using binary search
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double low = 0, high = 1;
        int[] res = new int[2];

        while (low <= high) {
            double mid = (low + high)/2;

            int total = 0, j = 1, num = -1, den = -1;
            double maxFrac = 0;

            for (int i = 0; i < n; i++) {
                while (j < n && arr[i] >= arr[j] * mid) {
                    j += 1;
                }

                total += n - j;

                if (j < n && maxFrac < arr[i] * 1.0/arr[j]) {
                    maxFrac = arr[i] * 1.0/arr[j];
                    num = i;
                    den = j;
                }
            }

            if (total == k) {
                res[0] = arr[num];
                res[1] = arr[den];
                return res;
            }

            if (total > k) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return res;
    }
}