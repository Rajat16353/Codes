// In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.

// Return the maximum amount of gold you can collect under the conditions:

// Every time you are located in a cell you will collect all the gold in that cell.
// From your position, you can walk one step to the left, right, up, or down.
// You can't visit the same cell more than once.
// Never visit a cell with 0 gold.
// You can start and stop collecting gold from any position in the grid that has some gold.
 

// Example 1:

// Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
// Output: 24
// Explanation:
// [[0,6,0],
//  [5,8,7],
//  [0,9,0]]
// Path to get the maximum gold, 9 -> 8 -> 7.
// Example 2:

// Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
// Output: 28
// Explanation:
// [[1,0,7],
//  [2,0,6],
//  [3,4,5],
//  [0,3,0],
//  [9,0,20]]
// Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 15
// 0 <= grid[i][j] <= 100
// There are at most 25 cells containing gold.

class Solution {
    private final int[][] DFS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int m;
    int n;
    private int getMaximumGold(int[][] grid, int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m || grid[r][c] == 0) {
            return 0;
        }

        int max = 0;
        int temp = grid[r][c];
        grid[r][c] = 0;

        for (int i = 0; i < DFS.length; i++) {
            max = Math.max(max, temp + getMaximumGold(grid, r + DFS[i][0], c + DFS[i][1]));
        }

        grid[r][c] = temp;
        return max;
    }

    public int getMaximumGold(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int max = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (grid[r][c] == 0) continue;
                max = Math.max(max, getMaximumGold(grid, r, c));
            }
        }

        return max;
    }
}