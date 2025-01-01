// Given a string s. In one step you can insert any character at any index of the string.

// Return the minimum number of steps to make s palindrome.

// A Palindrome String is one that reads the same backward as well as forward.

 

// Example 1:

// Input: s = "zzazz"
// Output: 0
// Explanation: The string "zzazz" is already palindrome we do not need any insertions.
// Example 2:

// Input: s = "mbadm"
// Output: 2
// Explanation: String can be "mbdadbm" or "mdbabdm".
// Example 3:

// Input: s = "leetcode"
// Output: 5
// Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 

// Constraints:

// 1 <= s.length <= 500
// s consists of lowercase English letters.

// Can refer 379, 108
// Using memory optimised tabulation
class Solution {
    public int minInsertions(String s) {
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
        
        return n - dp[n];
    }
}