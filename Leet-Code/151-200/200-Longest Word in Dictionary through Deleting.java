// Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

 

// Example 1:

// Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
// Output: "apple"
// Example 2:

// Input: s = "abpcplea", dictionary = ["a","b","c"]
// Output: "a"
 

// Constraints:

// 1 <= s.length <= 1000
// 1 <= dictionary.length <= 1000
// 1 <= dictionary[i].length <= 1000
// s and dictionary[i] consist of lowercase English letters.

// Unoptimized code
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        for (int i = 0; i < dictionary.size(); i++) {
            int j = 0;
            String word = dictionary.get(i);
            if (word.length() > s.length()) {
                continue;
            }
            int k = 0;
            while(k < word.length() && j < s.length()) {
                if (word.charAt(k) == s.charAt(j)) {
                    j++;
                    k++;
                } else {
                    j++;
                }
            }
            if (k == word.length() && k > longest) {
                longest = k;
                wordPos = i;
                System.out.println("if " + wordPos);
            } else if (k == word.length() && k == longest  && word.compareTo(dictionary.get(wordPos)) < 0) {
                longest = k;
                wordPos = i;
                System.out.println("else " + wordPos);
            }
        }
        if (wordPos != -1) {
            return dictionary.get(wordPos);
        }
        return "";
    }
}

// Optimized code
class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String longest = "";

        for (String word: dictionary) {
            int i = 0;
            for(char c: s.toCharArray()) {
                if (i < word.length() && c == word.charAt(i)) {
                    i++;
                }
            }

            if (i == word.length() && i >= longest.length()) {
                if (i > longest.length() || word.compareTo(longest) < 0) {
                    longest = word;
                }
            }
        }
        return longest;
    }
}