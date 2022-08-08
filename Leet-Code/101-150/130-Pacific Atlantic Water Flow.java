// There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

// The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

// The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

// Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

 

// Example 1:


// Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
// Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
// Example 2:

// Input: heights = [[2,1],[1,2]]
// Output: [[0,0],[0,1],[1,0],[1,1]]
 

// Constraints:

// m == heights.length
// n == heights[r].length
// 1 <= m, n <= 200
// 0 <= heights[r][c] <= 105

class Solution {
    private void isWaterFlowPossible(int[][] heights, int r, int c, Set<Pair<Integer, Integer>> ocean, int previousHeight) {
        Pair rc = new Pair(r, c);
        if (ocean.contains(rc) || r < 0 || c < 0 || r >= heights.length || c >= heights[0].length || previousHeight > heights[r][c]) {
            return;
        }
        
        ocean.add(rc);
        
        isWaterFlowPossible(heights, r+1, c, ocean, heights[r][c]);
        isWaterFlowPossible(heights, r, c+1, ocean, heights[r][c]);
        isWaterFlowPossible(heights, r-1, c, ocean, heights[r][c]);
        isWaterFlowPossible(heights, r, c-1, ocean, heights[r][c]);
    }
    
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Pair<Integer, Integer>> pacific = new HashSet<>();
        Set<Pair<Integer, Integer>> atlantic = new HashSet<>();
        int ROWS = heights.length;
        int COLS = heights[0].length;
        
        
        for(int c = 0; c < COLS; c++) {
            isWaterFlowPossible(heights, 0, c, pacific, heights[0][c]);
            isWaterFlowPossible(heights, ROWS-1, c, atlantic, heights[ROWS-1][c]);
        }
        
        for(int r = 0; r < ROWS; r++) {
            isWaterFlowPossible(heights, r, 0, pacific, heights[r][0]);
            isWaterFlowPossible(heights, r, COLS-1, atlantic, heights[r][COLS-1]);
        }
        
        for(int r = 0; r < ROWS; r++) {
            for(int c = 0; c < COLS; c++) {
                Pair rc = new Pair(r, c);
                if (pacific.contains(rc) && atlantic.contains(rc)) {
                    result.add(Arrays.asList(r,c));
                }
            }
        }
        return result;
    }
}