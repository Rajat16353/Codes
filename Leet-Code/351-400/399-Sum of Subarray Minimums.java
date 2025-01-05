// Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.

 

// Example 1:

// Input: arr = [3,1,2,4]
// Output: 17
// Explanation: 
// Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
// Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
// Sum is 17.
// Example 2:

// Input: arr = [11,81,94,43,3]
// Output: 444
 

// Constraints:

// 1 <= arr.length <= 3 * 104
// 1 <= arr[i] <= 3 * 104

// By finding next and previous smaller elements
class Solution {
    public int sumSubarrayMins(int[] arr) {
        double mod = 1000000007;
        int[] nse = nextSmallerElementList(arr);
        int[] psse = previousSmallerOrEqualElementList(arr);

        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int left = i - psse[i];
            int right = nse[i] - i;
            sum = (sum + (left * right * (double)arr[i])) % mod;
        }

        return (int)sum;
    }

    private int[] nextSmallerElementList(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        return nse;
    }

    private int[] previousSmallerOrEqualElementList(int[] arr) {
        int n = arr.length;
        int[] psse = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            psse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        return psse;
    }
}

// By finding previous smaller elements
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long[] res = new long[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }

            int j = stack.isEmpty() ? -1 : stack.peek();
            res[i] = (i - j) * arr[i] + (j == -1 ? 0 : res[j]);
            stack.push(i);
        }

        long mod = 1000000007;
        long sum = 0;
        for (long r: res) {
            sum = (sum + r) % mod;
        }

        return (int)sum;
    }
}