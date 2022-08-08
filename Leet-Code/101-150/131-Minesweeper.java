// Let's play the minesweeper game (Wikipedia, online game)!

// You are given an m x n char matrix board representing the game board where:

// 'M' represents an unrevealed mine,
// 'E' represents an unrevealed empty square,
// 'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
// digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
// 'X' represents a revealed mine.
// You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').

// Return the board after revealing this position according to the following rules:

// If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
// If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
// If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
// Return the board when no more squares will be revealed.
 

// Example 1:


// Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
// Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
// Example 2:


// Input: board = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]], click = [1,2]
// Output: [["B","1","E","1","B"],["B","1","X","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
 

// Constraints:

// m == board.length
// n == board[i].length
// 1 <= m, n <= 50
// board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
// click.length == 2
// 0 <= clickr < m
// 0 <= clickc < n
// board[clickr][clickc] is either 'M' or 'E'.

// if board[x][y] = M
//     board[x][y] = x
//     return
// else
//     if mine in adjacent of board[x][y] 
//         board[x][y] = count of mines
//     else
//         board[x][y] = E
//     repeat the else block
    

class Solution {
    private int getAdjacentMinesCount(char[][] board, int x, int y) {
        int count = 0;
        
        for (int r = x-1; r <= x+1; r++) {
            for (int c = y-1; c <= y+1; c++) {
                if (r == x && c == y) {
                    continue;
                }
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length == 0){
            return board;
        }
        int x = click[0], y = click[1];
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            int adjacentMinesCount = getAdjacentMinesCount(board, x, y);
            if (adjacentMinesCount == 0) {
                board[x][y] = 'B';
                for (int r = x-1; r <= x+1; r++) {
                    for (int c = y-1; c <= y+1; c++) {
                        if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] != 'B') {
                            click[0] = r;
                            click[1] = c;
                            updateBoard(board, click);
                        }
                            
                    }
                }
            } else {
                board[x][y] = (char)('0' + adjacentMinesCount);
            }
        }
        return board;
    }
}