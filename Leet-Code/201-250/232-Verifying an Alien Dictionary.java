// In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

// Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

 

// Example 1:

// Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
// Output: true
// Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
// Example 2:

// Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
// Output: false
// Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
// Example 3:

// Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
// Output: false
// Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

// Constraints:

// 1 <= words.length <= 100
// 1 <= words[i].length <= 20
// order.length == 26
// All characters in words[i] and order are English lowercase letters.

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> orderMap = new HashMap<>();
        int i = 1;
        for (char c: order.toCharArray()) {
            orderMap.put(c, i++);
        }

        for (int j = 0; j < words.length - 1; j++) {
            String word1 = words[j];
            String word2 = words[j + 1];
            
            for (int k = 0; k < word1.length(); k++) {
                if (k == word2.length()) {
                    return false;
                }
                if (word1.charAt(k) != word2.charAt(k)) {
                    if (orderMap.get(word2.charAt(k)) < orderMap.get(word1.charAt(k))) {
                        return false;
                    }
                    break;
                }
            }
        }

        return true;
    }
}