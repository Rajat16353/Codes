// Given a string s, return true if the s can be palindrome after deleting at most one character from it.

 

// Example 1:

// Input: s = "aba"
// Output: true
// Example 2:

// Input: s = "abca"
// Output: true
// Explanation: You could delete the character 'c'.
// Example 3:

// Input: s = "abc"
// Output: false
 

// Constraints:

// 1 <= s.length <= 105
// s consists of lowercase English letters.

class Solution {
    private boolean validPalindrome(String s, boolean deleted) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (!deleted) {
                    return validPalindrome(s.substring(0, i) + s.substring(i + 1, s.length()), true)
                    || validPalindrome(s.substring(0, j) + s.substring(j + 1, s.length()), true);
                }
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        return validPalindrome(s, false);
    }
}