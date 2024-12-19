// You have been given a directed weighted graph of ‘N’ vertices labeled from 1 to 'N' and ‘M’ edges. Each edge connecting two nodes 'u' and 'v' has a weight 'w' denoting the distance between them.

// Your task is to detect whether the graph contains a negative cycle or not. A negative cycle is a cycle whose edges are such that the sum of their weights is a negative value.

// Example:


// The above graph contains a cycle ( 1 -> 2 -> 3 -> 1 ) and the sum of weights of edges is -1 (1 - 4 + 2 = -1) which is negative. Hence the graph contains a negative cycle.
// Note:

// It's guaranteed that the graph doesn't contain self-loops and multiple edges.
// Detailed explanation ( Input/output format, Notes, Images )
// Sample input 1
// 1
// 5 6
// 1 5 2
// 2 1 1
// 2 3 -4
// 3 4 2
// 4 5 -3
// 5 2 1 
// Sample output 1
// True   
// Explanation of sample input 1
// In the 1st test case, there are two cycles ( 2 -> 3 -> 4 -> 5 -> 2) and ( 1 -> 5 -> 2 -> 1), but only the first one is the negative cycle.

// Sample input 2
// 2
// 3 2
// 1 3 2
// 2 1 1
// 2 2
// 1 2 -1
// 2 1 -1
// Sample output 2
// False
// True

import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

public class Solution {
	
	public static boolean detectNegativeCycle(int n, int m, ArrayList<ArrayList<Integer>> edges) {
		int[] cost = new int[n];

		Arrays.fill(cost, 1000000);
		cost[0] = 0;

		for (int i = 0; i < n - 1; i++) {
			for (List<Integer> edge: edges) {
				int u = edge.get(0) - 1;
				int v = edge.get(1) - 1;
				int w = edge.get(2);

				if (cost[u] + w < cost[v]) cost[v] = cost[u] + w;
			}
		}

		for (List<Integer> edge: edges) {
			int u = edge.get(0) - 1;
			int v = edge.get(1) - 1;
			int w = edge.get(2);

			if (cost[u] + w < cost[v]) return true;
		}

		return false;
	}

}