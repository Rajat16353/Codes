// You are given a number N. Find the total count of set bits for all numbers from 1 to N(both inclusive).

// Example 1:

// Input: N = 4
// Output: 5
// Explanation:
// For numbers from 1 to 4.
// For 1: 0 0 1 = 1 set bits
// For 2: 0 1 0 = 1 set bits
// For 3: 0 1 1 = 2 set bits
// For 4: 1 0 0 = 1 set bits
// Therefore, the total set bits is 5.
// Example 2:

// Input: N = 17
// Output: 35
// Explanation: From numbers 1 to 17(both inclusive), 
// the total number of set bits is 35.

// Your Task: The task is to complete the function countSetBits() that takes n as a parameter and returns the count of all bits.

// Expected Time Complexity: O(log N).
// Expected Auxiliary Space: O(1).

//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.BigInteger;


// } Driver Code Ends
//User function Template for Java

class Solution{
    private static int largestPowerOf2InRange(int n) {
        int x = 0;
        while((1 << x) <= n) {
            // System.out.println(1 << x);
            x++;
        }
        return x - 1;
    }
    
    //Function to return sum of count of set bits in the integers from 1 to n.
    public static int countSetBits(int n){
    
        // Your code here
        int count = 0;
        while(n > 0) {
            int p = largestPowerOf2InRange(n);
            count = count + (int)Math.pow(2, p-1)*p + n - (int)Math.pow(2, p)+1;
            n = n - (int)Math.pow(2, p);
            // System.out.println(n + " " + p);
        }
        return count;
    }
}

//{ Driver Code Starts.

// Driver code
class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();//testcases
		int x, n;
		while(t-->0) {
	        n = sc.nextInt();//input n

		    Solution obj = new Solution();

		    System.out.println(obj.countSetBits(n));//calling countSetBits() method
		}
	}
}

// } Driver Code Ends