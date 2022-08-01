// Given an array of size N containing only 0s, 1s, and 2s; sort the array in ascending order.


// Example 1:

// Input: 
// N = 5
// arr[]= {0 2 1 2 0}
// Output:
// 0 0 1 2 2
// Explanation:
// 0s 1s and 2s are segregated 
// into ascending order.
// Example 2:

// Input: 
// N = 3
// arr[] = {0 1 0}
// Output:
// 0 0 1
// Explanation:
// 0s 1s and 2s are segregated 
// into ascending order.

// Your Task:
// You don't need to read input or print anything. Your task is to complete the function sort012() that takes an array arr and N as input parameters and sorts the array in-place.


// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(1)


// Constraints:
// 1 <= N <= 10^6
// 0 <= A[i] <= 2

// { Driver Code Starts
//Initial template for Java

import java.io.*;
import java.util.*;


 // } Driver Code Ends
//User function template for Java

class Solution
{
    public static void sort012(int a[], int n)
    {
        int[] count = new int[3];
        for(int num: a) {
            count[num] += 1;
        }
        
        for(int i=0; i<n; i++) {
            if(count[0] > 0){
                a[i] = 0;
                count[0] -= 1;
            }
            else if (count[1] > 0) {
                a[i] = 1;
                count[1] -= 1;
            } else {
                a[i] = 2;
                count[2] -= 1;
            }
        }
    }
}

// { Driver Code Starts.

class GFG {
    
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){
            int n = Integer.parseInt(br.readLine().trim());
            int arr[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(inputLine[i]);
            }
            Solution ob=new Solution();
            ob.sort012(arr, n);
            StringBuffer str = new StringBuffer();
            for(int i=0; i<n; i++){
                str.append(arr[i]+" ");
            }
            System.out.println(str);
        }
    }
}

  // } Driver Code Ends