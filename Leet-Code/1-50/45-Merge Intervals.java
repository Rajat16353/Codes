// # Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

// # Example 1:

// # Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
// # Output: [[1,6],[8,10],[15,18]]
// # Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
// # Example 2:

// # Input: intervals = [[1,4],[4,5]]
// # Output: [[1,5]]
// # Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

// # Constraints:

// # 1 <= intervals.length <= 104
// # intervals[i].length == 2
// # 0 <= starti <= endi <= 104

// # class Solution:
// #     def merge(self, intervals: List[List[int]]) -> List[List[int]]:
// #         res = []
// #         intervals.sort(key = lambda x: x[0])
// #         start = intervals[0][0]
// #         end = intervals[0][1]
// #         for tuple in intervals[1:]:
// #             if tuple[0] <= end:
// #                 if tuple[1] > end:
// #                     end = tuple[1]
// #             else:
// #                 res.append([start, end])
// #                 start = tuple[0]
// #                 end = tuple[1]
// #         res.append([start, end])
// #         return res

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        List<int[]> result = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int start = intervals[0][0];
        int end = intervals[0][1];

        int j = 0;
        for(int[] i: intervals) {
            if (i[0] <= end) {
                end = Math.max(end, i[1]);
            } else {
                result.add(new int[]{start, end});
                start = i[0];
                end = i[1];
            }
        }

        result.add(new int[]{start, end});

        return result.toArray(new int[0][]);
    }
}