// Given two integers dividend and divisor. Find the quotient after dividing dividend by divisor without using multiplication, division and mod operator.

// Example 1:

// Input:
// a = 10, b= 3
// Output: 3
// Exaplanation:
// 10/3 gives quotient as 3 
// and remainder as 1.
// Example 2:

// Input:
// a = 43, b = -8
// Output: -5
// Explanation:
// 43/-8 gives quotient as -5 and 
// remainder as 3.
// Your task:
// You don't have to read input or print anything. Your task is to complete the function divide() which takes two integers a and b as input and returns the quotient after dividing a by b.
 
// Expected Time Complexity: O(1)
// Expected Auxiliary Space: O(1)
 
// Constraints :
// -10^9 <= a,b <= 10^9

//{ Driver Code Starts
//Initial Template for Java


import java.io.*;
import java.util.*;

class GfG {
    

	public static void main (String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int t=Integer.parseInt(br.readLine());
    
	        while(t > 0){
	            String S[] = br.readLine().split(" ");
            
            long a = Long.parseLong(S[0]);
            
            long b = Long.parseLong(S[1]);
	       
	    	Solution ob = new Solution();
           
            System.out.println(ob.divide(a,b));
            t--;
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution
{
    public static long divide(long a, long b)
    {
        //code here
        Boolean neg = false;
        int quotient = 0;
        if ((a < 0) ^ (b < 0)) {
            neg = true;
        }
        
        a = Math.abs(a);
        b = Math.abs(b);
        
        for (int i = 31; i >= 0; i--) {
            if ((b << i) <= a) {
                a -= b << i;
                quotient += 1L << i;
            }
        }
        
        if (neg) {
            return -1 * quotient;
        }
        return quotient;
    }
}