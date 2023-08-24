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
        List<List<String>> groupLists = new ArrayList<List<String>>();
        Map<String, List<String>> anagram = new HashMap<>();

        for (String word: strs) {
            char[] charArray = word.toCharArray();
            Arrays.sort(charArray);
            String sortedWord = new String(charArray);
            if (anagram.containsKey(sortedWord)) {
                anagram.get(sortedWord).add(word);
            } else {
                List<String> groupList = new ArrayList<>();
                groupList.add(word);
                anagram.put(sortedWord, groupList);
            }
        }

        for (List<String> groupList: anagram.values()) {
            groupLists.add(groupList);
        }

        return groupLists;
    }
}