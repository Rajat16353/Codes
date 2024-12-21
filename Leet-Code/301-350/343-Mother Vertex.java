// Given a Directed Graph, find a Mother Vertex in the Graph (if present). 
// A Mother Vertex is a vertex through which we can reach all the other vertices of the Graph.

// Example 1:

// Input: 

// Output: 0
// Explanation: According to the given edges, all 
// nodes can be reached from nodes from 0, 1 and 2. 
// But, since 0 is minimum among 0,1 and 2, so 0 
// is the output.
// Example 2:

// Input: 

// Output: -1
// Explanation: According to the given edges, 
// no vertices are there from where we can 
// reach all vertices. So, output is -1.
// Your Task:
// You don't need to read or print anything. Your task is to complete the function findMotherVertex() which takes V denoting the number of vertices and adjacency list as input parameter and returns the vertices from through which we can traverse all other vertices of the graph. If there is more than one possible nodes then return the node with minimum value. If not possible returns -1.

// Expected Time Complexity: O(V + E)
// Expected Space Complexity: O(V)

//{ Driver Code Starts
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
            ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
            for(int i = 0; i < V; i++)
                adj.add(i, new ArrayList<Integer>());
            for(int i = 0; i < E; i++){
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
            }
            Solution obj = new Solution();
            int ans = obj.findMotherVertex(V, adj);
            System.out.println(ans);
       
System.out.println("~");
}
    }
}
// } Driver Code Ends



class Solution
{
    //Function to find a Mother Vertex in the Graph.
    public int findMotherVertex(int v, ArrayList<ArrayList<Integer>>adj)
    {
        boolean[] visited = new boolean[v];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }
        
        int u = stack.pop();
        visited = new boolean[v];
        dfs(u, adj, visited, null);
        
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                return -1;
            }
        }
        return u;
    }
    
    private void dfs(int u, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        
        for (int nbr: adj.get(u)) {
            if (!visited[nbr]) {
                dfs(nbr, adj, visited, stack);
            }
        }
        
        if (stack != null) stack.push(u);
    }
}