// Given an m x n matrix, find all common elements present in all rows in O(mn) time and one traversal of matrix.

// Example: 

// Input:
// mat[4][5] = {{1, 2, 1, 4, 8},
//              {3, 7, 8, 5, 1},
//              {8, 7, 7, 3, 1},
//              {8, 1, 2, 7, 9},
//             };

// Output: 
// 1 8 or 8 1
// 8 and 1 are present in all rows.

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class Main {
    static int M = 4;
    static int N =5;
    
    public static void main(String[] args)
    {
        int mat[][] =
        {
            {1, 2, 1, 4, 8},
            {3, 7, 8, 5, 1},
            {8, 7, 7, 3, 1},
            {8, 1, 2, 7, 9},
        };

        printCommonElements(mat);
    }
    
    
 
    // prints common element in all rows of matrix
    static void printCommonElements(int mat[][])
    {
        Map<Integer, Integer> commonCount = new HashMap<>();
        for (int c = 0; c < N; c++) {
            commonCount.put(mat[0][c],1);
        }
        
        for (int r = 1; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (commonCount.containsKey(mat[r][c])) {
                    commonCount.put(mat[r][c], commonCount.get(mat[r][c])+1);
                }
            }
        }
        
        for (Map.Entry<Integer, Integer> entry: commonCount.entrySet()) {
            if (entry.getValue() >= M) {
                System.out.print(entry.getKey()+" ");
            }
        }
    }
}