// You are given an m x n grid where each cell can have one of three values:

// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

// Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

// Example 1:


// Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
// Output: 4
// Example 2:

// Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
// Output: -1
// Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
// Example 3:

// Input: grid = [[0,2]]
// Output: 0
// Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 

// Constraints:

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 10
// grid[i][j] is 0, 1, or 2.

class Solution {
    private final int[][] BFS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    // private int orangesRotting(int[][] grid, int r, int c, int minutes) {
    //     if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0 || grid[r][c] == 2) {
    //         return minutes - 1;
    //     }
    //     System.out.println("r: " + r + " c: " + c + " min: " + minutes);
    //     grid[r][c] = 2;
        // for(int i = 0; i < BFS.length; i++) {
        //     minutes = Math.max(orangesRotting(grid, r + BFS[i][0], c + BFS[i][1], minutes + 1), minutes);
        // }

    //     return minutes;
    // }

        // int minutes = 0;
        // for (int i = 0; i < grid.length; i++) {
        //     for (int j = 0; j < grid[i].length; j++) {
        //         if (grid[i][j] == 2) {
        //             grid[i][j] = 1;
        //             minutes += orangesRotting(grid, i, j, 0);
        //         }
        //     }
        // }

        // return minutes;

    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int minutes = 0;
        int oranges = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    oranges++;
                }
            }
        }

        boolean rot = false;

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                int[] rc = queue.poll();
                for(int i = 0; i < BFS.length; i++) {
                    int r = rc[0] + BFS[i][0];
                    int c = rc[1] + BFS[i][1];
                    if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
                        rot = true;
                        grid[r][c] = 2;
                        oranges--;
                        queue.offer(new int[]{r, c});
                    }
                }
                size--;
            }
            if (rot) {
                minutes++;
                rot = false;
            }
        }
        return oranges > 0 ? -1: minutes;
    }
}