// In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative. For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".

// Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.

// Return the sentence after the replacement.

 

// Example 1:

// Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
// Output: "the cat was rat by the bat"
// Example 2:

// Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
// Output: "a a b c"
 

// Constraints:

// 1 <= dictionary.length <= 1000
// 1 <= dictionary[i].length <= 100
// dictionary[i] consists of only lower-case letters.
// 1 <= sentence.length <= 106
// sentence consists of only lower-case letters and spaces.
// The number of words in sentence is in the range [1, 1000]
// The length of each word in sentence is in the range [1, 1000]
// Every two consecutive words in sentence will be separated by exactly one space.
// sentence does not have leading or trailing spaces.

class Solution {
    class TrieNode {
        Map<Character, TrieNode> prefixMap;
        boolean isRoot;
        
        public TrieNode() {
            prefixMap = new HashMap<>();
        }
        
        private boolean contains(char c) {
            return this.prefixMap.containsKey(c);
        }
        
        private void add(char c) {
            this.prefixMap.put(c, new TrieNode());
        }
        
        private TrieNode get(char c) {
            return this.prefixMap.get(c);
        }
        
        public void insert(String word) {
            TrieNode node = this;
            for(char c: word.toCharArray()) {
                if (!node.contains(c)) {
                    node.add(c);
                }
                node = node.get(c);
            }
            node.isRoot = true;
        }
        
        public String search(String word) {
            TrieNode node = this;
            StringBuilder sb = new StringBuilder();
            
            for(char c: word.toCharArray()) {
                if (!node.contains(c)) {
                    return word;
                }
                node = node.get(c);
                sb.append(c);
                if (node.isRoot) return sb.toString();
            }
            
            return sb.toString();
        }
    }
    
    TrieNode root;
    
    private void createRootDict(List<String> dictionary) {
        for(String word: dictionary) {
            root.insert(word);
        }
    }
    
    public String replaceWords(List<String> dictionary, String sentence) {
        root = new TrieNode();
        createRootDict(dictionary);
        
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = root.search(words[i]);
        }
        
        return String.join(" ", words);
    }
}