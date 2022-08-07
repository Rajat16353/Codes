// Given two numbers x and y, and a range [l, r] where 1 <= l, r <= 32. Find the set bits of y in range [l, r] and set these bits in x also.

// Example 1:

// Input: 
// X = 44, Y = 3 
// L = 1,  R = 5
// Output: 47
// Explaination: presenation of 44 and 3 are 
// 101100 and 11. So in the range 1 to 5 there 
// are two set bits. If those are set in 44 
// it will become 101111 = 47.

// Example 2:

// Input: 
// X = 16, Y = 2
// L = 1,  R = 3
// Output: 18
// Explaination: representation of 16 and 2 
// are 10000 and 10. If the mentioned rule is 
// followed then 16 will become 10010 = 18.

// Your Task:
// You do not need to read input or print anything. Your task is to complete the dunction setSetBit() which takes the number x, y, l and r as input parameters and return the modified value of x.


// Expected Time Complexity: O(r - l)
// Expected Auxiliary Space: O(1)


// Constraints:
// 1 ≤ x, y ≤ 109
// 1 ≤ l ≤ r ≤ 32

//{ Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            String line[] = in.readLine().trim().split("\\s+");
            int x = Integer.parseInt(line[0]);
            int y = Integer.parseInt(line[1]);
            int l = Integer.parseInt(line[2]);
            int r = Integer.parseInt(line[3]);
            
            Solution ob = new Solution();
            System.out.println(ob.setSetBit(x, y, l, r));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int setSetBit(int x, int y, int l, int r){
        // code here
        int temp = y;
        int count = 1;
        String num = "";
        
        while (count < l) {
            num = "0"+num;
            temp = temp >> 1;
            count++;
        }
        
        while(temp != 0 && count <= r) {
            count++;
            num = (temp & 1) + num;
            temp = temp >> 1;
        }
        
        return x | Integer.parseInt(num, 2);
    }
}