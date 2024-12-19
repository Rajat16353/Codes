// You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

// The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

// Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

 

// Example 1:


// Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
// Output: 20
// Explanation: 

// We can connect the points as shown above to get the minimum cost of 20.
// Notice that there is a unique path between every pair of points.
// Example 2:

// Input: points = [[3,12],[-2,5],[-4,1]]
// Output: 18
 

// Constraints:

// 1 <= points.length <= 1000
// -106 <= xi, yi <= 106
// All pairs (xi, yi) are distinct.

// Prims
class Solution {
    public int minCostConnectPoints(int[][] points) {
        Map<Integer, List<Pair>> adjacencyList = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                adjacencyList.computeIfAbsent(i, k -> new ArrayList<>()).add(new Pair(dist, j));
                adjacencyList.computeIfAbsent(j, k -> new ArrayList<>()).add(new Pair(dist, i));
            }
        }

        int res = 0;
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(new PairComparator());
        minHeap.offer(new Pair(0, 0));
        Set<Integer> visited = new HashSet<>();
        

        while(visited.size() != points.length) {
            Pair top = minHeap.poll();
            if (visited.contains(top.point)) continue;

            res += top.dist;
            visited.add(top.point);
            if (adjacencyList.containsKey(top.point)) {
                for (Pair pt: adjacencyList.get(top.point)) {
                    if (!visited.contains(pt)) {
                        minHeap.offer(pt);
                    }
                }
            }
        }

        return res;
    }

    private class Pair {
        int dist;
        int point;

        public Pair(int dist, int point) {
            this.dist = dist;
            this.point = point;
        }
    }

    class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            return Integer.compare(a.dist, b.dist);
        }
    }
}

// Optmised Prims
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int weight = 0;
        Map<Integer, Integer> heapDict = new HashMap<>();
        heapDict.put(0, 0);
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        minHeap.offer(new int[]{0, 0});
        boolean[] visited = new boolean[n];
        
        while(!minHeap.isEmpty()) {
            int[] top = minHeap.poll();
            int w = top[0], u = top[1];
            
            if (visited[u] || heapDict.getOrDefault(u, Integer.MAX_VALUE) < w) continue;

            weight += w;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int newDistance = manhattanDistance(points[u], points[v]);
                    if (newDistance < heapDict.getOrDefault(v, Integer.MAX_VALUE)) {
                        heapDict.put(v, newDistance);
                        minHeap.offer(new int[]{newDistance, v});
                    }
                }
            }
        }

        return weight;
    }

    private int manhattanDistance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}