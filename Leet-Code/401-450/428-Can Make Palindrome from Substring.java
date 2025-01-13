// You are given a string s and array queries where queries[i] = [lefti, righti, ki]. We may rearrange the substring s[lefti...righti] for each query and then choose up to ki of them to replace with any lowercase English letter.

// If the substring is possible to be a palindrome string after the operations above, the result of the query is true. Otherwise, the result is false.

// Return a boolean array answer where answer[i] is the result of the ith query queries[i].

// Note that each letter is counted individually for replacement, so if, for example s[lefti...righti] = "aaa", and ki = 2, we can only replace two of the letters. Also, note that no query modifies the initial string s.

 

// Example :

// Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
// Output: [true,false,false,true,true]
// Explanation:
// queries[0]: substring = "d", is palidrome.
// queries[1]: substring = "bc", is not palidrome.
// queries[2]: substring = "abcd", is not palidrome after replacing only 1 character.
// queries[3]: substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
// queries[4]: substring = "abcda", could be changed to "abcba" which is palidrome.
// Example 2:

// Input: s = "lyb", queries = [[0,1,0],[2,2,1]]
// Output: [false,true]
 

// Constraints:

// 1 <= s.length, queries.length <= 105
// 0 <= lefti <= righti < s.length
// 0 <= ki <= s.length
// s consists of lowercase English letters.

class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] dp = new int[26][n];
        List<Boolean> ans = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            char curChar = (char)(i + 'a');
            dp[i][0] = s.charAt(0) == curChar ? 1: 0;
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + (s.charAt(j) == curChar ? 1: 0);
            }
        }

        for (int[] q: queries) {
            int left = q[0];
            int right = q[1];
            int k = q[2];

            int unMatchedCount = 0;

            for (int i = 0; i < 26; i++) {
                int occurence = dp[i][right] - dp[i][left] + (s.charAt(left) == i + 'a' ? 1: 0);

                if (occurence % 2 == 1) {
                    unMatchedCount += 1;
                }
            }

            if (unMatchedCount / 2 <= k) {
                ans.add(true);
            } else {
                ans.add(false);
            }
        }

        return ans;
    }
}