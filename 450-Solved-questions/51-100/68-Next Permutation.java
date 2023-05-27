// Implement the next permutation, which rearranges the list of numbers into Lexicographically next greater permutation of list of numbers. If such arrangement is not possible, it must be rearranged to the lowest possible order i.e. sorted in an ascending order. You are given an list of numbers arr[ ] of size N.

// Example 1:

// Input: N = 6
// arr = {1, 2, 3, 6, 5, 4}
// Output: {1, 2, 4, 3, 5, 6}
// Explaination: The next permutation of the 
// given array is {1, 2, 4, 3, 5, 6}.
// Example 2:

// Input: N = 3
// arr = {3, 2, 1}
// Output: {1, 2, 3}
// Explaination: As arr[] is the last 
// permutation. So, the next permutation 
// is the lowest one.
// Your Task:
// You do not need to read input or print anything. Your task is to complete the function nextPermutation() which takes N and arr[ ] as input parameters and returns a list of numbers containing the next permutation.

// Expected Time Complexity: O(N)
// Expected Auxiliary Space: O(1)

// Constraints:
// 1 ≤ N ≤ 10000

//{ Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());
            String a[] = in.readLine().trim().split("\\s+");
            int arr[] = new int[N];
            for(int i = 0;i < N;i++)
                arr[i] = Integer.parseInt(a[i]);
            
            Solution ob = new Solution();
            List<Integer> ans = new ArrayList<Integer>();
            ans = ob.nextPermutation(N, arr);
            StringBuilder out = new StringBuilder();
            for(int i = 0;i < N;i++)
                out.append(ans.get(i) + " ");
            System.out.println(out);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution{
    private static List<Integer> reverse(int[] arr, int N) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = N-1; i >= 0; i--) {
            list.add(arr[i]);
        }
        return list;
    }
    
    static List<Integer> nextPermutation(int N, int arr[]){
        // code here
        int l = N-2;
        int r = N-1;
        
        while(l >= 0) {
            if (arr[l] < arr[r]) {
                break;
            }
            l--;
            r--;
        }
        
        if (l == -1) {
            return reverse(arr, N);
        }
        
        int min = r;
        
        for(int i = l+1; i < N; i++) {
            if (arr[i] < arr[min] && arr[i] > arr[l]) {
                min = i;
            }
        }
        
        int temp = arr[l];
        arr[l] = arr[min];
        arr[min] = temp;
        
        Arrays.sort(arr, l+1, N);
        List<Integer> list = new ArrayList<>();
        for(int num: arr) {
            list.add(num);
        }
        
        return list;
    }
}