// Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

// The distance between two adjacent cells is 1.

 

// Example 1:


// Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
// Output: [[0,0,0],[0,1,0],[0,0,0]]
// Example 2:


// Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
// Output: [[0,0,0],[0,1,0],[1,2,1]]
 

// Constraints:

// m == mat.length
// n == mat[i].length
// 1 <= m, n <= 104
// 1 <= m * n <= 104
// mat[i][j] is either 0 or 1.
// There is at least one 0 in mat.

class Solution {
    private static class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int[][] updateMatrix(int[][] mat) {
        Queue<Pair> queue = new LinkedList<>();

        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                if (mat[r][c] == 0) {
                    queue.offer(new Pair(r, c));
                } else {
                    mat[r][c] = -1;
                }
            }
        }

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int row = cur.r + dirs[i][0];
                int col = cur.c + dirs[i][1];

                if (row >= 0 && col >= 0 && row < mat.length && col < mat[0].length && mat[row][col] < 0) {
                    queue.offer(new Pair(row, col));
                    mat[row][col] = mat[cur.r][cur.c] + 1;
                }
            }
        }

        return mat;
    }
}