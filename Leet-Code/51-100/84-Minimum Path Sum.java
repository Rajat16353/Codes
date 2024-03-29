// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

// Note: You can only move either down or right at any point in time.

 

// Example 1:


// Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
// Output: 7
// Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
// Example 2:

// Input: grid = [[1,2,3],[4,5,6]]
// Output: 12
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 200
// 0 <= grid[i][j] <= 100

class Solution {
    public int minPathSum(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        int[][] result = new int[r+1][c+1];
        for(int i = 0; i < r+1; i++) {
            for(int j = 0; j< c+1; j++) {
                if(i == r && j == c-1)
                    result[i][j] = 0;
                else
                    result[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for(int i = r-1; i > -1; i--){
            for(int j = c-1; j > -1; j--) {
                result[i][j] = grid[i][j] + Math.min(result[i+1][j], result[i][j+1]);
            }   
        }            
        return result[0][0];
    }
}