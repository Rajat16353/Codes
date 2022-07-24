// Given an array arr[] of N non-negative integers representing the height of blocks. If width of each block is 1, compute how much water can be trapped between the blocks during the rainy season. 
 

// Example 1:

// Input:
// N = 6
// arr[] = {3,0,0,2,0,4}
// Output:
// 10
// Explanation: 

// Example 2:

// Input:
// N = 4
// arr[] = {7,4,0,9}
// Output:
// 10
// Explanation:
// Water trapped by above 
// block of height 4 is 3 units and above 
// block of height 0 is 7 units. So, the 
// total unit of water trapped is 10 units.
// Example 3:

// Input:
// N = 3
// arr[] = {6,9,9}
// Output:
// 0
// Explanation:
// No water will be trapped.

// Your Task:
// You don't need to read input or print anything. The task is to complete the function trappingWater() which takes arr[] and N as input parameters and returns the total amount of water that can be trapped.


// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(N)


// Constraints:
// 3 < N < 106
// 0 < Ai < 108

// { Driver Code Starts
import java.io.*;
import java.util.*;
import java.lang.*;


class Array {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
		while(t-->0){
		  
		    //size of array
		    int n = Integer.parseInt(br.readLine().trim());
		    int arr[] = new int[n];
		    String inputLine[] = br.readLine().trim().split(" ");
		    
		    //adding elements to the array
		    for(int i=0; i<n; i++){
		        arr[i] = Integer.parseInt(inputLine[i]);
		    }
		    
		    Solution obj = new Solution();
		    
		    //calling trappingWater() function
		    System.out.println(obj.trappingWater(arr, n));
		}
	}
}

// } Driver Code Ends


class Solution{
    
    // arr: input array
    // n: size of array
    // Function to find the trapped water between the blocks.
    static long trappingWater(int arr[], int n) { 
        // Your code here
        int maxL[] = new int[n];
        int maxR[] = new int[n];
        int minLR[] = new int[n];
        
        maxL[0] = 0;
        
        for(int i = 1; i< n; i++) {
            maxL[i] = Math.max(maxL[i-1], arr[i-1]);
        }
        
        maxR[n-1] = 0;
        for(int i = n-2; i >= 0; i--) {
            maxR[i] = Math.max(maxR[i+1], arr[i+1]);
        }
        
        for(int i = 0; i < n; i++) {
            minLR[i] = Math.min(maxL[i], maxR[i]);
        }
        
        long water = 0;
        for (int i = 0; i < n; i++) {
            if (minLR[i] - arr[i] > 0)
                water += minLR[i] - arr[i];
        }
        return water;
    } 
}

// O(1) space using two pointers
class Solution{
    
    // arr: input array
    // n: size of array
    // Function to find the trapped water between the blocks.
    static long trappingWater(int arr[], int n) { 
        // Your code here
        int l = 0, r = n-1;
        int maxL = arr[0];
        int maxR = arr[n-1];
        long water = 0;
        
        while(l < r) {
            if (maxL <= maxR) {
                water += maxL-arr[l] > 0 ? maxL-arr[l]: 0;
                l++;
                if (maxL < arr[l])
                    maxL = arr[l];
            } else {
                water += maxR-arr[r] > 0 ? maxR-arr[r]: 0;
                r--;
                if (maxR < arr[r])
                    maxR = arr[r];
            }
        }
        return water;
    } 
}