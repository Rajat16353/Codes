// Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

 

// Example 1:


// Input: points = [[1,1],[2,2],[3,3]]
// Output: 3
// Example 2:


// Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
// Output: 4
 

// Constraints:

// 1 <= points.length <= 300
// points[i].length == 2
// -104 <= xi, yi <= 104
// All the points are unique.

class Solution {
    public int maxPoints(int[][] points) {
        int maxP = 0;
        int n = points.length;
        if (n == 1) return 1;

        for (int i = 0; i < n; i++) {
            Map<Double, Integer> map = new HashMap<>();
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                if ((x2 - x1) == 0) {
                    map.put(Double.MAX_VALUE, map.getOrDefault(Double.MAX_VALUE, 0) + 1);
                } else if (y2 - y1 == 0) {
                    map.put(0.0, map.getOrDefault(0.0, 0) + 1);
                } else {
                    double m = (double)(y2 - y1)/(x2 - x1);
                    map.put(m, map.getOrDefault(m, 0) + 1);
                }
            }
            for (Map.Entry<Double, Integer> entry: map.entrySet()) {
                maxP = Math.max(maxP, entry.getValue());
            }
        }
        return maxP + 1;
    }
}