// Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
 

// Example 1:


// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
// Output: true
// Example 2:


// Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
// Output: false
 

// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 100
// -104 <= matrix[i][j], target <= 104

class Solution {
    private Boolean binSearch(int[][] matrix, int target, int l, int r) {
        if (l > r) {
            return false;
        } else {
            int mid = (l+r)/2;
            int row = mid/matrix[0].length;
            int col = mid%matrix[0].length;
            int midVal = matrix[row][col];
            if (target == midVal) {
                return true;
            } else if (target > midVal) {
                return binSearch(matrix, target, mid+1, r);
            } else {
                return binSearch(matrix, target, l, mid-1);
            }       
        }
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        return binSearch(matrix, target, 0, matrix.length * matrix[0].length - 1);
    }
}