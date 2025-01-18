// Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

// Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

// Example 1:

// Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
// Output: ["cats and dog","cat sand dog"]
// Example 2:

// Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
// Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
// Explanation: Note that you are allowed to reuse a dictionary word.
// Example 3:

// Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
// Output: []
 

// Constraints:

// 1 <= s.length <= 20
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 10
// s and wordDict[i] consist of only lowercase English letters.
// All the strings of wordDict are unique.
// Input is generated in a way that the length of the answer doesn't exceed 105.

// recursion and memoisation
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        List<String>[] dp = new ArrayList[n + 1];
        return recursion(0, s, dict, dp);
    }

    private List<String> recursion(int start, String s, Set<String> dict, List<String>[] dp) {
        List<String> validSentences = new ArrayList<>();
        
        if (dp[start] != null) return dp[start];
        
        if (start == s.length()) validSentences.add("");
    
        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (dict.contains(prefix)) {
                List<String> suffixes = recursion(end, s, dict, dp);
                for (String suffix: suffixes) {
                    validSentences.add(prefix + (suffix.equals("") ? "" : " ") + suffix);
                }
            }
        }

        return dp[start] = validSentences;
    }
}

// tabulation
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        List<String>[] dp = new ArrayList[n + 1];

        dp[n] = new ArrayList<>(Arrays.asList(""));

        for (int start = n - 1; start >= 0; start--) {
            List<String> validSentences = new ArrayList<>();
            for (int end = start + 1; end <= s.length(); end++) {
                String prefix = s.substring(start, end);
                if (dict.contains(prefix)) {
                    List<String> suffixes = dp[end];
                    for (String suffix: suffixes) {
                        validSentences.add(prefix + (suffix.equals("") ? "" : " ") + suffix);
                    }
                }
            }

            dp[start] = validSentences;
        }

        return dp[0];
    }
}

// Simple backtracking best approach
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();

        backtrack(0, new StringBuilder(), result, s, dict);

        return result;
    }

    private void backtrack(int start, StringBuilder currentSentence, List<String> result, String s, Set<String> dict) {
        if (start == s.length()) {
            result.add(currentSentence.toString().trim());
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (dict.contains(word)) {
                int curLength = currentSentence.length();
                currentSentence.append(word).append(" ");
                backtrack(end, currentSentence, result, s, dict);
                currentSentence.setLength(curLength);
            }
        }
    }
}