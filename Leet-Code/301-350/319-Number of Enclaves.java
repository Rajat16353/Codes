// You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

// A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

// Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

// Example 1:


// Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
// Output: 3
// Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
// Example 2:


// Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
// Output: 0
// Explanation: All 1s are either on the boundary or can reach the boundary.
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 500
// grid[i][j] is either 0 or 1.

class Solution {
    private final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 || c == 0 || r == m - 1 || c == n - 1) {
                    if (grid[r][c] == 1) dfs(grid, r, c);
                }
            }
        }

        int count = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) count++;
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return;
        }

        grid[r][c] = 0;
        
        for(int i = 0; i < DIRS.length; i++) {
            dfs(grid, r + DIRS[i][0], c + DIRS[i][1]);
        }
    }
}