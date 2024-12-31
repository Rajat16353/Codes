// Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].

// The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:

// The rank is an integer starting from 1.
// If two elements p and q are in the same row or column, then:
// If p < q then rank(p) < rank(q)
// If p == q then rank(p) == rank(q)
// If p > q then rank(p) > rank(q)
// The rank should be as small as possible.
// The test cases are generated so that answer is unique under the given rules.

 

// Example 1:


// Input: matrix = [[1,2],[3,4]]
// Output: [[1,2],[2,3]]
// Explanation:
// The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
// The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
// The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
// The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
// Example 2:


// Input: matrix = [[7,7],[7,7]]
// Output: [[1,1],[1,1]]
// Example 3:


// Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
// Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 

// Constraints:

// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 500
// -109 <= matrix[row][col] <= 109

class Solution {
    int[] rows;
    int[] cols;
    int m;
    int n;

    public int[][] matrixRankTransform(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        rows = new int[m];
        cols = new int[n];

        Pair[] arr = new Pair[m * n];
        int k = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[k] = new Pair(matrix[i][j], i, j);
                k += 1;
            }
        }

        Arrays.sort(arr);

        int last = Integer.MIN_VALUE;
        List<Pair> group = new ArrayList<>();

        for (Pair p: arr) {
            if (p.ele != last) {
                processGroup(group, matrix);
                last = p.ele;
                group = new ArrayList<>();
            }
            group.add(p);
        }

        processGroup(group, matrix);
        return matrix;
    }

    private class Pair implements Comparable<Pair> {
        int ele;
        int r;
        int c;

        public Pair(int ele, int r, int c) {
            this.ele = ele;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pair b) {
            return this.ele - b.ele;
        }
    }

    private void processGroup(List<Pair> group, int[][] matrix) {
        int[] parent = new int[n + m];
        Arrays.fill(parent, -1);

        for (Pair p: group) {
            int r = p.r;
            int c = p.c;

            int p1 = findParent(r, parent);
            int p2 = findParent(m + c, parent);

            if (p1 != p2) {
                parent[p1] = Math.min(parent[p1], Math.min(parent[p2], -Math.max(rows[r], cols[c]) - 1));
                parent[p2] = p1;
            }
        }

        for (Pair p: group) {
            int r = p.r;
            int c = p.c;

            int par = findParent(r, parent);
            int rank = -parent[par];

            matrix[r][c] = rank;
            rows[r] = rank;
            cols[c] = rank;
        }
    }

    private int findParent(int x, int[] parent) {
        if (parent[x] < 0) {
            return x;
        }

        return parent[x] = findParent(parent[x], parent);
    }
}