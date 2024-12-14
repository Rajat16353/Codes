// You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid represents the color of the grid square at that location.

// Two squares are called adjacent if they are next to each other in any of the 4 directions.

// Two squares belong to the same connected component if they have the same color and they are adjacent.

// The border of a connected component is all the squares in the connected component that are either adjacent to (at least) a square not in the component, or on the boundary of the grid (the first or last row or column).

// You should color the border of the connected component that contains the square grid[row][col] with color.

// Return the final grid.

 

// Example 1:

// Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
// Output: [[3,3],[3,2]]
// Example 2:

// Input: grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
// Output: [[1,3,3],[2,3,3]]
// Example 3:

// Input: grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
// Output: [[2,2,2],[2,1,2],[2,2,2]]
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 50
// 1 <= grid[i][j], color <= 1000
// 0 <= row < m
// 0 <= col < n

class Solution {
    private final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int[][] visited = new int[grid.length][grid[0].length];

        dfs(grid, row, col, grid[row][col], visited);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0) {
                    grid[i][j] = color;
                }
            }
        }
        return grid;
    }

    private void dfs(int[][] grid, int r, int c, int clr, int[][] visited) {
        grid[r][c] = -clr;
        visited[r][c] = 1;
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int newRow = r + DIRS[i][0];
            int newCol = c + DIRS[i][1]; 
            if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length || Math.abs(grid[newRow][newCol]) != clr) {
                continue;
            }
            count++;
            if (grid[newRow][newCol] == clr && visited[newRow][newCol] != 1) dfs(grid, newRow, newCol, clr, visited);
        }

        if (count == 4) {
            grid[r][c] = clr;
        }
    }
}