// Given a string s, partition s such that every 
// substring
//  of the partition is a 
// palindrome
// .

// Return the minimum cuts needed for a palindrome partitioning of s.

 

// Example 1:

// Input: s = "aab"
// Output: 1
// Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
// Example 2:

// Input: s = "a"
// Output: 0
// Example 3:

// Input: s = "ab"
// Output: 1
 

// Constraints:

// 1 <= s.length <= 2000
// s consists of lowercase English letters only.

// recurison and memoisation
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return recursion(0, n, s, dp) - 1;
    }

    private int recursion(int i, int n, String s, int[] dp) {
        if (i == n) return 0;

        if (dp[i] != -1) return dp[i];

        int minCuts = Integer.MAX_VALUE;

        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, s)) {
                int cuts = 1 + recursion(j + 1, n, s, dp);
                minCuts = Math.min(cuts, minCuts);
            }
        }

        return dp[i] = minCuts;
    }

    private boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

// recurison and memoisation for checking palindrome
class Solution {
    int[][] isPalindrome;
    public int minCut(String s) {
        int n = s.length();
        isPalindrome = new int[n][n];
        Arrays.stream(isPalindrome).forEach(row -> Arrays.fill(row, -1));

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return recursion(0, n, s, dp) - 1;
    }

    private int recursion(int i, int n, String s, int[] dp) {
        if (i == n) return 0;

        if (dp[i] != -1) return dp[i];

        int minCuts = Integer.MAX_VALUE;

        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, s)) {
                int cuts = 1 + recursion(j + 1, n, s, dp);
                minCuts = Math.min(cuts, minCuts);
            }
        }

        return dp[i] = minCuts;
    }

    private boolean isPalindrome(int i, int j, String s) {
        if (isPalindrome[i][j] != -1) return isPalindrome[i][j] == 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                isPalindrome[i][j] = 0;
                return false;
            }
            i++;
            j--;
        }

        isPalindrome[i][j] = 1;
        return true;
    }
}

// Tabulation
class Solution {
    int[][] isPalindrome;
    public int minCut(String s) {
        int n = s.length();
        isPalindrome = new int[n][n];
        Arrays.stream(isPalindrome).forEach(row -> Arrays.fill(row, -1));

        int[] dp = new int[n + 1];
        
        for (int i = n - 1; i >= 0; i--) {
            int minCuts = Integer.MAX_VALUE;

            for (int j = i; j < n; j++) {
                if (isPalindrome(i, j, s)) {
                    int cuts = 1 + dp[j + 1];
                    minCuts = Math.min(cuts, minCuts);
                }
            }

            dp[i] = minCuts;
        }
        return dp[0] - 1;
    }

    private boolean isPalindrome(int i, int j, String s) {
        if (isPalindrome[i][j] != -1) return isPalindrome[i][j] == 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                isPalindrome[i][j] = 0;
                return false;
            }
            i++;
            j--;
        }

        isPalindrome[i][j] = 1;
        return true;
    }
}