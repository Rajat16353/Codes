// Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).

 

// Example 1:

// Input: s = "aa", p = "a"
// Output: false
// Explanation: "a" does not match the entire string "aa".
// Example 2:

// Input: s = "aa", p = "*"
// Output: true
// Explanation: '*' matches any sequence.
// Example 3:

// Input: s = "cb", p = "?a"
// Output: false
// Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 

// Constraints:

// 0 <= s.length, p.length <= 2000
// s contains only lowercase English letters.
// p contains only lowercase English letters, '?' or '*'.

// Using recursion and memoisation
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        Boolean[][] dp = new Boolean[n][m];

        return recursion(n - 1, m - 1, s, p, dp);
    }

    private boolean recursion(int i, int j, String s, String p, Boolean[][] dp) {
        if (i < 0 && j < 0) return true;
        if (j < 0) return false;
        if (i < 0) {
            if (p.charAt(j) == '*') return recursion(i, j - 1, s, p, dp);
            return false;
        }

        if (dp[i][j] != null) return dp[i][j];

        if (p.charAt(j) == '*') {
            return dp[i][j] = recursion(i - 1, j - 1, s, p, dp) || recursion(i - 1, j, s, p, dp)
            || recursion(i, j - 1, s, p, dp);
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return dp[i][j] = recursion(i - 1, j - 1, s, p, dp);
        }

        return dp[i][j] = false;
    }
}

// Using Tabulation
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++) dp[i][0] = false;

        for (int j = 1; j <= m; j++) {
            boolean flag = true;
            for (int jj = 1; jj <= j; jj++) {
                if (p.charAt(jj - 1) != '*') {
                    flag = false;
                    break;
                }
            }
            dp[0][j] = flag;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[n][m];
    }

    private boolean recursion(int i, int j, String s, String p, Boolean[][] dp) {
        if (i < 0 && j < 0) return true;
        if (i >= 0 && j < 0) return false;
        if (i < 0) {
            if (p.charAt(j) == '*') return recursion(i, j - 1, s, p, dp);
            return false;
        }

        if (dp[i][j] != null) return dp[i][j];

        if (p.charAt(j) == '*') {
            return dp[i][j] = recursion(i - 1, j, s, p, dp)
            || recursion(i, j - 1, s, p, dp);
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return dp[i][j] = recursion(i - 1, j - 1, s, p, dp);
        }

        return dp[i][j] = false;
    }
}