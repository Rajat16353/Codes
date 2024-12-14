// Given a boolean 2D matrix grid of size n * m. You have to find the number of distinct islands where a group of connected 1s (horizontally or vertically) forms an island. Two islands are considered to be distinct if and only if one island is not equal to another (not rotated or reflected).

// Example 1:

// Input:
// grid[][] = {{1, 1, 0, 0, 0},
//             {1, 1, 0, 0, 0},
//             {0, 0, 0, 1, 1},
//             {0, 0, 0, 1, 1}}
// Output:
// 1
// Explanation:
// grid[][] = {{1, 1, 0, 0, 0}, 
//             {1, 1, 0, 0, 0}, 
//             {0, 0, 0, 1, 1}, 
//             {0, 0, 0, 1, 1}}
// Same colored islands are equal.
// We have 2 equal islands, so we 
// have only 1 distinct island.

// Example 2:

// Input:
// grid[][] = {{1, 1, 0, 1, 1},
//             {1, 0, 0, 0, 0},
//             {0, 0, 0, 0, 1},
//             {1, 1, 0, 1, 1}}
// Output:
// 3
// Explanation:
// grid[][] = {{1, 1, 0, 1, 1}, 
//             {1, 0, 0, 0, 0}, 
//             {0, 0, 0, 0, 1}, 
//             {1, 1, 0, 1, 1}}
// Same colored islands are equal.
// We have 4 islands, but 2 of them
// are equal, So we have 3 distinct islands.

// Your Task:

// You don't need to read or print anything. Your task is to complete the function countDistinctIslands() which takes the grid as an input parameter and returns the total number of distinct islands.

// Expected Time Complexity: O(n * m)
// Expected Space Complexity: O(n * m)

// Constraints:
// 1 ≤ n, m ≤ 500
// grid[i][j] == 0 or grid[i][j] == 1

//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];

            for (int i = 0; i < n; i++) {

                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

            Solution ob = new Solution();
            int ans = ob.countDistinctIslands(grid);
            System.out.println(ans);
        
System.out.println("~");
}
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {

    int countDistinctIslands(int[][] grid) {
        Set<String> islands = new HashSet<>();
        
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    islands.add(dfs(grid, r, c, ""));
                }
            }
        }
        
        return islands.size();
    }
    
    private String dfs(int[][] grid, int r, int c, String islandShape) {
        grid[r][c] = 0;
        
        if (c > 0 && grid[r][c - 1] == 1) {
            islandShape += "l";
            islandShape = dfs(grid, r, c - 1, islandShape);
        }
        
        if (r > 0 && grid[r - 1][c] == 1) {
            islandShape += "u";
            islandShape = dfs(grid, r - 1, c, islandShape);
        }
        
        if (c < grid[0].length - 1 && grid[r][c + 1] == 1) {
            islandShape += "r";
            islandShape = dfs(grid, r, c + 1, islandShape);
        }
        
        if (r < grid.length - 1 && grid[r + 1][c] == 1) {
            islandShape += "d";
            islandShape = dfs(grid, r + 1, c, islandShape);
        }
        
        islandShape += "x";
        
        return islandShape;
    }
}
