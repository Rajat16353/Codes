// Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

// A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

// For example, "ace" is a subsequence of "abcde".
// A common subsequence of two strings is a subsequence that is common to both strings.

 

// Example 1:

// Input: text1 = "abcde", text2 = "ace" 
// Output: 3  
// Explanation: The longest common subsequence is "ace" and its length is 3.
// Example 2:

// Input: text1 = "abc", text2 = "abc"
// Output: 3
// Explanation: The longest common subsequence is "abc" and its length is 3.
// Example 3:

// Input: text1 = "abc", text2 = "def"
// Output: 0
// Explanation: There is no such common subsequence, so the result is 0.
 

// Constraints:

// 1 <= text1.length, text2.length <= 1000
// text1 and text2 consist of only lowercase English characters.

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length() + 1;
        int len2 = text2.length() + 1;
        int[][] len = new int[len1][len2];
        
        for (int r = 0; r < len1; r++) {
            len[r][0] = 0;
        }
        
        for (int c = 0; c < len2; c++) {
            len[0][c] = 0;
        }
        
        for (int r = 1; r < len1; r++) {
            for (int c = 1; c < len2; c++) {
                if (text1.charAt(r-1) == text2.charAt(c-1)) {
                    len[r][c] = 1 + len[r-1][c-1];
                }
                else {
                    len[r][c] = Math.max(len[r-1][c], len[r][c-1]);
                }
            }
        }
        
        return len[len1-1][len2-1];
    }
}

// Using recursion and dp
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1][l2];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, -1));
        return recursion(l1 - 1, l2 - 1, text1, text2, dp);
    }

    private int recursion(int idx1, int idx2, String text1, String text2, int[][] dp) {
        if (idx1 < 0 || idx2 < 0) return 0;

        if (dp[idx1][idx2] != -1) return dp[idx1][idx2];
        
        if (text1.charAt(idx1) == text2.charAt(idx2)) {
            return dp[idx1][idx2] = 1 + recursion(idx1 - 1, idx2 - 1, text1, text2, dp);
        }

        int firstString = recursion(idx1 - 1, idx2, text1, text2, dp);
        int secondString = recursion(idx1, idx2 - 1, text1, text2, dp);

        return dp[idx1][idx2] = Math.max(firstString, secondString);
    }
}

// Using tabulation
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 0; i <= l1; i++) dp[i][0] = 0;
        for (int i = 0; i <= l2; i++) dp[0][i] = 0;

        for (int idx1 = 1; idx1 <= l1; idx1++) {
            for (int idx2 = 1; idx2 <= l2; idx2++) {
                if (text1.charAt(idx1 - 1) == text2.charAt(idx2 - 1)) {
                    dp[idx1][idx2] = 1 + dp[idx1 - 1][idx2 - 1];
                    continue;
                }

                dp[idx1][idx2] = Math.max(dp[idx1 - 1][idx2], dp[idx1][idx2 - 1]);
            }
        }
        
        return dp[l1][l2];
    }
}

// Using memory optimised tabulation
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[] dp = new int[l2 + 1];

        for (int idx1 = 1; idx1 <= l1; idx1++) {
            int[] cur = new int[l2 + 1];
            for (int idx2 = 1; idx2 <= l2; idx2++) {
                if (text1.charAt(idx1 - 1) == text2.charAt(idx2 - 1)) {
                    cur[idx2] = 1 + dp[idx2 - 1];
                    continue;
                }

                cur[idx2] = Math.max(dp[idx2], cur[idx2 - 1]);
            }
            dp = cur;
        }
        
        return dp[l2];
    }
}