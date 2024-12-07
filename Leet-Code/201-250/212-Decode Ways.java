// A message containing letters from A-Z can be encoded into numbers using the following mapping:

// 'A' -> "1"
// 'B' -> "2"
// ...
// 'Z' -> "26"
// To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:

// "AAJF" with the grouping (1 1 10 6)
// "KJF" with the grouping (11 10 6)
// Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

// Given a string s containing only digits, return the number of ways to decode it.

// The test cases are generated so that the answer fits in a 32-bit integer.

 

// Example 1:

// Input: s = "12"
// Output: 2
// Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
// Example 2:

// Input: s = "226"
// Output: 3
// Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
// Example 3:

// Input: s = "06"
// Output: 0
// Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 

// Constraints:

// 1 <= s.length <= 100
// s contains only digits and may contain leading zero(s).

// Top Down approach with dp
class Solution {
    private int numDecodings(String s, int index, int[] dp) {       
        if (index > s.length() - 1) {
            return 1;
        }
        
        if (s.charAt(index) == '0') {
            return 0;
        }

        if (dp[index] != -1) return dp[index];

        int ans = numDecodings(s, index + 1, dp);
        if (index + 2 <= s.length()) {
            int twoDigit = Integer.parseInt(s.substring(index, index + 2));
            if (twoDigit >= 10 && twoDigit <= 26) ans += numDecodings(s, index + 2, dp);
        }
        
        return dp[index] = ans;
    }

    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return numDecodings(s, 0, dp);
    }
}

// Bottom Up Approach
class Solution {
    public int numDecodings(String s) {
        if (s.startsWith("0") || s.length() == 0) {
            return 0;
        }

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1: 0;

        for(int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') dp[i] += dp[i - 1];

            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }
}

// Space optimised bottom up approach
class Solution {
    public int numDecodings(String s) {
        if (s.startsWith("0") || s.length() == 0) {
            return 0;
        }

        int prev2 = 1;
        int prev1 = s.charAt(0) != '0' ? 1: 0;

        for(int i = 2; i <= s.length(); i++) {
            int cur = 0;
            if (s.charAt(i - 1) != '0') cur += prev1;

            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                cur += prev2;
            }
            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }
}