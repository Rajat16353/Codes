// You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

// An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

// You may change 0's to 1's to connect the two islands to form one island.

// Return the smallest number of 0's you must flip to connect the two islands.

 

// Example 1:

// Input: grid = [[0,1],[1,0]]
// Output: 1
// Example 2:

// Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
// Output: 2
// Example 3:

// Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
// Output: 1
 

// Constraints:

// n == grid.length == grid[i].length
// 2 <= n <= 100
// grid[i][j] is either 0 or 1.
// There are exactly two islands in grid.

class Solution {
    private class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int shortestBridge(int[][] grid) {
        Queue<Pair> queue = new LinkedList<>();
        boolean island = false;

        for (int r = 0; r < grid.length && !island; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    dfs(grid, r, c, queue);
                    island = true;
                    break;
                }
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int k = queue.size();

            while (k > 0) {
                Pair cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int r = cur.r + dirs[i][0];
                    int c = cur.c + dirs[i][1];
                    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 3) {
                        continue;
                    }
                    
                    if (grid[r][c] == 1) {
                        return level;
                    }
                    grid[r][c] = 3;
                    queue.offer(new Pair(r, c));
                }
                k--;
            }
            level++;
        }

        return -1;
    }

    private final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private void dfs(int[][] grid, int r, int c, Queue<Pair> queue) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0 || grid[r][c] == 3) {
            return;
        }

        grid[r][c] = 3;
        queue.offer(new Pair(r, c));

        for (int i = 0; i < 4; i++) {
            dfs(grid, r + dirs[i][0], c + dirs[i][1], queue);
        }
    }
}