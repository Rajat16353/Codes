// Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

// if no such substring exists, return 0.

 

// Example 1:

// Input: s = "aaabb", k = 3
// Output: 3
// Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
// Example 2:

// Input: s = "ababbc", k = 2
// Output: 5
// Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 

// Constraints:

// 1 <= s.length <= 104
// s consists of only lowercase English letters.
// 1 <= k <= 105

class Solution {
    public int longestSubstring(String s, int k) {
        return longestSubstring(s.toCharArray(), 0, s.length(), k);
    }

    private int longestSubstring(char[] s, int start, int end, int k) {
        if (end - start < k) return 0;

        int[] count = new int[26];
        for(int i = start; i < end; i++) {
            count[s[i] - 'a']++;
        }

        for(int i = start; i < end; i++) {
            if (count[s[i] - 'a'] < k) {
                int j = i + 1;

                while (j < end && count[s[j] - 'a'] < k) j++;

                return Math.max(longestSubstring(s, start, i, k), longestSubstring(s, j, end, k));
            }
        }

        return end - start;
    }
}