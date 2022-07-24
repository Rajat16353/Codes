// Given an integer N, find its factorial.

// Example 1:

// Input: N = 5
// Output: 120
// Explanation : 5! = 1*2*3*4*5 = 120
// Example 2:

// Input: N = 10
// Output: 3628800
// Explanation :
// 10! = 1*2*3*4*5*6*7*8*9*10 = 3628800

// Your Task:
// You don't need to read input or print anything. Complete the function factorial() that takes integer N as input parameter and returns a list of integers denoting the digits that make up the factorial of N.


// Expected Time Complexity : O(N2)
// Expected Auxilliary Space : O(1)


// Constraints:
// 1 ≤ N ≤ 1000

// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GfG
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int N = sc.nextInt();
            Solution ob = new Solution();
            ArrayList<Integer> ans = ob.factorial(N);
            for (Integer val: ans) 
                System.out.print(val); 
            System.out.println();
        }   
    }
}// } Driver Code Ends


//User function Template for Java

class Solution {
    static ArrayList<Integer> factorial(int N){
        //code here
        ArrayList<Integer> fact = new ArrayList<>();
        fact.add(1);
        int n = 2;
        while(n <= N) {
            int i = 0, carry = 0;
            while(i < fact.size()) {
                // int dig = fact.get(i);
                int data = fact.get(i) * n+carry;
                fact.set(i, data % 10);
                carry = data / 10;
                i++;
            }
            while (carry != 0) {
                fact.add(carry % 10);
                carry /= 10;
            }
            // System.out.println(Arrays.toString(fact.toArray()));

            n++;
        }
        Collections.reverse(fact);
        return fact;
    }
}