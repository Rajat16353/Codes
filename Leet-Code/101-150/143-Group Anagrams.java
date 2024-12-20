// Given an array of strings strs, group the anagrams together. You can return the answer in any order.

// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

// Example 1:

// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
// Example 2:

// Input: strs = [""]
// Output: [[""]]
// Example 3:

// Input: strs = ["a"]
// Output: [["a"]]
 

// Constraints:

// 1 <= strs.length <= 104
// 0 <= strs[i].length <= 100
// strs[i] consists of lowercase English letters.

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groupLists = new ArrayList<>();
        Map<String, List<String>> anagram = new HashMap<>();

        for (String word: strs) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            anagram.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        for (Map.Entry<String, List<String>> entry: anagram.entrySet()) {
            groupLists.add(entry.getValue());
        }
        
        return groupLists;
    }
}