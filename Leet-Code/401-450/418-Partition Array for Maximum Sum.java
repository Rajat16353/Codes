// Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

// Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

 

// Example 1:

// Input: arr = [1,15,7,9,2,5,10], k = 3
// Output: 84
// Explanation: arr becomes [15,15,15,9,10,10,10]
// Example 2:

// Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
// Output: 83
// Example 3:

// Input: arr = [1], k = 1
// Output: 1
 

// Constraints:

// 1 <= arr.length <= 500
// 0 <= arr[i] <= 109
// 1 <= k <= arr.length

// recurison with memoisation
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        
        return recursion(0, k, n, arr, dp);
    }

    private int recursion(int i, int k, int n, int[] arr, int[] dp) {
        if (i == n) return 0;

        if (dp[i] != -1) return dp[i];

        int maxSum = Integer.MIN_VALUE;
        int maxNum = Integer.MIN_VALUE;

        for (int j = i; j < Math.min(n, i + k); j++) {
            maxNum = Math.max(maxNum, arr[j]);
            int sum = (j - i + 1) * maxNum + recursion(j + 1, k, n, arr, dp);
            maxSum = Math.max(maxSum, sum);
        }
        return dp[i] = maxSum;
    }
}

// Tabulation
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            int maxSum = Integer.MIN_VALUE;
            int maxNum = Integer.MIN_VALUE;

            for (int j = i; j < Math.min(n, i + k); j++) {
                maxNum = Math.max(maxNum, arr[j]);
                int sum = (j - i + 1) * maxNum + dp[j + 1];
                maxSum = Math.max(maxSum, sum);
            }
            dp[i] = maxSum;
        }

        return dp[0];
    }
}