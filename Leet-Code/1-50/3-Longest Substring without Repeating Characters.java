/* 
Given a string s, find the length of the longest substring without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int l = 0, r = 0;

        while (r < s.length()) {
            char rc = s.charAt(r);
            map.put(rc, map.getOrDefault(rc, 0) + 1);

            while (map.get(rc) > 1) {
                char lc = s.charAt(l);
                map.put(lc, map.get(lc) - 1);
                l += 1;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r += 1;
        }

        return maxLen;
    }
}

// Optimised approach
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLen = 0;
        int l = 0, r = 0;

        while (r < s.length()) {
            char rc = s.charAt(r);
            if (charIndex.containsKey(rc) && charIndex.get(rc) >= l) {
                l = charIndex.get(rc) + 1;
            }
            charIndex.put(rc, r);

            maxLen = Math.max(maxLen, r - l + 1);
            r += 1;
        }

        return maxLen;
    }
}
