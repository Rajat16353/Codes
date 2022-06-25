// A string is considered beautiful if it satisfies the following conditions:

// Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
// The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
// For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.

// Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.

// A substring is a contiguous sequence of characters in a string.

 

// Example 1:

// Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
// Output: 13
// Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
// Example 2:

// Input: word = "aeeeiiiioooauuuaeiou"
// Output: 5
// Explanation: The longest beautiful substring in word is "aeiou" of length 5.
// Example 3:

// Input: word = "a"
// Output: 0
// Explanation: There is no beautiful substring, so return 0.
 

// Constraints:

// 1 <= word.length <= 5 * 105
// word consists of characters 'a', 'e', 'i', 'o', and 'u'.

class Solution {
    private void resetSet(Set<Character> vowels) {
        vowels.clear();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
    }
    
    public int longestBeautifulSubstring(String word) {
        Set<Character> vowels = new HashSet<>();
        resetSet(vowels);
        
        int l = 0, r = 1, maxLength = 0, len = 0;
        while(l < word.length()) {
            Boolean end = false;
            if(l >= word.length() -1) {
                end = true;
            }
            vowels.remove((Character)word.charAt(l));
            len++;
            if (end || word.charAt(r) - word.charAt(l) < 0) {
                if (vowels.isEmpty()) {
                    maxLength = Math.max(len, maxLength);
                }
                
                len = 0;
                resetSet(vowels);
            }
            l++;
            r++;
        }
        return maxLength;
    }
}