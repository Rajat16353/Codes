// Given an array arr of positive integers, consider all binary trees such that:

// Each node has either 0 or 2 children;
// The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
// The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree, respectively.
// Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node. It is guaranteed this sum fits into a 32-bit integer.

// A node is a leaf if and only if it has zero children.

 

// Example 1:


// Input: arr = [6,2,4]
// Output: 32
// Explanation: There are two possible trees shown.
// The first has a non-leaf node sum 36, and the second has non-leaf node sum 32.
// Example 2:


// Input: arr = [4,11]
// Output: 44
 

// Constraints:

// 2 <= arr.length <= 40
// 1 <= arr[i] <= 15
// It is guaranteed that the answer fits into a 32-bit signed integer (i.e., it is less than 231).

// Using recursion andm memoisation
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        Map<Pair<Integer, Integer>, Integer> maxMap = new HashMap<>();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            maxMap.put(new Pair(i, i), arr[i]);
            for (int j = i + 1; j < n; j++) {
                maxMap.put(new Pair(i, j), Math.max(arr[j], maxMap.get(new Pair(i, j - 1))));
            }
        }

        return recursion(arr, 0, n - 1, maxMap, dp);
    }

    private int recursion(int[] arr, int left, int right, Map<Pair<Integer, Integer>, Integer> maxMap, int[][] dp) {
        if (left == right) return 0;

        if (dp[left][right] != 0) return dp[left][right];

        int ans = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            int val = maxMap.get(new Pair(left, i)) * maxMap.get(new Pair(i + 1, right)) + recursion(arr, left, i, maxMap, dp) + recursion(arr, i + 1, right, maxMap, dp);
            ans = Math.min(ans, val);
        }

        return dp[left][right] = ans;
    }
}

// Tabulation
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        Map<Pair<Integer, Integer>, Integer> maxMap = new HashMap<>();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            maxMap.put(new Pair(i, i), arr[i]);
            for (int j = i + 1; j < n; j++) {
                maxMap.put(new Pair(i, j), Math.max(arr[j], maxMap.get(new Pair(i, j - 1))));
            }
        }

        for (int left = n - 1; left >= 0; left--) {
            for (int right = left; right < n; right++) {
                if (left == right) continue;
                int ans = Integer.MAX_VALUE;
                for (int i = left; i < right; i++) {
                    int val = maxMap.get(new Pair(left, i)) * maxMap.get(new Pair(i + 1, right)) + dp[left][i] + dp[i + 1][right];
                    ans = Math.min(ans, val);
                }
                dp[left][right] = ans;
            }
        }

        return dp[0][n - 1];
    }
}