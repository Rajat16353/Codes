// Given an m x n matrix, return all elements of the matrix in spiral order.

 

// Example 1:


// Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
// Output: [1,2,3,6,9,8,7,4,5]
// Example 2:


// Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
// Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 

// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 10
// -100 <= matrix[i][j] <= 100

// Brute force
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiralPrint = new ArrayList<>();
        int r = 0, c = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        while(spiralPrint.size() != row * col) {
            while(c < col && matrix[r][c] != 101) {
                spiralPrint.add(matrix[r][c]);
                matrix[r][c] = 101;
                c++;
            }
            c--;
            r++;
            while(r < row && matrix[r][c] != 101) {
                spiralPrint.add(matrix[r][c]);
                matrix[r][c] = 101;
                r++;
            }
            r--;
            c--;
            while(c > -1 && matrix[r][c] != 101) {
                spiralPrint.add(matrix[r][c]);
                matrix[r][c] = 101;
                c--;
            }
            c++;
            r--;
            while(r > -1 && matrix[r][c] != 101) {
                spiralPrint.add(matrix[r][c]);
                matrix[r][c] = 101;
                r--;
            }
            r++;
            c++;
        }
        return spiralPrint;
    }
}

// Creating subProblems
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> spiral = new ArrayList<>();
        int top = 0, bottom = matrix.length;
        int left = 0, right = matrix[0].length;
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