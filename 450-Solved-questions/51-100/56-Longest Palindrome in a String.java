// Given a string S, find the longest palindromic substring in S. Substring of string S: S[ i . . . . j ] where 0 ≤ i ≤ j < len(S). Palindrome string: A string which reads the same backwards. More formally, S is palindrome if reverse(S) = S. Incase of conflict, return the substring which occurs first ( with the least starting index).


// Example 1:

// Input:
// S = "aaaabbaa"
// Output: aabbaa
// Explanation: The longest Palindromic
// substring is "aabbaa".
// Example 2:

// Input: 
// S = "abc"
// Output: a
// Explanation: "a", "b" and "c" are the 
// longest palindromes with same length.
// The result is the one with the least
// starting index.

// Your Task:
// You don't need to read input or print anything. Your task is to complete the function longestPalin() which takes the string S as input and returns the longest palindromic substring of S.


// Expected Time Complexity: O(|S|2).
// Expected Auxiliary Space: O(1).


// Constraints:
// 1 ≤ |S| ≤ 103

//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String S = read.readLine();
            
            Solution ob = new Solution();
            System.out.println(ob.longestPalin(S));
        }
    }
}

// } Driver Code Ends


//User function Template for Java

class Solution{
    static String longestPalin(String S){
        // code here
        String res = "";
        int resLen = 0;
        for(int i=0; i<S.length(); i++){
            int l = i,r = i;
            while(l>=0 && r < S.length() && S.charAt(l) == S.charAt(r)){
                if ((r-l+1) > resLen){
                    res = S.substring(l,r+1);
                    resLen = r-l+1;
                }
                l -= 1;
                r += 1;
            }
            
            //even length
            l = i;
            r = i+1;
            while(l>=0 && r < S.length() && S.charAt(l) == S.charAt(r)){
                if ((r-l+1) > resLen){
                    res = S.substring(l,r+1);
                    resLen = r-l+1;
                }
                l -= 1;
                r += 1;
            }
        }
        return res;
    }
}