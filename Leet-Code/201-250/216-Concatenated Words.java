// Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.

// A concatenated word is defined as a string that is comprised entirely of at least two shorter words (not necessarily distinct) in the given array.

 

// Example 1:

// Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
// Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
// Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
// "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
// "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
// Example 2:

// Input: words = ["cat","dog","catdog"]
// Output: ["catdog"]
 

// Constraints:

// 1 <= words.length <= 104
// 1 <= words[i].length <= 30
// words[i] consists of only lowercase English letters.
// All the strings of words are unique.
// 1 <= sum(words[i].length) <= 105

// Using dp and recursion
class Solution {
    Set<String> wordSet;
    List<String> concatenatedWords;
    Map<String, Boolean> dp;
    private Boolean dfs(String word) {
        if (dp.containsKey(word)) {
            return dp.get(word);
        }
        for (int i = 1; i < word.length() + 1; i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if ((wordSet.contains(prefix) && wordSet.contains(suffix)) || (wordSet.contains(prefix) && dfs(suffix))) {
                dp.put(word, true);
                return true;
            }
        }
        dp.put(word, false);
        return false;
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        wordSet = new HashSet<>();
        concatenatedWords = new ArrayList<>();
        dp = new HashMap<>();
        for (String word: words) {
            wordSet.add(word);
        }

        for (String word: words) {
            if (dfs(word)) {
                concatenatedWords.add(word);
            }
        }
        return concatenatedWords;
    }
}