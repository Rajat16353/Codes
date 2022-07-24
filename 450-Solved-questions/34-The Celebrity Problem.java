// A celebrity is a person who is known to all but does not know anyone at a party. If you go to a party of N people, find if there is a celebrity in the party or not.
// A square NxN matrix M[][] is used to represent people at the party such that if an element of row i and column j  is set to 1 it means ith person knows jth person. Here M[i][i] will always be 0.
// Note: Follow 0 based indexing.
 

// Example 1:

// Input:
// N = 3
// M[][] = {{0 1 0},
//          {0 0 0}, 
//          {0 1 0}}
// Output: 1
// Explanation: 0th and 2nd person both
// know 1. Therefore, 1 is the celebrity. 

// Example 2:

// Input:
// N = 2
// M[][] = {{0 1},
//          {1 0}}
// Output: -1
// Explanation: The two people at the party both
// know each other. None of them is a celebrity.

// Your Task:
// You don't need to read input or print anything. Complete the function celebrity() which takes the matrix M and its size N as input parameters and returns the index of the celebrity. If no such celebrity is present, return -1.


// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(1)


// Constraints:
// 2 <= N <= 3000
// 0 <= M[][] <= 1

// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*; 

class GFG{
    public static void main(String args[]) throws IOException { 
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0)
        {
            int N = sc.nextInt();
            int M[][] = new int[N][N];
            for(int i=0; i<N; i++)
            {
                for(int j=0; j<N; j++)
                {
                    M[i][j] = sc.nextInt();
                }
            }
            System.out.println(new Solution().celebrity(M,N));
            t--;
        }
    } 
} // } Driver Code Ends


//User function Template for Java

// O(n^2)
class Solution
{ 
    private int knows(int M[][], int i, int j) {
        return M[i][j];
    }
    
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];
        
    	// code here
    	for (int i = 0; i < n; i++) {
    	    for (int j = 0; j < n; j++) {
    	        int degree = knows(M, i, j);
    	        
    	        inDegree[j] += degree;
    	        outDegree[i] += degree;
    	    }
    	}
    	
    	for (int i = 0; i<n; i++) {
    	    if (outDegree[i] == 0 && inDegree[i] == n-1) {
    	        return i;
    	    }
    	}
    	return -1;
    }
}

// o(n)
class Solution
{ 
    private int knows(int M[][], int i, int j) {
        return M[i][j];
    }
    
    private int findPotentialCelebrity(int M[][], int n) {
        if (n == 0) {
            return -1;
        }
        
        int id = findPotentialCelebrity(M, n-1);
        
        if(id == -1) {
            return n-1;
        }
        
        else if (knows(M, id, n-1) == 1) {
            return n-1;
        }
        
        else if(knows(M, n-1, id) == 1) {
            return id;
        }
        
        return -1;
    }
    
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
        int id = findPotentialCelebrity(M, n);
        if (id == -1) {
            return id;
        }
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];
        
        
        for (int i = 0; i<n; i++) {
            int deg = knows(M, i, id);
            
            inDegree[id] += deg;
            outDegree[i] += deg;
        }
        
        for (int i = 0; i<n; i++) {
            if (inDegree[i] == n-1 && outDegree[i] == 0) {
                return id;
            }
        }
        
        return -1;
    }
}

// O(n) two pointer approach
class Solution
{ 
    private int knows(int M[][], int i, int j) {
        return M[i][j];
    }
    
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
        int inDegree = 0;
        int outDegree = 0;
        
        int l = 0, r = n-1;
        
        while(l<r) {
            if (knows(M, l, r) == 1 && knows(M, r, l) == 1) {
                l++;
                r--;
            } else if (knows(M, l, r) == 1) {
                l++;
            } else if (knows(M, r, l) == 1) {
                r--;
            } else {
                l++;
                r--;
            }
        }
        
        
        for (int i = 0; i<n; i++) {
            int ideg = knows(M, i, r);
            int odeg = knows(M, r, i);
            
            inDegree += ideg;
            inDegree += odeg;
        }
        
        if (inDegree == n-1 && outDegree == 0) {
            return r;
        }
        
        return -1;
    }
}