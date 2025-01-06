// You are given an array of words where each word consists of lowercase English letters.

// wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

// For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
// A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

// Return the length of the longest possible word chain with words chosen from the given list of words.

 

// Example 1:

// Input: words = ["a","b","ba","bca","bda","bdca"]
// Output: 4
// Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
// Example 2:

// Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
// Output: 5
// Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
// Example 3:

// Input: words = ["abcd","dbqca"]
// Output: 1
// Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
// ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 

// Constraints:

// 1 <= words.length <= 1000
// 1 <= words[i].length <= 16
// words[i] only consists of lowercase English letters.

class Solution {
    private String chainWord(String word, int charPosition) {
        return word.substring(0, charPosition) + word.substring(charPosition + 1);
    }

    private int maxWordChain(Map<String, Integer> dp, String word) {
        int maxChain = 1;
        for(int i = 0; i < word.length(); i++) {
            String key = chainWord(word, i);
            if (dp.containsKey(key)) {
                maxChain = Math.max(maxChain, dp.get(key) + 1);
            }
        }
        dp.put(word, maxChain);
        return maxChain;
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparing(word -> word.length()));
        Map<String, Integer> dp = new HashMap<>();

        int longestWordChain = 1;
        for(int j = 0; j < words.length; j++) {
            longestWordChain = Math.max(longestWordChain, maxWordChain(dp, words[j]));
        }
        return longestWordChain;
    }
}

// Using tabulation
class Solution {
    public int longestStrChain(String[] words) {
        int n = words.length;
        int ans = -1;
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (checkPossible(words[i], words[j]) && 1 + dp[j] > dp[i]) {
                    dp[i] = 1 + dp[j];
                }
            }
            if (ans < dp[i]) {
                ans = dp[i];
            }
        }

        return ans;
    }    

    private boolean checkPossible(String s1, String s2) {
        if (s1.length() != s2.length() + 1) return false;
        int first = 0;
        int second = 0;

        while (first < s1.length()) {
            if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                first += 1;
                second += 1;
            } else {
                first += 1;
            }
        }

        if (first == s1.length() && second == s2.length()) return true;
        return false;
    }
}