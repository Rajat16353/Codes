// Given an m x n grid of characters board and a string word, return true if word exists in the grid.

// The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

// Example 1:


// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
// Output: true
// Example 2:


// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
// Output: true
// Example 3:


// Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
// Output: false
 

// Constraints:

// m == board.length
// n = board[i].length
// 1 <= m, n <= 6
// 1 <= word.length <= 15
// board and word consists of only lowercase and uppercase English letters.
 

// Follow up: Could you use search pruning to make your solution faster with a larger board?

// Using set for visited letters
class Solution {
    private Boolean dfs(int i, int j, char[][] board, int k, String word, Set<Pair<Integer, Integer>> visited){
        if(k == word.length()) {
            return true;
        }
        
        if(i >= board.length || j >= board[0].length || i < 0 || j < 0 || visited.contains(new Pair(i,j)) || word.charAt(k) != board[i][j]){
            return false;
        }
                
        visited.add(new Pair(i,j));
        Boolean res = dfs(i+1, j, board, k+1, word, visited) ||
        dfs(i, j+1, board, k+1, word, visited) ||
        dfs(i-1, j, board, k+1, word, visited) ||
        dfs(i, j-1, board, k+1, word, visited);
        visited.remove(new Pair(i,j));
        
        return res;
    }
    
    public boolean exist(char[][] board, String word) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[0].length; j++) {
                if (dfs(i, j, board, 0, word, visited)) return true;
            }
        }
        return false;
    }
}

// Replacing visited letters with ' ' to avoid revisiting them
class Solution {
    private Boolean dfs(int i, int j, char[][] board, int k, String word){
        if(k == word.length()) {
            return true;
        }
        
        if(i >= board.length || j >= board[0].length || i < 0 || j < 0 ||  word.charAt(k) != board[i][j]){
            return false;
        }
                
        char temp = board[i][j];
        board[i][j] = ' ';
        
        Boolean res = dfs(i+1, j, board, k+1, word) ||
        dfs(i, j+1, board, k+1, word) ||
        dfs(i-1, j, board, k+1, word) ||
        dfs(i, j-1, board, k+1, word);
        
        board[i][j] = temp;
        
        return res;
    }
    
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i<board.length; i++) {
            for(int j = 0; j<board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(i, j, board, 0, word))
                    return true;
            }
        }
        return false;
    }
}