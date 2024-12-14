// Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

// Note that it is the kth smallest element in the sorted order, not the kth distinct element.

// You must find a solution with a memory complexity better than O(n2).

 

// Example 1:

// Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
// Output: 13
// Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
// Example 2:

// Input: matrix = [[-5]], k = 1
// Output: -5
 

// Constraints:

// n == matrix.length == matrix[i].length
// 1 <= n <= 300
// -109 <= matrix[i][j] <= 109
// All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
// 1 <= k <= n2
 

// Follow up:

// Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
// Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.

// Using maxHeap
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                maxHeap.offer(matrix[i][j]);
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        return maxHeap.poll();
    }
}

// Using minHeap
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[0]));
        int result = -1, rowSize = matrix.length;

        for (int r = 0; r < Math.min(rowSize, k); r++) {
            minHeap.offer(new int[]{matrix[r][0], r, 0});
        }

        for (int i = 1; i <= k; i++) {
            int[] top = minHeap.poll();
            result = top[0];
            int r = top[1], c = top[2];
            if (c + 1 < matrix[r].length) {
                minHeap.offer(new int[]{matrix[r][c + 1], r, c + 1});
            }
        }
        return result;
    }
}

// Using 2 binary searches
class Solution {
    private int countSmaller(int[][] matrix, int mid) {
        int count = 0, n = matrix.length;
        
        for(int i = 0; i < n; i++) {
            int l = 0, r = n;
            while (l < r && l != n) {
                int m = l + (r - l)/2;
                if (mid >= matrix[i][m]) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            count += l;
        }
        return count;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n - 1][n - 1];

        while (low < high) {
            int mid = low + (high - low)/2;
            int count = countSmaller(matrix, mid);

            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}