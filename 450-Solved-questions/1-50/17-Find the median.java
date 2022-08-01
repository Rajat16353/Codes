// Given an array arr[] of N integers, calculate the median
 

// Example 1:

// Input: N = 5
// arr[] = 90 100 78 89 67
// Output: 89
// Explanation: After sorting the array 
// middle element is the median 

// Example 2:

// Input: N = 4
// arr[] = 56 67 30 79
// Output: 61
// Explanation: In case of even number of 
// elements, average of two middle elements 
// is the median.

 

// Your Task:
// You don't need to read or print anything. Your task is to complete the function find_median() which takes the array as input parameter and returns the floor value of the median.
 

// Expected Time Complexity: O(n * log(n))
// Expected Space Complexity: O(1)
 

// Constraints:
// 1 <= Length of Array <= 100
// 1 <= Elements of Array <= 100

// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            int n = Integer.parseInt(br.readLine().trim());
            String s = br.readLine();
            String[] S = s.split(" ");
            int[] v  = new int[n];
            for(int  i = 0; i < n; i++)
                v[i] = Integer.parseInt(S[i]);
            Solution ob = new Solution();
            int ans = ob.find_median(v);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution
{
    public int find_median(int[] v)
    {
        // Code here
        Arrays.sort(v);
        int l = v.length;
        if (l % 2 == 0)
            return (v[(l/2)-1] + v[l/2])/2;
        else
            return v[l/2];
    }
}