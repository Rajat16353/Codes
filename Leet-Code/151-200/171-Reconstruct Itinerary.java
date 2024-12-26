// You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

// All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

// For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
// You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

 

// Example 1:


// Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
// Output: ["JFK","MUC","LHR","SFO","SJC"]
// Example 2:


// Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
// Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
// Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 

// Constraints:

// 1 <= tickets.length <= 300
// tickets[i].length == 2
// fromi.length == 3
// toi.length == 3
// fromi and toi consist of uppercase English letters.
// fromi != toi

class Solution {
    private void dfs(Map<String, Stack<String>> map, String currentAirport, List<String> itinerary) {
        while(map.containsKey(currentAirport) && map.get(currentAirport).size() > 0) {
            dfs(map, map.get(currentAirport).pop(), itinerary);
        }
        itinerary.add(currentAirport);
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Stack<String>> map = new HashMap<>();
        Collections.sort(tickets, new Comparator<List<String>> () {
            @Override
            public int compare(List<String> ticket1, List<String> ticket2) {
                return ticket2.get(1).compareTo(ticket1.get(1));
            }
        });

        for (List<String> airports: tickets) {
            if (map.containsKey(airports.get(0))) {
                map.get(airports.get(0)).push(airports.get(1));
            } else {
                Stack<String> stack = new Stack<>();
                stack.push(airports.get(1));
                map.put(airports.get(0), stack);
            }
        }

        System.out.println(Arrays.asList(map));

        List<String> itinerary = new ArrayList<>();
        dfs(map, "JFK", itinerary);
        Collections.reverse(itinerary);
        return itinerary;
    }
}

// Using eulerian path
class Solution {
    List<String> result;
    Map<String, PriorityQueue<String>> graph;
    public List<String> findItinerary(List<List<String>> tickets) {
        result = new ArrayList<>();
        graph = new HashMap<>();
        for (List<String> paths: tickets) {
            graph.computeIfAbsent(paths.get(0), k -> new PriorityQueue<>()).add(paths.get(1));
        }

        dfs("JFK");

        return result;
    }

    private void dfs(String src) {
        PriorityQueue<String> nbrs = graph.get(src);

        while (nbrs != null && !nbrs.isEmpty()) {
            String nbr = nbrs.poll();
            dfs(nbr);
        }

        result.addFirst(src);
    }
}