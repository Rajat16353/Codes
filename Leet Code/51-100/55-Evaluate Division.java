// You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

// You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

// Return the answers to all queries. If a single answer cannot be determined, return -1.0.

// Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 

// Example 1:

// Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
// Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
// Explanation: 
// Given: a / b = 2.0, b / c = 3.0
// queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
// return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
// Example 2:

// Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
// Output: [3.75000,0.40000,5.00000,0.20000]
// Example 3:

// Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
// Output: [0.50000,2.00000,-1.00000,-1.00000]
 

// Constraints:

// 1 <= equations.length <= 20
// equations[i].length == 2
// 1 <= Ai.length, Bi.length <= 5
// values.length == equations.length
// 0.0 < values[i] <= 20.0
// 1 <= queries.length <= 20
// queries[i].length == 2
// 1 <= Cj.length, Dj.length <= 5
// Ai, Bi, Cj, Dj consist of lower case English letters and digits.

class Solution {
    class GraphNode {
        String key;
        double value;
        public GraphNode(String key, double value) {
            this.key = key;
            this.value = value;
        }
    }
    private Map<String, List<GraphNode>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<GraphNode>> map = new HashMap<>();
        for(int i=0; i<values.length; i++) {
            String src = equations.get(i).get(0);
            String des = equations.get(i).get(1);
            map.computeIfAbsent(src,v -> new ArrayList<>()).add(new GraphNode(des, values[i]));
            map.computeIfAbsent(des,v -> new ArrayList<>()).add(new GraphNode(src, 1/values[i]));
        }
        return map;
    }
    
    private double dfs(String s, String d, Set<String> visited, Map<String, List<GraphNode>> map) {
        if (!map.containsKey(s)) {
            return -1.0;
        }
        if (s.equals(d)) {
            return 1.0;
        }
        visited.add(s);
        for (GraphNode node: map.get(s)) {
            if (!visited.contains(node.key)) {
                double res = dfs(node.key, d, visited, map);
                if (res != -1.0) {
                    return res * node.value;
                }
            }
        }
        return -1.0;            
    }
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<GraphNode>> map = buildGraph(equations, values);
        double[] res = new double[queries.size()];
        for(int i=0; i<queries.size(); i++) {
            res[i] = dfs(queries.get(i).get(0), queries.get(i).get(1), new HashSet<>(), map);
        }
        
        return res;
    }
}