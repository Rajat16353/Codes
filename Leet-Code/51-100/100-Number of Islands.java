// Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

// An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

// Example 1:

// Input: grid = [
//   ["1","1","1","1","0"],
//   ["1","1","0","1","0"],
//   ["1","1","0","0","0"],
//   ["0","0","0","0","0"]
// ]
// Output: 1
// Example 2:

// Input: grid = [
//   ["1","1","0","0","0"],
//   ["1","1","0","0","0"],
//   ["0","0","1","0","0"],
//   ["0","0","0","1","1"]
// ]
// Output: 3
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] is '0' or '1'.

class Solution {
    private void bfs(char[][] grid, int row, int column, Set<Pair<Integer, Integer>> visited) {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Pair<Integer, Integer> rc = new Pair(row, column);
        
        visited.add(rc);
        queue.offer(rc);

        while(!queue.isEmpty()) {
            Pair<Integer, Integer> rc1 = queue.poll();
            
            int r = rc1.getKey();
            int c = rc1.getValue();
            
            int[][] iterations = {{r+1, c}, {r, c+1}, {r-1, c}, {r, c-1}};

            for(int[] i: iterations) {
                Pair<Integer, Integer> pair = new Pair<>(i[0], i[1]);
                
                if (i[0] < grid.length && i[0] >= 0 && i[1] < grid[0].length && i[1] >= 0 && !visited.contains(pair) && grid[i[0]][i[1]] == '1') {
                    queue.offer(pair);
                    visited.add(pair);
                }
            }
        }
    }
    
    public int numIslands(char[][] grid) {        
        int rows = grid.length, columns = grid[0].length;
        
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        
        int islands = 0;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid[r][c] == '1' && !visited.contains(new Pair(r, c)))                               
                {
                    bfs(grid, r, c, visited);
                    islands += 1;
                } 
            }
        }
        
        return islands;
    }
}