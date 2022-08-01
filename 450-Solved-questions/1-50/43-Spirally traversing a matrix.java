// Given a matrix of size r*c. Traverse the matrix in spiral form.

// Example 1:

// Input:
// r = 4, c = 4
// matrix[][] = {{1, 2, 3, 4},
//            {5, 6, 7, 8},
//            {9, 10, 11, 12},
//            {13, 14, 15,16}}
// Output: 
// 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10
// Explanation:

// Example 2:

// Input:
// r = 3, c = 4  
// matrix[][] = {{1, 2, 3, 4},
//            {5, 6, 7, 8},
//            {9, 10, 11, 12}}
// Output: 
// 1 2 3 4 8 12 11 10 9 5 6 7
// Explanation:
// Applying same technique as shown above, 
// output for the 2nd testcase will be 
// 1 2 3 4 8 12 11 10 9 5 6 7.

// Your Task:
// You dont need to read input or print anything. Complete the function spirallyTraverse() that takes matrix, r and c as input parameters and returns a list of integers denoting the spiral traversal of matrix. 

// Expected Time Complexity: O(r*c)
// Expected Auxiliary Space: O(r*c), for returning the answer only.

// Constraints:
// 1 <= r, c <= 100
// 0 <= matrixi <= 100

//{ Driver Code Starts
import java.io.*;
import java.util.*;
class GFG
{
    public static void main(String args[])throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            int r = sc.nextInt();
            int c = sc.nextInt();
            
            int matrix[][] = new int[r][c];
            
            for(int i = 0; i < r; i++)
            {
                for(int j = 0; j < c; j++)
                 matrix[i][j] = sc.nextInt();
            }
            Solution ob = new Solution();
            ArrayList<Integer> ans = ob.spirallyTraverse(matrix, r, c);
            for (Integer val: ans) 
                System.out.print(val+" "); 
            System.out.println();
        }
    }
}
// } Driver Code Ends


class Solution
{
    //Function to return a list of integers denoting spiral traversal of matrix.
    static ArrayList<Integer> spirallyTraverse(int matrix[][], int row, int col)
    {
        // code here
        ArrayList<Integer> spiral = new ArrayList<>();
        int top = 0, bottom = row;
        int left = 0, right = col;
        while(top < bottom && left < right) {
            for(int i = left; i < right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;
            for(int i = top; i < bottom; i++) {
                spiral.add(matrix[i][right-1]);
            }
            right--;
            if (!(top < bottom && left < right))
                break;
            
            for(int i = right-1; i >= left; i--) {
                spiral.add(matrix[bottom-1][i]);
            }
            bottom--;
            for(int i = bottom-1; i >= top; i--) {
                spiral.add(matrix[i][left]);
            }
            left++;
        }
        return spiral;
    }
}
