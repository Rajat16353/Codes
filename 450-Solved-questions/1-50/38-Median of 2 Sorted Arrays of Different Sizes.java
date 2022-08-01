// Given two sorted arrays array1 and array2 of size m and n respectively. Find the median of the two sorted arrays.

// Example 1:

// Input:
// m = 3, n = 4
// array1[] = {1,5,9}
// array2[] = {2,3,6,7}
// Output: 5
// Explanation: The middle element for
// {1,2,3,5,6,7,9} is 5
// Example 2:

// Input:
// m = 2, n = 4
// array1[] = {4,6}
// array2[] = {1,2,3,5}
// Output: 3.5
// Your Task:
// The task is to complete the function MedianOfArrays() that takes array1 and array2 as input and returns their median. 

// Can you solve the problem in expected time complexity?

// Expected Time Complexity: O(min(log n, log m)).
// Expected Auxiliary Space: O((n+m)/2).

// Constraints: 
// 0 ≤ m,n ≤ 104
// 1 ≤ array1[i], array2[i] ≤ 105

//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class Driver
{
    public static void main(String args[]) 
	{ 
	    Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int []a = new int[n];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();
            
            int  m= sc.nextInt();
            int []b = new int[m];
            for (int i = 0; i < m; i++) b[i] = sc.nextInt();
            
            double res = new GFG().medianOfArrays(n, m, a, b);
            
            if (res == (int)res) System.out.println ((int)res);
            else System.out.println (res);
        }
    		
	} 
}
// } Driver Code Ends


//User function Template for Java

class GFG 
{ 
    static double medianOfArrays(int n, int m, int a[], int b[]) 
    {
        int totalLen = (a.length + b.length);
        int half = totalLen / 2;
        if (a.length > b.length) {
            int[] temp = new int[b.length];
            temp = b;
            b = a;
            a = temp;
        }   
        int l = 0, r = a.length-1;
        while(true) {
            int i = r >= 0 ? (l + r)/ 2 : (l + r)/ 2 - 1;
            int j = half - i - 2;
            
            double aLeft = i >= 0 ? a[i] : Double.NEGATIVE_INFINITY;
            double aRight = (i+1) < a.length ? a[i+1] : Double.POSITIVE_INFINITY;
            double bLeft = j >= 0 ? b[j] : Double.NEGATIVE_INFINITY;
            double bRight = (j+1) < b.length ? b[j+1] : Double.POSITIVE_INFINITY;
            
            if (aLeft <= bRight && bLeft <= aRight) {
                if (totalLen % 2 != 0)
                    return Math.min(aRight, bRight);
                else
                    return (Math.max(aLeft, bLeft) + Math.min(aRight, bRight)) / 2;
            }
            else if (aLeft > bRight)
                r = i - 1;
            else
                l = i + 1;
        }
    }
}