// There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

// We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:

// Every worker in the paid group must be paid at least their minimum wage expectation.
// In the group, each worker's pay must be directly proportional to their quality. This means if a worker’s quality is double that of another worker in the group, then they must be paid twice as much as the other worker.
// Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.

 

// Example 1:

// Input: quality = [10,20,5], wage = [70,50,30], k = 2
// Output: 105.00000
// Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
// Example 2:

// Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
// Output: 30.66667
// Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 

// Constraints:

// n == quality.length == wage.length
// 1 <= k <= n <= 104
// 1 <= quality[i], wage[i] <= 104

class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double res = Double.MAX_VALUE;
        int n = quality.length;
        Pair[] ratio = new Pair[n];

        for (int i = 0; i < n; i++) {
            ratio[i] = new Pair(1.0 * wage[i] / quality[i], quality[i]);
        }

        Arrays.sort(ratio);
        
        int totalQuality = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            Pair cur = ratio[i];
            double rate = cur.rate;
            int q = cur.quality;

            maxHeap.offer(q);
            totalQuality += q;

            if (maxHeap.size() == k) {
                res = Math.min(res, totalQuality * rate);
                totalQuality -= maxHeap.poll();
            }
        }
        return res;
    }

    private class Pair implements Comparable<Pair> {
        double rate;
        int quality;

        public Pair(double rate, int quality) {
            this.rate = rate;
            this.quality = quality;
        }

        @Override
        public int compareTo(Pair b) {
            return Double.compare(this.rate, b.rate);
        }
    }
}