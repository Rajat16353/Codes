// There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

// You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

 

// Example 1:


// Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
// Output: 700
// Explanation:
// The graph is shown above.
// The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
// Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
// Example 2:


// Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
// Output: 200
// Explanation:
// The graph is shown above.
// The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
// Example 3:


// Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
// Output: 500
// Explanation:
// The graph is shown above.
// The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 

// Constraints:

// 1 <= n <= 100
// 0 <= flights.length <= (n * (n - 1) / 2)
// flights[i].length == 3
// 0 <= fromi, toi < n
// fromi != toi
// 1 <= pricei <= 104
// There will not be any multiple flights between two cities.
// 0 <= src, dst, k < n
// src != dst

// Using min heap
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight: flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];

            graph.get(u).add(new Pair(v, cost));
        }

        PriorityQueue<Tuple> minHeap = new PriorityQueue<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        minHeap.offer(new Tuple(0, src, 0));

        while (!minHeap.isEmpty()) {
            Tuple curr = minHeap.poll();
            int stops = curr.stops;
            int cSrc = curr.src;
            int cost = curr.cost;

            if (stops > k) continue;

            for (Pair nbr: graph.get(cSrc)) {
                int dest = nbr.dest;
                int travelCost = nbr.cost;

                if (cost + travelCost < dist[dest] && stops <= k) {
                    dist[dest] = cost + travelCost;
                    minHeap.offer(new Tuple(stops + 1, dest, dist[dest]));
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    private class Pair {
        int dest;
        int cost;

        public Pair (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    private class Tuple implements Comparable<Tuple> {
        int stops;
        int src;
        int cost;

        public Tuple (int stops, int src, int cost) {
            this.stops = stops;
            this.src = src;
            this.cost = cost;
        }

        @Override
        public int compareTo(Tuple b) {
            return Integer.compare(this.stops, b.stops);
        }
    }
}

// Using Queue
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] flight: flights) {
            int u = flight[0];
            int v = flight[1];
            int cost = flight[2];

            graph.get(u).add(new Pair(v, cost));
        }

        Queue<Tuple> minHeap = new LinkedList<>();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        minHeap.offer(new Tuple(0, src, 0));

        while (!minHeap.isEmpty()) {
            Tuple curr = minHeap.poll();
            int stops = curr.stops;
            int cSrc = curr.src;
            int cost = curr.cost;

            if (stops > k) continue;

            for (Pair nbr: graph.get(cSrc)) {
                int dest = nbr.dest;
                int travelCost = nbr.cost;

                if (cost + travelCost < dist[dest] && stops <= k) {
                    dist[dest] = cost + travelCost;
                    minHeap.offer(new Tuple(stops + 1, dest, dist[dest]));
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    private class Pair {
        int dest;
        int cost;

        public Pair (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    private class Tuple implements Comparable<Tuple> {
        int stops;
        int src;
        int cost;

        public Tuple (int stops, int src, int cost) {
            this.stops = stops;
            this.src = src;
            this.cost = cost;
        }

        @Override
        public int compareTo(Tuple b) {
            return Integer.compare(this.stops, b.stops);
        }
    }
}