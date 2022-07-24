// Given an m x n board of characters and a list of strings words, return all words on the board.

// Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

// Example 1:


// Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
// Output: ["eat","oath"]
// Example 2:


// Input: board = [["a","b"],["c","d"]], words = ["abcb"]
// Output: []
 

// Constraints:

// m == board.length
// n == board[i].length
// 1 <= m, n <= 12
// board[i][j] is a lowercase English letter.
// 1 <= words.length <= 3 * 104
// 1 <= words[i].length <= 10
// words[i] consists of lowercase English letters.
// All the strings of words are unique.

class TrieNode {
    Map<Character, TrieNode> children;
    Boolean isWord;
    
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }
    
    public void addWord(String word) {
        TrieNode current = this;
        for(char c: word.toCharArray()) { 
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isWord = true;
    }
    
}

class Solution {
    // TrieNode root;
    // public Solution() {
    //     root = new TrieNode();
    // }
    
    private void findWithDfs(int r, int c, char[][]board, TrieNode node, String word, Set<String> result) {
        Pair rc = new Pair(r, c);
        if ((r < 0 || c < 0) || (r == board.length || c == board[0].length) || (!node.children.containsKey(board[r][c])) || (board[r][c] == '#')) {
            return;
        }
        
        char original = board[r][c];
        node = node.children.get(board[r][c]);
        word += board[r][c];
        board[r][c] = '#';
        
        if (node.isWord) {
            result.add(word);
        }
        
        findWithDfs(r+1, c, board, node, word, result);
        findWithDfs(r-1, c, board, node, word, result);
        findWithDfs(r, c+1, board, node, word, result);
        findWithDfs(r, c-1, board, node, word, result);
        
        board[r][c] = original;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        
        for(String word: words) {
            root.addWord(word);
        }
        
        Set<String> result = new HashSet<>();
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                findWithDfs(r, c, board, root, "", result);
            }
        }
        System.out.println(result.toString());
        return new ArrayList<>(result);
    }
}