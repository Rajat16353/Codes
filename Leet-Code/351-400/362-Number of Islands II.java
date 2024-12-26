// You have a 2D grid of ‘N’ rows and ‘M’ columns which are initially filled with water. You are given ‘Q’ queries each consisting of two integers ‘X’ and ‘Y’ and in each query operation, you have to turn the water at position (‘X’, ‘Y’) into a land. You are supposed to find the number of islands in the grid after each query.

// An island is a group of lands surrounded by water horizontally, vertically, or diagonally.

// Detailed explanation ( Input/output format, Notes, Images )
// Constraints:
// 1 <= T <= 5
// 1 <= N <= 1000
// 1 <= M <= 1000
// 1 <= Q <= 100000
// 0 <= X < N
// 0 <= Y < M

// Time limit: 1 sec
// Sample Input 1:
// 2
// 3 3
// 4
// 0 0
// 0 1
// 1 2
// 2 1
// 4 5
// 4
// 1 1
// 0 1
// 3 3
// 3 4
// Sample Output 1:
// 1 1 2 3
// 1 1 2 2
// Explanation of Sample Output 1:
// In test case 1, 

// 0.  000
//     000
//     000

// 1.  100
//     000
//     000

// 2.  110
//     000
//     000

//  3. 110
//     001
//     000

//  4. 110
//     001
//     100

// So, the answer is 1, 1, 2, 3.

// In test case 2,

// 0.  00000
//     00000
//     00000
//     00000

// 1.  00000
//     01000
//     00000
//     00000

// 2.  01000
//     01000
//     00000
//     00000

//  3. 01000
//     01000
//     00000
//     00010

//  4. 01000
//     01000
//     00000
//     00011

// So, the answer is 1, 1, 2, 2.
// Sample Input 2:
// 2
// 2 2
// 2
// 0 0
// 1 1
// 1 1
// 1
// 0 0
// Sample Output 2:
// 1 2
// 1
// Explanation of Sample Output 2:
// In test case 1, 

// 0.  00
//     00

// 1.  10
//     00

// 2.  10
//     01

// So, the answer is 1, 2.

// In test case 2,

// 0.  0

// 1.  1

// So, the answer is 1.

import java.util.*;

public class Solution {
    public static int[] numOfIslandsII(int n, int m, int[][] q) {
        int[] result = new int[q.length];
        int ir = 0;
        int[] parent = new int[m * n];
        int[] rank = new int[m * n];
        Arrays.fill(parent, -1);
        int count = 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1} ,{0 ,1}};

        for (int[] pos: q) {
            int r = pos[0];
            int c = pos[1];

            int cellNo = r * m + c;
            if (parent[cellNo] != -1) {
                result[ir++] = count;
                continue;
            }

            parent[cellNo] = cellNo;
            rank[cellNo] = 1;
            count += 1;

            for (int[] d: dirs) {
                int newR = r + d[0];
                int newC = c + d[1];

                int newCellNo = newR * m + newC;

                if (newR < 0 || newC < 0 || newR >= n || newC >= m || parent[newCellNo] == -1) {
                    continue;
                }

                int lx = find(cellNo, parent);
                int ly = find(newCellNo, parent);

                if (lx != ly) {
                    if (rank[lx] > rank[ly]) {
                        parent[ly] = lx;
                    } else if (rank[lx] < rank[ly]) {
                        parent[lx] = ly;
                    } else {
                        parent[lx] = ly;
                        rank[ly] += 1;
                    }
                    count -= 1;
                }
            }
            result[ir++] = count;
        }

        return result;
    }

    private static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x], parent);
    }
}