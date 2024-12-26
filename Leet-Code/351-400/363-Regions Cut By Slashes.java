// An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

// Given the grid grid represented as a string array, return the number of regions.

// Note that backslash characters are escaped, so a '\' is represented as '\\'.

 

// Example 1:


// Input: grid = [" /","/ "]
// Output: 2
// Example 2:


// Input: grid = [" /","  "]
// Output: 1
// Example 3:


// Input: grid = ["/\\","\\/"]
// Output: 5
// Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
 

// Constraints:

// n == grid.length == grid[i].length
// 1 <= n <= 30
// grid[i][j] is either '/', '\', or ' '.

class Solution {
    int[] parent;
    int[] rank;
    int count;

    public int regionsBySlashes(String[] grid) {
        int n = grid.length + 1;
        parent = new int[n * n];
        rank = new int[n * n];
        count = 1;

        for (int r = 0; r < parent.length; r++) {
            parent[r] = r;
            rank[r] = 1;
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 || c == 0 || r == n - 1 || c == n - 1) {
                    int cellNo = r * n + c;
                    if (cellNo != 0) union(0, cellNo);
                }
            }
        }

        for (int r = 0; r < n - 1; r++) {
            char[] ch = grid[r].toCharArray();
            for (int c = 0; c < n - 1; c++) {
                if (ch[c] == '/') {
                    int xCellNo = (r + 1) * n + c;
                    int yCellNo = r * n + c + 1;
                    union(xCellNo, yCellNo);
                } else if (ch[c] == '\\') {
                    int xCellNo = r * n + c;
                    int yCellNo = (r + 1) * n + c + 1;
                    union(xCellNo, yCellNo);
                }
            }
        }

        return count;
    }

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int lx = find(x);
        int ly = find(y);

        if (lx != ly) {
            if (rank[lx] > rank[ly]) {
                parent[ly] = lx;
            } else if (rank[ly] > rank[lx]) {
                parent[lx] = ly;
            } else {
                parent[ly] = lx;
                rank[lx] += 1;
            }
        } else {
            count += 1;
        }
    }
}