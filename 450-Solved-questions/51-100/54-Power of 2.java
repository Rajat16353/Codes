// Given a non-negative integer N. The task is to check if N is a power of 2. More formally, check if N can be expressed as 2x for some x.

// Example 1:

// Input: N = 1
// Output: YES
// Explanation:1 is equal to 2 
// raised to 0 (20 = 1).
// Example 2:

// Input: N = 98
// Output: NO
// Explanation: 98 cannot be obtained
// by any power of 2.

// Your Task:Your task is to complete the function isPowerofTwo() which takes n as a parameter and returns true or false by checking if the given number can be represented as a power of two or not.

// Expected Time Complexity:O(log N).
// Expected Auxiliary Space:O(1).

// Constraints:
// 0 ≤N ≤1018

//{ Driver Code Starts
// //Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Driver_class
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());//testcases
        
        while(t-- > 0)
        {
            //input a number n
            long n = Long.parseLong(read.readLine());
            
            
            // if n is less than equal to zero then 
            //it can't be a power of 2 so we print No
            if(new Solution().isPowerofTwo(n) == true)
              System.out.println("YES");
            else System.out.println("NO");
        }
    }
}

// } Driver Code Ends


//User function Template for Java
// right shfit solution
class Solution{
    
    // Function to check if given number n is a power of two.
    public static boolean isPowerofTwo(long n){
        
        // Your code here
        if (n == 0) {
            return false;
        }
        
        while (n > 1 && (n & 1) == 0) {
            n >>= 1;
        }
        
        if (n == 1) {
            return true;
        }
        return false;
    }
    
}