// Given a triangle array, return the minimum path sum from top to bottom.

// For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

// Example 1:

// Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
// Output: 11
// Explanation: The triangle looks like:
//    2
//   3 4
//  6 5 7
// 4 1 8 3
// The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
// Example 2:

// Input: triangle = [[-10]]
// Output: -10
 

// Constraints:

// 1 <= triangle.length <= 200
// triangle[0].length == 1
// triangle[i].length == triangle[i - 1].length + 1
// -104 <= triangle[i][j] <= 104
 

// Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?

// Recursion with memoisation
class Solution {
    private int minimumTotal(List<List<Integer>> triangle, int i, int j, int[][] dp) {
        if (i == triangle.size() - 1) {
            return triangle.get(i).get(j);
        }

        if (dp[i][j] != 100000) return dp[i][j];

        int down = triangle.get(i).get(j) + minimumTotal(triangle, i + 1, j, dp);
        int diagonal = triangle.get(i).get(j) + minimumTotal(triangle, i + 1, j + 1, dp);

        return dp[i][j] = Math.min(down, diagonal);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size() + 1];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 100000));
        return minimumTotal(triangle, 0, 0, dp);
    }
}

// Tabulation
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        for(int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n-1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j);
                int diagonal = triangle.get(i).get(j);

                down += dp[i + 1][j];
                if (j + 1 < n) diagonal += dp[i + 1][j + 1];

                dp[i][j] = Math.min(down, diagonal);
            }
        }

        return dp[0][0];
    }
}

// Memory optimised tabulation
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        for(int j = 0; j < n; j++) {
            dp[j] = triangle.get(n-1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            int[] cur = new int[n];
            for (int j = i; j >= 0; j--) {
                int down = triangle.get(i).get(j) + dp[j];
                int diagonal = triangle.get(i).get(j) + dp[j + 1];

                cur[j] = Math.min(down, diagonal);
            }
            dp = cur;
        }

        return dp[0];
    }
}