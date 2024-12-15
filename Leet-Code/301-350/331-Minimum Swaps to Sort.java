// Given an array arr[] of distinct elements. Find the minimum number of swaps required to sort the array in strictly increasing order.

// Examples:

// Input: arr[] = [2, 8, 5, 4]
// Output: 1
// Explanation: swap 8 with 4.
// Input: arr[] = [10, 19, 6, 3, 5]
// Output: 2
// Explanation: swap 10 with 3 and swap 19 with 5.
// Input: arr[] = [1, 3, 4, 5, 6]
// Output: 0
// Explanation: Already sorted array ,so no swaps required.
// Constraints:
// 1 ≤ arr.size() ≤ 106
// 1 ≤ arr[i] ≤ 109

//{ Driver Code Starts
// Initial Template for Java
import java.io.*;
import java.lang.*;
import java.util.*;


// } Driver Code Ends


class Solution {
    // Function to find the minimum number of swaps required to sort the array.
    public int minSwaps(int arr[]) {
        Pair[] arr1 = new Pair[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = new Pair(arr[i], i);
        }
        
        Arrays.sort(arr1);
        boolean[] vis = new boolean[arr.length];
        int ans = 0;
        
        for (int i = 0; i < arr1.length; i++) {
            if (vis[i] || arr1[i].idx == i) {
                continue;
            }
            
            int cycleLength = 0;
            int j = i;
            while (!vis[j]) {
                vis[j] = true;
                cycleLength += 1;
                j = arr1[j].idx;
            }
            
            ans += cycleLength - 1;
        }
        
        return ans;
    }
    
    class Pair implements Comparable<Pair> {
        int val;
        int idx;
        
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Pair a) {
            return Integer.compare(this.val, a.val);
        }
    }
}

//{ Driver Code Starts.

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            // int k = Integer.parseInt(br.readLine());
            String line = br.readLine();
            String[] tokens = line.split(" ");

            // Create an ArrayList to store the integers
            ArrayList<Integer> array = new ArrayList<>();

            // Parse the tokens into integers and add to the array
            for (String token : tokens) {
                array.add(Integer.parseInt(token));
            }

            int[] arr = new int[array.size()];
            int idx = 0;
            for (int i : array) arr[idx++] = i;
            Solution obj = new Solution();
            int ans = obj.minSwaps(arr);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends