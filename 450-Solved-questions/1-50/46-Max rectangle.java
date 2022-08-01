// Given a binary matrix M of size n X m. Find the maximum area of a rectangle formed only of 1s in the given matrix.

// Example 1:

// Input:
// n = 4, m = 4
// M[][] = {{0 1 1 0},
//          {1 1 1 1},
//          {1 1 1 1},
//          {1 1 0 0}}
// Output: 8
// Explanation: For the above test case the
// matrix will look like
// 0 1 1 0
// 1 1 1 1
// 1 1 1 1
// 1 1 0 0
// the max size rectangle is 
// 1 1 1 1
// 1 1 1 1
// and area is 4 *2 = 8.
// Your Task: 
// Your task is to complete the function maxArea which returns the maximum size rectangle area in a binary-sub-matrix with all 1’s. The function takes 3 arguments the first argument is the Matrix M[ ] [ ] and the next two are two integers n and m which denotes the size of the matrix M. 

// Expected Time Complexity : O(n*m)
// Expected Auixiliary Space : O(m)

// Constraints:
// 1<=n,m<=1000
// 0<=M[][]<=1

// Note:The Input/Ouput format and Example given are used for system's internal purpose, and should be used by a user for Expected Output only. As it is a function problem, hence a user should not read any input from stdin/console. The task is to complete the function specified, and not to write the full code.

//{ Driver Code Starts
import java.util.*;

class FindMinCost
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t > 0)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int arr[][] = new int[n][m];
			for(int i=0; i<n; i++)
			{
				for(int j=0; j<m; j++ )
				{
					arr[i][j] = sc.nextInt();
				}
			}
			System.out.println(new Solution().maxArea(arr, n, m));
		t--;
		}
	}
}
// } Driver Code Ends

class Tuple {
    int key;
    int value;
    
    public Tuple(int key, int value) {
        this.key = key;
        this.value = value;
    }
    
    public int getKey() {
        return key;
    }
    
    public int getValue() {
        return value;
    }
}

/*Complete the function given below*/
class Solution {
    public int maxArea(int M[][], int n, int m) {
        // add code here.
        int r = 0;
        int maxArea = 0;
        while(r < n) {
            int area = 0;
            Stack<Tuple> stack = new Stack<>();
            for (int c = 0; c < m;c++) {
                Tuple pair = null;
                if (M[r][c] != 0 && r != 0) {
                    M[r][c] += M[r-1][c];
                }
                while (!stack.empty() && stack.peek().getValue() > M[r][c]) {
                    pair = stack.pop();
                    area = Math.max(area, pair.getValue() * (c - pair.getKey()));
                }
                stack.push(new Tuple(pair != null && c > 0 && M[r][c-1] > M[r][c] ? pair.getKey(): c, M[r][c]));
            }
            while(!stack.empty()) {
                Tuple pair = stack.pop();
                area = Math.max(area, pair.getValue() * (m - pair.getKey()));
            }
            // System.out.println(area);
            maxArea = Math.max(maxArea, area);
            r++;
        }
        return maxArea;
    }
}