// You are given an integer n and a 2D integer array queries.

// There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

// queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

// There are no two queries such that queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].

// Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.

 

// Example 1:

// Input: n = 5, queries = [[2,4],[0,2],[0,4]]

// Output: [3,2,1]

// Explanation:



// After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.



// After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.



// After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.

// Example 2:

// Input: n = 4, queries = [[0,3],[0,2]]

// Output: [1,1]

// Explanation:



// After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.



// After the addition of the road from 0 to 2, the length of the shortest path remains 1.

 

// Constraints:

// 3 <= n <= 105
// 1 <= queries.length <= 105
// queries[i].length == 2
// 0 <= queries[i][0] < queries[i][1] < n
// 1 < queries[i][1] - queries[i][0]
// There are no repeated roads among the queries.
// There are no two queries such that i != j and queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].

// Using array for segment tree
class Solution {
    class SegmentTree {
        int[] segmentTree;
        int[] pendingUpdate;
        int n;

        SegmentTree(int n) {
            this.n = n;
            segmentTree = new int[4*n];
            pendingUpdate = new int[4*n];
            Arrays.fill(pendingUpdate, -1);
            buildST(0, 0, n - 1);
        }

        private int buildST(int i, int low, int high) {
            if (low == high) {
                segmentTree[i] = 1;
                if (low == 0) segmentTree[i] = 0;
                return segmentTree[i];
            }

            int mid = low + (high - low)/2;
            segmentTree[i] = buildST(2*i + 1, low, mid) + buildST(2*i + 2, mid + 1, high);
            return segmentTree[i];
        }

        private void lazyPropogate(int i, int low, int high) {
            if (pendingUpdate[i] != -1) {
                segmentTree[i] = 0;
                if (low != high) {
                    pendingUpdate[2*i + 1] = pendingUpdate[i];
                    pendingUpdate[2*i + 2] = pendingUpdate[i];
                }
                pendingUpdate[i] = -1;
            }
        }

        private int updateST(int i, int low, int high, int left, int right) {
            lazyPropogate(i, low, high);
            if (low > right || high < left) return segmentTree[i]; 
            if (low >= left && high <= right) {
                segmentTree[i] = 0;
                if (low != high) {
                    pendingUpdate[2*i + 1] = 0;
                    pendingUpdate[2*i + 2] = 0;
                }
                return segmentTree[i];
            }
            
            int mid = low + (high - low)/2;
            segmentTree[i] = updateST(2*i + 1, low, mid, left, right) + updateST(2*i + 2, mid + 1, high, left, right);
            return segmentTree[i];
        }

        private int updateST(int left, int right) {
            return updateST(0, 0, n - 1, left, right);
        }
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        SegmentTree st = new SegmentTree(n);
        int[] result = new int[queries.length];

        int k = 0;
        for(int[] query: queries) {
            result[k++] = st.updateST(query[0] + 1, query[1] - 1);
        }

        return result;
    }
}