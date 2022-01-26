// Given an array of N integers arr[] where each element represents the max number of steps that can be made forward from that element. Find the minimum number of jumps to reach the end of the array (starting from the first element). If an element is 0, then you cannot move through that element.
// Note: Return -1 if you can't reach the end of the array.


// Example 1:

// Input:
// N = 11 
// arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9} 
// Output: 3 
// Explanation: 
// First jump from 1st element to 2nd 
// element with value 3. Now, from here 
// we jump to 5th element with value 9, 
// and from here we will jump to last. 
// Example 2:

// Input :
// N = 6
// arr = {1, 4, 3, 2, 6, 7}
// Output: 2 
// Explanation: 
// First we jump from the 1st to 2nd element 
// and then jump to the last element.

// Your task:
// You don't need to read input or print anything. Your task is to complete function minJumps() which takes the array arr and it's size N as input parameters and returns the minimum number of jumps. If not possible returns -1.


// Expected Time Complexity: O(N)
// Expected Space Complexity: O(1)


// Constraints:
// 1 ≤ N ≤ 107
// 0 ≤ arri ≤ 107

// { Driver Code Starts
import java.lang.*;
import java.io.*;
import java.util.*;
class GFG
 {
	public static void main (String[] args) throws IOException
	 {
	 
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        int t = Integer.parseInt(br.readLine()); 

        while(t-- > 0){
            int size = Integer.parseInt(br.readLine());
            String[] arrStr = ((String)br.readLine()).split("\\s+");
            int[] arr= new int[size];
            for(int i = 0;i<size;i++){
                arr[i] = Integer.parseInt(arrStr[i]);
            }
            System.out.println(new Solution().minJumps(arr));
        }
	 }
	 
}
// } Driver Code Ends


class Solution{
    static int minJumps(int[] nums){
        // your code here
        if (nums.length == 1) return 0;
        int target = nums.length - 1;
        int j = target - 1;
        
        while(j >= 0) {
            int dist = target - j;
            if (nums[j] >= dist){
                target = j;
                j--;
            } else {
                j--;
            }
        }
        if (target != 0) {
            return -1;
        }
        
        int jumps = 0;
        int l = 0, r = 0;
        while(r < nums.length-1) {
            int farthest = 0;
            for (int i = l; i < r+1; i++) {
                farthest = Math.max(farthest, i+nums[i]);
            }
            l = r+1;
            r = farthest;
            jumps++;
        }
        
        return jumps;
    }
}