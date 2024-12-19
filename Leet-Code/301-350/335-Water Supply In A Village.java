// There are ‘N’ houses in a village. Ninja wants to supply water for all the houses by building wells and laying pipes.

// For each house ‘i’, we can either build a well inside it directly with cost ‘WELLS[i]’, or pipe in water from another well to it. The total cost to lay pipes between houses is given by the array ‘PIPES’, where ‘PIPES[i]’ = ‘[HOUSE1, HOUSE2, COST]’ and the ‘COST’ represent the total cost connect ‘HOUSE1’ and ‘HOUSE2’ together using a pipe.

// Note: Given all the connections are bidirectional.

// For Example:

// For ‘N’ = 3, ‘WELLS[]’ = ‘[1,2,2]’, ‘PIPES[]’ = [ [1, 2, 1], [2 , 3, 1]]. The image shows the costs of connecting houses using pipes. The best strategy is to build a well in the first house with cost 1 and connect the other houses to it with cost 2 so the total cost is 3.

// Ninja wants to find out the minimum total cost to supply water to all houses in the village. Can you help the Ninja to find out this minimum cost?

// Detailed explanation ( Input/output format, Notes, Images )
// Constraints :
// 1 <= T <= 100
// 1 <= N <= 10 ^ 2
// 0 <= WELLS[i] <= 10^5
// 1 <= K <= 10000
// 1 <= PIPES[i][0], PIPES[i][1] <= N
// 0 <= PIPES[i][2] <= 10^5
// PIPES[i][0] != PIPES[i][1]

// Where ‘T’ is the number of test cases, ‘N’ is the number of houses in the village, WELL[i]’ denotes the cost of inserting a well at house ‘i’ and ‘PIPES[i][0]’, ‘PIPES[i][1]’ and ‘PIPES[2]’ represents the cost to connect house ‘PIPES[i][0]’ to ‘PIPES[i][1]’.

// Time Limit: 1 sec
// Sample Input 1 :
// 2
// 4 2
// 1 4 4 4
// 1 4 2
// 1 2 1
// 3 3
// 5 5 5
// 1 2 80
// 1 3 20
// 2 3 90
// Sample Output 1:
// 8
// 15    
// Explanation for Sample Output 1:
// For the first test case :
// See the picture below for the output reference:

// The efficient way to supply water to all houses is to connect 0 with 1 and 0 with 2 and build a well at house 0 and 3. So the total cost will be 1 (well at house 0) + 4 (well at house 3) + 2 (connect 0 and 1) + 1 (connect 0 and 2) which is 8.

// For the second test case :
// See the picture below for the output reference:

// The efficient way to supply water to all houses is to make well at all the houses. So the total cost will be 5 (well at house 0) + 5 (well at house 1) + 5 (well at house 2) which is 15.
// Sample Input 2 :
// 2
// 2 1
// 8 2
// 1 2 1
// 2 1
// 1 1
// 1 2 1
// Sample Output 2 :
// 3
// 2
// Explanation for Sample Output 2:
// For the first test case:
// The efficient way to supply water to all houses is to connect 1 with 2 and build a well at house 1. So the total cost will be 2 (well at house 1) + 1 (connect 1 and 2) Which is equal to 3.

// For the second test case :
// The efficient way to supply water to all houses is to connect 1 with 2 or build well at both of the houses. The answer will be the same for either case. Which is equal to 2.

import java.lang.Integer;
import java.util.*
;
public class Solution {

    public static int supplyWater(int n, int k, int[] wells, int[][] pipes) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        boolean[] visited = new boolean[n + 1];
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] pipe: pipes) {
            int u = pipe[0];
            int v = pipe[1];
            int cost = pipe[2];
            graph.get(u).add(new int[]{cost, v});
            graph.get(v).add(new int[]{cost, u});
        }

        for (int i = 0; i < n; i++) {
            graph.get(i + 1).add(new int[]{wells[i], 0});
            graph.get(0).add(new int[]{wells[i], i + 1});
        }
        
        minHeap.offer(new int[]{0, 0});

        int cost = 0;
        while(!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int edge = top[0], u = top[1];
            
            if (visited[u]) continue;

            visited[u] = true;
            cost += edge;
            
            for (int[] village: graph.get(u)) {
                if (!visited[village[1]]) {
                    minHeap.offer(village);
                }
            }
        }

        return cost;
    }


    
}