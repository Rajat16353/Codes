// Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

// Example 1:


// Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
// Output: 6
// Explanation: The maximal rectangle is shown in the above picture.
// Example 2:

// Input: matrix = [["0"]]
// Output: 0
// Example 3:

// Input: matrix = [["1"]]
// Output: 1
 

// Constraints:

// rows == matrix.length
// cols == matrix[i].length
// 1 <= row, cols <= 200
// matrix[i][j] is '0' or '1'.

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

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] M = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                M[i][j] = matrix[i][j] - '0';
            }
        }
        
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


//Optimised approach with clean code
class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> index = new Stack<>();
        int n = heights.length;
        
        int maxArea = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++) {
            int area = 0;
            while (!index.isEmpty() && heights[i] < heights[index.peek()]) {
                int val = heights[index.pop()];
                int lse = index.isEmpty() ? -1: index.peek();
                maxArea = Math.max(maxArea, val * (i - lse - 1));
            }
            index.push(i);
        }

        while(!index.isEmpty()) {
            int val = heights[index.pop()];
            int nse = n;
            int lse = index.isEmpty() ? -1: index.peek();
            maxArea = Math.max(maxArea, val * (nse - lse - 1));
        }

        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        int maxArea = Integer.MIN_VALUE;
        int[] heights = new int[matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == '0' ? 0: heights[j] + matrix[i][j] - '0';
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }
}