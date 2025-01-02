// There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

// For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
// Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

// Example 1:

// Input: numCourses = 2, prerequisites = [[1,0]]
// Output: [0,1]
// Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
// Example 2:

// Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
// Output: [0,2,1,3]
// Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
// So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
// Example 3:

// Input: numCourses = 1, prerequisites = []
// Output: [0]
 

// Constraints:

// 1 <= numCourses <= 2000
// 0 <= prerequisites.length <= numCourses * (numCourses - 1)
// prerequisites[i].length == 2
// 0 <= ai, bi < numCourses
// ai != bi
// All the pairs [ai, bi] are distinct.

class Solution {
    private Boolean dfs(Map<Integer, ArrayList<Integer>> pre, Set<Integer> cycle, Set<Integer> visited, int course, List<Integer> order) {
        if (cycle.contains(course))
            return false;
        if (visited.contains(course))
            return true;
        
        cycle.add(course);
        for (int j: pre.get(course)) {
            if (!dfs(pre, cycle, visited, j, order))
                return false;
        }
        cycle.remove(course);
        order.add(course);
        visited.add(course);
        return true;
    }
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> cycle = new HashSet<>();
        Map<Integer, ArrayList<Integer>> preMap = new HashMap<>();
        
        for(int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<Integer>());
        }
        
        for (int i = 0; i < prerequisites.length; i++) { 
            preMap.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }   
        
        List<Integer> order = new ArrayList<>();
        int[] finalOrder = new int[numCourses];
        
        for (int course = 0; course < numCourses; course++) {
            if (!dfs(preMap, cycle, visited, course, order))
                return new int[0];
        }
        
        for (int i = 0; i < order.size(); i++) {
            finalOrder[i] = order.get(i);
        }
        
        return finalOrder;
    }
}

// Using Kahn's algorithm (Topological sort)
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        int[] degree = new int[numCourses];
        int[] result = new int[numCourses];

        for (int[] pre: prerequisites) {
            degree[pre[0]] += 1;
            graph.computeIfAbsent(pre[1], k -> new ArrayList<>()).add(pre[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) queue.offer(i);
        }

        int i = 0;
        while(!queue.isEmpty()) {
            int course = queue.poll();
            result[i++] = course;
            
            if (graph.containsKey(course)) {
                for(int vertex: graph.get(course)) {
                    degree[vertex] -= 1;
                    if(degree[vertex] == 0) queue.offer(vertex);
                }
            }
        }

        if (i == numCourses) return result;
        return new int[]{};
    }
}