// Given an n x n matrix mat[n][n] of integers, find the maximum value of mat(c, d) – mat(a, b) over all choices of indexes such that both c > a and d > b.

// Example: 

// Input:
// mat[N][N] = {{ 1, 2, -1, -4, -20 },
//              { -8, -3, 4, 2, 1 }, 
//              { 3, 8, 6, 1, 3 },
//              { -4, -1, 1, 7, -6 },
//              { 0, -4, 10, -5, 1 }};
// Output: 18
// The maximum value is 18 as mat[4][2] 
// - mat[1][0] = 18 has maximum difference. 
// The program should do only ONE traversal of the matrix. i.e. expected time complexity is O(n2)

import java.util.* ;
import java.io.*; 
public class Solution {
    public static int findMaxValue(int mat[][], int n) {
        // Write your code here.
        
        int[] maxRow = new int[n];
        int maxValue = Integer.MIN_VALUE;

        maxRow[n-1] = mat[n-1][n-1];

        for (int i = n-2; i >= 0; i--) {
            maxRow[i] = Math.max(maxRow[i+1], mat[n-1][i]);
        }

        for (int i = n-2; i >= 0; i--) {
            int[] tempRow = new int[n];
            tempRow[n-1] = Math.max(maxRow[n-1], mat[i][n-1]);
            for (int j = n-2; j >= 0; j--) {
                maxValue = Math.max(maxValue, maxRow[j+1] - mat[i][j]);
                tempRow[j] = Math.max(maxRow[j], Math.max(tempRow[j+1], mat[i][j]));
            }
            maxRow = tempRow;
        }

        return maxValue;
    }
}
