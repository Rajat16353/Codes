// You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’. Your task is to check if there exists a subset in ‘ARR’ with a sum equal to ‘K’.

// Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.

// For Example :
// If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.
// Detailed explanation ( Input/output format, Notes, Images )
// Constraints:
// 1 <= T <= 5
// 1 <= N <= 10^3
// 0 <= ARR[i] <= 10^9
// 0 <= K <= 10^3

// Time Limit: 1 sec
// Sample Input 1:
// 2
// 4 5
// 4 3 2 1
// 5 4
// 2 5 1 6 7
// Sample Output 1:
// true
// false
// Explanation For Sample Input 1:
// In example 1, ‘ARR’ is {4,3,2,1} and ‘K’ = 5. There exist 2 subsets with sum = 5. These are {4,1} and {3,2}. Hence, return true.
// In example 2, ‘ARR’ is {2,5,1,6,7} and ‘K’ = 4. There are no subsets with sum = 4. Hence, return false.
// Sample Input 2:
// 2
// 4 4
// 6 1 2 1
// 5 6
// 1 7 2 9 10
// Sample Output 2:
// true
// false
// Explanation For Sample Input 2:
// In example 1, ‘ARR’ is {6,1,2,1} and ‘K’ = 4. There exist 1 subset with sum = 4. That is {1,2,1}. Hence, return true.
// In example 2, ‘ARR’ is {1,7,2,9,10} and ‘K’ = 6. There are no subsets with sum = 6. Hence, return false.


// Hints:
// 1. Can you find every possible subset of ‘ARR’ and check if its sum is equal to ‘K’?
// 2. Can you use dynamic programming and use the previously calculated result to calculate the new result?
// 3. Try to use a recursive approach followed by memoization by including both index and sum we can form. 

// Top down approach with memoisation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        int[][] dp = new int[arr.length][k + 1];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return subsetSumToK(arr, arr.length - 1, k, dp);
    }

    private static boolean subsetSumToK(int[] arr, int index, int target, int[][] dp) {
        if (target == 0) return true;
        if (index == 0) return (arr[0] == target);

        if (dp[index][target] != -1) return dp[index][target] == 1;

        boolean take = false;
        if (arr[index] <= target) take = subsetSumToK(arr, index - 1, target - arr[index], dp);
        boolean notTake = subsetSumToK(arr, index - 1, target, dp);

        dp[index][target] = take | notTake ? 1: 0;
        return dp[index][target] == 1;
    }
}

// Tabulation
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        boolean[][] dp = new boolean[arr.length][k + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = true;
        }
        if (arr[0] < k) dp[0][arr[0]] = true;

        for (int index = 1; index < arr.length; index++) {
            for (int target = 1; target <= k; target++) {
                boolean take = false;
                if (arr[index] <= target) take = dp[index - 1][target - arr[index]];
                boolean notTake = dp[index - 1][target];

                dp[index][target] = take | notTake;
            }
        }

        return dp[arr.length - 1][k];
    }
}

// Tabulation with optimised memory
import java.util.* ;
import java.io.*; 
public class Solution {
    public static boolean subsetSumToK(int n, int k, int arr[]){
        boolean[] dp = new boolean[k + 1];
        
        dp[0] = true;
        if (arr[0] < k) dp[arr[0]] = true;

        for (int index = 1; index < arr.length; index++) {
            boolean[] cur = new boolean[k + 1];
            cur[0] = true;
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[target];
                boolean take = false;
                if (arr[index] <= target) take = dp[target - arr[index]];

                cur[target] = take | notTake;
            }
            dp = cur;
        }

        return dp[k];
    }
}
