// Given an integer numRows, return the first numRows of Pascal's triangle.

// In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

// Example 1:

// Input: numRows = 5
// Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
// Example 2:

// Input: numRows = 1
// Output: [[1]]
 

// Constraints:

// 1 <= numRows <= 30

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascalsTriangle = new ArrayList<>();
        pascalsTriangle.add(new ArrayList<>(Arrays.asList(1)));

        for(int i = 2; i < numRows + 1; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    row.add(1);
                } else {
                    row.add(pascalsTriangle.get(i - 2).get(j - 1) + pascalsTriangle.get(i - 2).get(j));
                }
            }
            pascalsTriangle.add(row);
        }

        return pascalsTriangle;
    }
}