// You are given an unweighted directed graph having 'V' vertices and 'E' edges. Your task is to count the number of strongly connected components (SCCs) present in the graph.

// A directed graph is said to be strongly connected if every vertex is reachable from every other vertex. The strongly connected components of a graph are the subgraphs which are themselves strongly connected.

// Note :
// Use zero-based indexing for the vertices.

// The given graph doesn’t contain any self-loops.
// Detailed explanation ( Input/output format, Notes, Images )
// Constraints :
// 1 <= T <= 10
// 1 <= V <= 10^4
// 0 <= E <= 10^4
// 0 <= a, b < V

// Time Limit: 1 sec
// Sample Input 1 :
// 1
// 5 6
// 0 1
// 1 2
// 1 4
// 2 3
// 3 2
// 4 0
// Sample Output 1 :
// 2
// Explanation of sample input 1 :
// For the first test case, the graph is shown below. There are two SCCs in the graph, which are enclosed in the boxes as shown in the image below.

// Sample Input 2 :
// 2
// 1 0
// 4 4
// 0 1
// 1 2
// 2 3
// 3 1
// Sample Output 2 :
// 1
// 2

import java.util.*;

public class Solution {
	public static int stronglyConnectedComponents(int v, ArrayList<ArrayList<Integer>> edges) {
		// Write your code here.
		boolean[] visited = new boolean[v];
		Stack<Integer> stack = new Stack<>();
		List<List<Integer>> graph = new ArrayList<>();
		
		for (int i = 0; i < v; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < edges.size(); i++) {
			int u1 = edges.get(i).get(0);
			int v1 = edges.get(i).get(1);
			graph.get(u1).add(v1);
		}

		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				dfs(i, graph, visited, stack);
			}
		}

		List<List<Integer>> reverseGraph = new ArrayList<>();

		for (int i = 0; i < v; i++) {
			reverseGraph.add(new ArrayList<>());
		}

		visited = new boolean[v];
		for (int i = 0; i < graph.size(); i++) {
			for (int nbr: graph.get(i)) {
				reverseGraph.get(nbr).add(i);
			}
		}

		int count = 0;
		while (!stack.isEmpty()) {
			int vertex = stack.pop();
			if (!visited[vertex]) {
				dfs(vertex, reverseGraph, visited);
				count += 1;
			}
		}

		return count;
	}

	private static void dfs(int vertex, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
		visited[vertex] = true;
		
		for (int nbr: graph.get(vertex)) {
			if (!visited[nbr]) {
				dfs(nbr, graph, visited, stack);
			}
		}

		if (stack != null) stack.push(vertex);
	}

	private static void dfs(int vertex, List<List<Integer>> graph, boolean[] visited) {
		dfs(vertex, graph, visited, null);
	}
}