// You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

// Return the length of the longest substring containing the same letter you can get after performing the above operations.

 

// Example 1:

// Input: s = "ABAB", k = 2
// Output: 4
// Explanation: Replace the two 'A's with two 'B's or vice versa.
// Example 2:

// Input: s = "AABABBA", k = 1
// Output: 4
// Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
// The substring "BBBB" has the longest repeating letters, which is 4.
// There may exists other ways to achieve this answer too.
 

// Constraints:

// 1 <= s.length <= 105
// s consists of only uppercase English letters.
// 0 <= k <= s.length

class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        int maxLen = 0;
        int maxf = 0;

        while (r < s.length()) {
            char cr = s.charAt(r);
            map.put(cr, map.getOrDefault(cr, 0) + 1);
            maxf = Math.max(maxf, map.get(cr));
            if (r - l + 1 - maxf > k) {
                char cl = s.charAt(l);
                map.put(cl, map.get(cl) - 1);
                maxf = 0;
                l++;
            }
            // if (r - l + 1 - maxf <= k) {
            maxLen = Math.max(maxLen, r - l + 1);
            // }
            r++;
        }

        return maxLen;
    }
}