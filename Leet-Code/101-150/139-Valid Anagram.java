// Given two strings s and t, return true if t is an anagram of s, and false otherwise.

// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

// Example 1:

// Input: s = "anagram", t = "nagaram"
// Output: true
// Example 2:

// Input: s = "rat", t = "car"
// Output: false
 

// Constraints:

// 1 <= s.length, t.length <= 5 * 104
// s and t consist of lowercase English letters.
 

// Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?

class Solution {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> anagramMap = new HashMap<>();

        if (s.length() != t.length()) {
            return false;
        }

        for(char c: s.toCharArray()) {
            if (anagramMap.containsKey(c)) {
                anagramMap.put(c, anagramMap.get(c)+1);
            } else {
                anagramMap.put(c, 1);
            }
        }

        for (char c: t.toCharArray()) {
            if (!anagramMap.containsKey(c) || anagramMap.get(c) == 0) {
                return false;
            } else {
                anagramMap.put(c, anagramMap.get(c)-1);
            }
        }

        return true;
    }
}