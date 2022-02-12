// Given an unsorted array Arr of N positive and negative numbers. Your task is to create an array of alternate positive and negative numbers without changing the relative order of positive and negative numbers.
// Note: Array should start with positive number.
 

// Example 1:

// Input: 
// N = 9
// Arr[] = {9, 4, -2, -1, 5, 0, -5, -3, 2}
// Output:
// 9 -2 4 -1 5 -5 0 -3 2
// Example 2:

// Input: 
// N = 10
// Arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
// Output:
// 5 -5 2 -2 4 -8 7 1 8 0 


// Your Task:  
// You don't need to read input or print anything. Your task is to complete the function rearrange() which takes the array of integers arr[] and n as parameters. You need to modify the array itself.

// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(N)
 

// Constraints:
// 1 ≤ N ≤ 107
// -106 ≤ Arr[i] ≤ 107

// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            new Solution().rearrange(arr, n);
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends


//User function Template for Java

// O(N) space
class Solution {
    void rearrange(int arr[], int n) {
        ArrayList<Integer> pos = new ArrayList<>();
	    ArrayList<Integer> neg = new ArrayList<>();
	    int k = 0, j = 0;
	    
	    for(int i = 0; i < n; i++) {
	        if (arr[i] >= 0)
	            pos.add(arr[i]);
	        else
	            neg.add(arr[i]);
	    }
	    
	    int i = 0;
	    while(k < pos.size() && j < neg.size()) {
	        if (i % 2 == 0) {
	            arr[i] = pos.get(k++);
	        } else {
	            arr[i] = neg.get(j++);
	        }
	        i++;
	    }
	    
	    while(k < pos.size()) {
	        arr[i++] = pos.get(k++);
	    }
	    
	    while(j < neg.size()) {
	        arr[i++] = neg.get(j++);
	    }
    }
}

// O(1) space maintaining order
class Solution {
    private void rotateRight(int arr[], int from, int to) {
        int end = arr[to];
        for(int k = to; k > from; k--) {
            arr[k] = arr[k-1];
        }
        arr[from] = end;
    }
    
    void rearrange(int arr[], int n) {
        int i = 0, j = -1;
        while(i < n) {
            if (i % 2 == 0) {
                if (arr[i] < 0) {
                    j = i;
                    while(arr[i] < 0) {
                        i++;
                        if (i == n)
                            return;
                    }
                    rotateRight(arr, j, i);
                    i = j + 1;
                }
            } else {
                if (arr[i] >= 0) {
                    j = i;
                    while(arr[i] >= 0) {
                        i++;
                        if (i == n) {
                            return;
                        }
                    }
                    rotateRight(arr, j, i);
                    i = j+1;
                }
            }
            i++;
        }
    }
}