// The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

// Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

 

// Example 1:


// Input: n = 4
// Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
// Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
// Example 2:

// Input: n = 1
// Output: [["Q"]]
 

// Constraints:

// 1 <= n <= 9

// Using Backtracking
class Solution {
    String[][] board;
    Set<Integer> columns;
    Set<Integer> posDiagonal;
    Set<Integer> negDiagonal;

    private void fillBoard(int n) {
        board = new String[n][n];
        for(int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                board[r][c] = ".";
            }
        }
    }

    private List<String> generateResultString(int n) {
        List<String> res = new ArrayList<>();
        for(int r = 0; r < n; r++) {
            res.add(String.join("", board[r]));
        }

        return res;
    }

    private void solveNQueens(int r, int n, List<List<String>> result) {
        if (r == n) {
            result.add(generateResultString(n));
            return;
        }

        for(int c = 0; c < n; c++) {
            if (columns.contains(c) || posDiagonal.contains(r + c) || negDiagonal.contains(r - c)) {
                continue;
            }

            columns.add(c);
            posDiagonal.add(r + c);
            negDiagonal.add(r - c);
            board[r][c] = "Q";

            solveNQueens(r + 1, n, result);

            columns.remove(c);
            posDiagonal.remove(r + c);
            negDiagonal.remove(r - c);
            board[r][c] = ".";
        }
    }

    public List<List<String>> solveNQueens(int n) {
        fillBoard(n);
        columns = new HashSet<>();
        posDiagonal = new HashSet<>();
        negDiagonal = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        solveNQueens(0, n, result);
        return result;
    }
}