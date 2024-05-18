// You are given a string s and an array of strings words. All the strings of words are of the same length.

// A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.

// For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
// Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.

 

// Example 1:

// Input: s = "barfoothefoobarman", words = ["foo","bar"]

// Output: [0,9]

// Explanation:

// The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
// The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

// Example 2:

// Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]

// Output: []

// Explanation:

// There is no concatenated substring.

// Example 3:

// Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]

// Output: [6,9,12]

// Explanation:

// The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
// The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
// The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].

 

// Constraints:

// 1 <= s.length <= 104
// 1 <= words.length <= 5000
// 1 <= words[i].length <= 30
// s and words[i] consist of lowercase English letters.


public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s.length() == 0 || words.length == 0)
            return result;

        int wordLength = words[0].length();
        int totalWords = words.length;
        int substringLength = wordLength * totalWords;
        if (s.length() < substringLength){
            return result;
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wordLength; i++) {
            Map<String, Integer> currentWordCount = new HashMap<>();
            int wordsFound = 0;
            int left = i;

            for (int j = i; j <= s.length() - wordLength; j += wordLength) {
                String currentWord = s.substring(j, j + wordLength);
                if (wordCount.containsKey(currentWord)) {
                    currentWordCount.put(currentWord, currentWordCount.getOrDefault(currentWord, 0) + 1);
                    wordsFound++;
                    while (currentWordCount.get(currentWord) > wordCount.get(currentWord)) {
                        String leftWord = s.substring(left, left + wordLength);
                        currentWordCount.put(leftWord, currentWordCount.get(leftWord) - 1);
                        wordsFound--;
                        left += wordLength;
                    }
                    if (wordsFound == totalWords) {
                        result.add(left);
                        String leftWord = s.substring(left, left + wordLength);
                        currentWordCount.put(leftWord, currentWordCount.get(leftWord) - 1);
                        wordsFound--;
                        left += wordLength;
                    }
                }
                else {
                    currentWordCount.clear();
                    wordsFound = 0;
                    left = j + wordLength;
                }
            }
        }

        return result;
    }
}