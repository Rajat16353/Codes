// Given a number N having only one ‘1’ and all other ’0’s in its binary representation, find position of the only set bit. If there are 0 or more than 1 set bit the answer should be -1. Position of  set bit '1' should be counted starting with 1 from LSB side in binary representation of the number.

// Example 1:

// Input:
// N = 2
// Output:
// 2
// Explanation:
// 2 is represented as "10" in Binary.
// As we see there's only one set bit
// and it's in Position 2 and thus the
// Output 2.
// Example 2:

// Input:
// N = 5
// Output:
// -1
// Explanation:
// 5 is represented as "101" in Binary.
// As we see there's two set bits
// and thus the Output -1.
// Your Task:
// You don't need to read input or print anything. Your task is to complete the function findPosition() which takes an integer N as input and returns the answer.

// Expected Time Complexity: O(log(N))
// Expected Auxiliary Space: O(1)

// Constraints:
// 0 <= N <= 108

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int N = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            System.out.println(ob.findPosition(N));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution {
    static Boolean isPowerOf2(int n) {
        if (n == 0) {
            return false;
        }
        return (n & (n-1)) == 0;
    }
    static int findPosition(int N) {
        // code here
        if (!isPowerOf2(N)) {
            return -1;
        }
        
        int shift = 0;
        while(N != 0) {
            shift++;
            N = N >> 1;
        }
        return shift;
    }
};