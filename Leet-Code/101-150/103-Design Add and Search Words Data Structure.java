// Design a data structure that supports adding new words and finding if a string matches any previously added string.

// Implement the WordDictionary class:

// WordDictionary() Initializes the object.
// void addWord(word) Adds word to the data structure, it can be matched later.
// bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 

// Example:

// Input
// ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
// [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
// Output
// [null,null,null,null,false,true,true,true]

// Explanation
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("bad");
// wordDictionary.addWord("dad");
// wordDictionary.addWord("mad");
// wordDictionary.search("pad"); // return False
// wordDictionary.search("bad"); // return True
// wordDictionary.search(".ad"); // return True
// wordDictionary.search("b.."); // return True
 

// Constraints:

// 1 <= word.length <= 25
// word in addWord consists of lowercase English letters.
// word in search consist of '.' or lowercase English letters.
// There will be at most 3 dots in word for search queries.
// At most 104 calls will be made to addWord and search.

class TrieNode {
    Map<Character, TrieNode> children;
    Boolean isWord;
    
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
}

class WordDictionary {
    TrieNode root;
   
    public WordDictionary() {
        root = new TrieNode();
    }
    
    private Boolean searchWithDFS(int index, TrieNode node, String word) {
        TrieNode current = node;
        
        for(int i = index; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                for(Map.Entry<Character, TrieNode> entry: current.children.entrySet()) {
                    if (searchWithDFS(i+1, entry.getValue(), word)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (!current.children.containsKey(c)) {
                    return false;
                }
                current = current.children.get(c);
            }
        }
        return current.isWord;
    }
    
    public void addWord(String word) {
        TrieNode current = root;
        for(char c: word.toCharArray()) { 
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isWord = true;
    }
    
    public boolean search(String word) {
        return searchWithDFS(0, root, word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */