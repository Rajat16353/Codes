// Given a string s, find the longest palindromic subsequence's length in s.

// A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

// Example 1:

// Input: s = "bbbab"
// Output: 4
// Explanation: One possible longest palindromic subsequence is "bbbb".
// Example 2:

// Input: s = "cbbd"
// Output: 2
// Explanation: One possible longest palindromic subsequence is "bb".
 

// Constraints:

// 1 <= s.length <= 1000
// s consists only of lowercase English letters.

// Using recursion and dp
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return recursion(n - 1, n - 1, s, new StringBuilder(s).reverse().toString(), dp);
    }

    private int recursion(int i, int j, String s1, String s2, int[][] dp) {
        if (i < 0 || j < 0) return 0;

        if (dp[i][j] != -1) return dp[i][j];
        
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = 1 + recursion(i - 1, j - 1, s1, s2, dp);
        }

        int firstString = recursion(i - 1, j, s1, s2, dp);
        int secondString = recursion(i, j - 1, s1, s2, dp);

        return dp[i][j] = Math.max(firstString, secondString);
    }
}

// Using tabulation
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        String s2 = new StringBuilder(s).reverse().toString();

        for (int i = 0; i <= n; i++) dp[i][0] = 0;
        for (int i = 0; i <= n; i++) dp[0][i] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    continue;
                }

                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        
        return dp[n][n];
    }
}

// Using memory optimised tabulation
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        String s2 = new StringBuilder(s).reverse().toString();

        for (int i = 1; i <= n; i++) {
            int[] cur = new int[n + 1];
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == s2.charAt(j - 1)) {
                    cur[j] = 1 + dp[j - 1];
                } else {
                    cur[j] = Math.max(dp[j], cur[j - 1]);
                }
            }
            dp = cur;
        }
        
        return dp[n];
    }
}