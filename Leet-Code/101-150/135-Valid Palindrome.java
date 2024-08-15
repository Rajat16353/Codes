// A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

// Given a string s, return true if it is a palindrome, or false otherwise.

 

// Example 1:

// Input: s = "A man, a plan, a canal: Panama"
// Output: true
// Explanation: "amanaplanacanalpanama" is a palindrome.
// Example 2:

// Input: s = "race a car"
// Output: false
// Explanation: "raceacar" is not a palindrome.
// Example 3:

// Input: s = " "
// Output: true
// Explanation: s is an empty string "" after removing non-alphanumeric characters.
// Since an empty string reads the same forward and backward, it is a palindrome.
 

// Constraints:

// 1 <= s.length <= 2 * 105
// s consists only of printable ASCII characters.

class Solution {
    public boolean isPalindrome(String s) {
        String str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int l = str.length()-1;
        Boolean isPalindrome = true;
        System.out.println(str);
        if (l == -1) return isPalindrome;
        for (int i = 0; i < l/2 + 1; i++) {
            if (str.charAt(i) != str.charAt(l-i)) {
                isPalindrome = false;
                break;
            }
        }

        return isPalindrome;
    }
}

// Using Recursion
class Solution {
    private Boolean isPalindrome(int i, String s) {
        if (i >= s.length()/2) return true;

        if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;

        return isPalindrome(i + 1, s);
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = s.replaceAll("[^a-zA-Z0-9]", "");

        if (s.length() < 1) return true;
        return isPalindrome(0, s);
    }
}