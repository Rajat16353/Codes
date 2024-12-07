// Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

// Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

// Example 1:

// Input: s = "leetcode", wordDict = ["leet","code"]
// Output: true
// Explanation: Return true because "leetcode" can be segmented as "leet code".
// Example 2:

// Input: s = "applepenapple", wordDict = ["apple","pen"]
// Output: true
// Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
// Note that you are allowed to reuse a dictionary word.
// Example 3:

// Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
// Output: false
 

// Constraints:

// 1 <= s.length <= 300
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 20
// s and wordDict[i] consist of only lowercase English letters.
// All the strings of wordDict are unique.

// DP using for loop inside dfs
class Solution {
    Set<String> dict;
    Map<String, Boolean> memo;

    private Boolean dfs(String word) {
        if (memo.containsKey(word)) {
            return memo.get(word);
        }
        if (dict.contains(word)) {
            return true;
        }
        for (int i = 1; i < word.length(); i++) {
            String subString = word.substring(0, i);
            if (dict.contains(subString) && dfs(word.substring(i))) {
                memo.put(word, true);
                return true;
            }
        }
        memo.put(word, false);
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        memo = new HashMap<>();
        
        return dfs(s);
    }
}

//Bottom Up approach
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        
        dp[s.length()] = true;

        for(int i = s.length() - 1; i >= 0; i--) {
            int j = i;
            while(j < s.length()) {
                String subString = s.substring(i, j + 1);
                if (dict.contains(subString) && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
                j++;
            }
        }

        return dp[0];
    }
}