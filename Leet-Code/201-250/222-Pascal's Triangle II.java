// Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

// In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

// Example 1:

// Input: rowIndex = 3
// Output: [1,3,3,1]
// Example 2:

// Input: rowIndex = 0
// Output: [1]
// Example 3:

// Input: rowIndex = 1
// Output: [1,1]
 

// Constraints:

// 0 <= rowIndex <= 33
 

// Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> pascalsTriangle = new ArrayList<>();
        pascalsTriangle.add(new ArrayList<>(Arrays.asList(1)));

        for(int i = 2; i <= rowIndex + 1; i++) {
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

        return pascalsTriangle.get(rowIndex);
    }
}

// Using recursion
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        if(rowIndex == 0){
            row.add(1);
            return row;
        } else {
            row.add(1);
            List<Integer> preRow = getRow(rowIndex - 1);
            for(int i=1; i<rowIndex; i++){
                row.add(preRow.get(i-1) + preRow.get(i));
            }
            row.add(1);
            return row;
        }
    }
}

