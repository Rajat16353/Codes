// You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.



// Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.



// Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.



// Example:
// Input: 'arr' = [1, 1, 4, 5]

// Output: 3

// Explanation: The possible ways are:
// [1, 4]
// [1, 4]
// [5]
// Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
// Detailed explanation ( Input/output format, Notes, Images )
// Sample Input 1 :
// 4 5
// 1 4 4 5


// Sample Output 1 :
//  3


// Explanation For Sample Output 1:
// The possible ways are:
// [1, 4]
// [1, 4]
// [5]
// Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.


// Sample Input 2 :
// 3 2
// 1 1 1


// Sample Output 2 :
// 3


// Explanation For Sample Output 1:
// There are three 1 present in the array. Answer is the number of ways to choose any two of them.


// Sample Input 3 :
// 3 40
// 2 34 5


// Sample Output 3 :
// 0


// Expected time complexity :
// The expected time complexity is O('n' * 'k').


// Constraints:
// 1 <= 'n' <= 100
// 0 <= 'arr[i]' <= 1000
// 1 <= 'k' <= 1000

// Time limit: 1 sec

import java.util.*;
import java.io.*;

public class Solution {
    private static final int mod = 1000000007;
    public static int findWays(int num[], int tar) {
        int n = num.length;
        int[][] dp = new int[n][tar + 1];
        Arrays.stream(dp).forEach((row) -> Arrays.fill(row, -1));

        return recursion(n - 1, num, tar, dp);
    }

    private static int recursion(int idx, int[] num, int tar, int[][] dp) {
        if (idx == 0) {
            if (tar == 0 && num[0] == 0) return 2;
            if (tar == 0 || tar == num[0]) return 1;
            return 0;
        }

        if (dp[idx][tar] != -1) return dp[idx][tar];

        int pick = 0;
        if (num[idx] <= tar) pick = recursion(idx - 1, num, tar - num[idx], dp);

        int skip = recursion(idx - 1, num, tar, dp);

        return dp[idx][tar] = (pick + skip) % mod;
    }
}

// Tabulation
import java.util.*;
import java.io.*;

public class Solution {
    private static final int mod = 1000000007;
    public static int findWays(int num[], int tar) {
        int n = num.length;
        int[][] dp = new int[n][tar + 1];
        for (int i = 0; i < n; i++) dp[i][0] = 1;
        if (num[0] <= tar) dp[0][num[0]] = 1;
        if (num[0] == 0) dp[0][0] = 2;

        for (int idx = 1; idx < n; idx++) {
            for (int sum = 0; sum <= tar; sum++) {
                int pick = 0;
                if (num[idx] <= sum) pick = dp[idx - 1][sum - num[idx]];

                int skip = dp[idx - 1][sum];

                dp[idx][sum] = (pick + skip) % mod;
            }
        }

        return dp[n - 1][tar];
    }
}

// Tabulation with optimised memory
import java.util.*;
import java.io.*;

public class Solution {
    private static final int mod = 1000000007;
    public static int findWays(int num[], int tar) {
        int n = num.length;
        int[] dp = new int[tar + 1];
        dp[0] = 1;
        if (num[0] <= tar) dp[num[0]] = 1;
        if (num[0] == 0) dp[0] = 2;

        for (int idx = 1; idx < n; idx++) {
            int[] cur = new int[tar + 1];
            for (int sum = 0; sum <= tar; sum++) {
                int pick = 0;
                if (num[idx] <= sum) pick = dp[sum - num[idx]];

                int skip = dp[sum];

                cur[sum] = (pick + skip) % mod;
            }
            dp = cur;
        }

        return dp[tar];
    }
}