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