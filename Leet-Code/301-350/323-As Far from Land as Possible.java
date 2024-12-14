// Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

// The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

// Example 1:


// Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
// Output: 2
// Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
// Example 2:


// Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
// Output: 4
// Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

// Constraints:

// n == grid.length
// n == grid[i].length
// 1 <= n <= 100
// grid[i][j] is 0 or 1

class Solution {
    private static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int maxDistance(int[][] grid) {
        Queue<Pair> queue = new LinkedList<>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    queue.offer(new Pair(r, c));
                }
            }
        }

        if (queue.size() == 0 || queue.size() == grid.length * grid[0].length) {
            return -1;
        }

        int level = -1;
        while (!queue.isEmpty()) {
            level++;
            int k = queue.size();
            while(k > 0) {
                Pair rc = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int row = rc.r + dirs[i][0];
                    int col = rc.c + dirs[i][1];

                    if (row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 0) {
                        queue.offer(new Pair(row, col));
                        grid[row][col] = 1;
                    }
                }
                k--;
            }
        }

        return level;
    }
}