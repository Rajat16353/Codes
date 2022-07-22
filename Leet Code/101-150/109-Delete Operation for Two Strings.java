// Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

// In one step, you can delete exactly one character in either string.

 

// Example 1:

// Input: word1 = "sea", word2 = "eat"
// Output: 2
// Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
// Example 2:

// Input: word1 = "leetcode", word2 = "etco"
// Output: 4
 

// Constraints:

// 1 <= word1.length, word2.length <= 500
// word1 and word2 consist of only lowercase English letters.

// Solution using longest common subsequence

class Solution {
    private int longestCommonSubsequence(String text1, String text2) {
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
    
    public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
    }
}

// Solution 2
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length() + 1;
        int len2 = word2.length() + 1;
        int[][] len = new int[len1][len2];
        
        for (int r = 0; r < len1; r++) {
            len[r][0] = r;
        }
        
        for (int c = 0; c < len2; c++) {
            len[0][c] = c;
        }
        
        for (int r = 1; r < len1; r++) {
            for (int c = 1; c < len2; c++) {
                if (word1.charAt(r-1) == word2.charAt(c-1)) {
                    len[r][c] = len[r-1][c-1];
                }
                else {
                    len[r][c] = Math.min(len[r-1][c], len[r][c-1])+1;
                }
            }
        }
        
        return len[len1-1][len2-1];
    }
}