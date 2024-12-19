// Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

// Return the number of closed islands.

 

// Example 1:



// Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
// Output: 2
// Explanation: 
// Islands in gray are closed because they are completely surrounded by water (group of 1s).
// Example 2:



// Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
// Output: 1
// Example 3:

// Input: grid = [[1,1,1,1,1,1,1],
//                [1,0,0,0,0,0,1],
//                [1,0,1,1,1,0,1],
//                [1,0,1,0,1,0,1],
//                [1,0,1,1,1,0,1],
//                [1,0,0,0,0,0,1],
//                [1,1,1,1,1,1,1]]
// Output: 2
 

// Constraints:

// 1 <= grid.length, grid[0].length <= 100
// 0 <= grid[i][j] <=1

class Solution {
    int m;
    int n;
    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int islands = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0 && dfs(grid, r, c)) {
                    islands += 1;
                }
            }
        }

        return islands;
    }

    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public boolean dfs(int[][] grid,int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }
        if (grid[i][j] == 1) return true;
        grid[i][j] = 1;
        boolean left = dfs(grid,i, j-1), right = dfs(grid,i, j+1);
        boolean up = dfs(grid,i-1, j), down = dfs(grid,i+1, j);
        return left && right && up && down;
    }
}