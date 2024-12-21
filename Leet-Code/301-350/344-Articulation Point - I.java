// Given an undirected connected graph with V vertices and adjacency list adj. You are required to find all the vertices removing which (and edges through it) disconnects the graph into 2 or more components and return it in sorted manner.
// Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.

// Example 1:

// Input:

// Output:{1,4}
// Explanation: Removing the vertex 1 will
// discconect the graph as-

// Removing the vertex 4 will disconnect the
// graph as-

 

// Your Task:
// You don't need to read or print anything. Your task is to complete the function articulationPoints() which takes V and adj as input parameters and returns a list containing all the vertices removing which turn the graph into two or more disconnected components in sorted order. If there are no such vertices then returns a list containing -1.
 

// Expected Time Complexity: O(V + E)
// Expected Auxiliary Space: O(V)
 

// Constraints:
// 1 ≤ V ≤ 105

//{ Driver Code Starts
// Initial Template for Java
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>>adj = new ArrayList<>();
            for(int i = 0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++){
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer>ans = obj.articulationPoints(V, adj);
            for (int i =0 ;i < ans.size (); i++) 
                System.out.print (ans.get (i) + " ");
            System.out.println();
        
System.out.println("~");
}
    }
}

// } Driver Code Ends


class Solution
{
    int[] parent;
    int[] low;
    int[] disc;
    int time;
    boolean[] visited;
    boolean[] ap;
    //Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> articulationPoints(int v,ArrayList<ArrayList<Integer>> adj)
    {
        parent = new int[v];
        low = new int[v];
        disc = new int[v];
        time = 0;
        visited = new boolean[v];
        ap = new boolean[v];
        parent[0] = -1;
        
        dfs(0, adj);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            if (ap[i]) result.add(i);
        }
        
        if (result.isEmpty()) result.add(-1);
        return result;
    }
    
    private void dfs(int u, ArrayList<ArrayList<Integer>> graph) {
        visited[u] = true;
        low[u] = disc[u] = time;
        time += 1;
        int count = 0;
        
        ArrayList<Integer> nbrs = graph.get(u);
        for (int i = 0; i < nbrs.size(); i++) {
            int v = nbrs.get(i);
            if (parent[u] == v) {
                continue;
            } else if (visited[v]) {
                low[u] = Math.min(low[u], disc[v]);
            } else {
                parent[v] = u;
                count += 1;
                dfs(v, graph);
                low[u] = Math.min(low[u], low[v]);
                if (parent[u] == -1) {
                    if (count >= 2) ap[u] = true;
                } else {
                    if (low[v] >= disc[u]) {
                        ap[u] = true;
                    }
                }
            }
        }
    }
}