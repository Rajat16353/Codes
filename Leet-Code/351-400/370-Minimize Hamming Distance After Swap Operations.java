// You are given two integer arrays, source and target, both of length n. You are also given an array allowedSwaps where each allowedSwaps[i] = [ai, bi] indicates that you are allowed to swap the elements at index ai and index bi (0-indexed) of array source. Note that you can swap elements at a specific pair of indices multiple times and in any order.

// The Hamming distance of two arrays of the same length, source and target, is the number of positions where the elements are different. Formally, it is the number of indices i for 0 <= i <= n-1 where source[i] != target[i] (0-indexed).

// Return the minimum Hamming distance of source and target after performing any amount of swap operations on array source.

 

// Example 1:

// Input: source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
// Output: 1
// Explanation: source can be transformed the following way:
// - Swap indices 0 and 1: source = [2,1,3,4]
// - Swap indices 2 and 3: source = [2,1,4,3]
// The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
// Example 2:

// Input: source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
// Output: 2
// Explanation: There are no allowed swaps.
// The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
// Example 3:

// Input: source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
// Output: 0
 

// Constraints:

// n == source.length == target.length
// 1 <= n <= 105
// 1 <= source[i], target[i] <= 105
// 0 <= allowedSwaps.length <= 105
// allowedSwaps[i].length == 2
// 0 <= ai, bi <= n - 1
// ai != bi

class Solution {
    int[] parent;
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] swaps: allowedSwaps) {
            int p1 = findParent(swaps[0]);
            int p2 = findParent(swaps[1]);

            if (p1 != p2) {
                parent[p2] = p1;
            }
        }

        Map<Integer, Map<Integer, Integer>> parentSourceFreq = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = findParent(i);
            int num = source[i];
            Map<Integer, Integer> map = parentSourceFreq.computeIfAbsent(root, k -> new HashMap<>());

            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int hd = 0;

        for (int i = 0; i < n; i++) {
            int root = findParent(i);
            int num = target[i];

            Map<Integer, Integer> map = parentSourceFreq.get(root);
            if (!map.containsKey(num)) {
                hd++;
                continue;
            }

            if (map.get(num) <= 0) {
                hd++;
            } else  {
                map.put(num, map.get(num) - 1);
            }
        }

        return hd;
    }

    private int findParent(int x) {
        if (parent[x] == x) return x;

        return parent[x] = findParent(parent[x]);
    }
}

// Using rank array
class Solution {
    int[] parent;
    int[] rank;
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int[] swaps: allowedSwaps) {
            int p1 = findParent(swaps[0]);
            int p2 = findParent(swaps[1]);

            if (p1 != p2) {
                if (rank[p1] > rank[p2]) {
                    parent[p2] = p1;
                } else if (rank[p2] > rank[p1]) {
                    parent[p1] = p2;
                } else {
                    parent[p2] = p1;
                    rank[p1] += 1;
                }
            }
        }

        Map<Integer, Map<Integer, Integer>> parentSourceFreq = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = findParent(i);
            int num = source[i];
            Map<Integer, Integer> map = parentSourceFreq.computeIfAbsent(root, k -> new HashMap<>());

            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int hd = 0;

        for (int i = 0; i < n; i++) {
            int root = findParent(i);
            int num = target[i];

            Map<Integer, Integer> map = parentSourceFreq.get(root);
            if (!map.containsKey(num)) {
                hd++;
                continue;
            }

            if (map.get(num) <= 0) {
                hd++;
            } else  {
                map.put(num, map.get(num) - 1);
            }
        }

        return hd;
    }

    private int findParent(int x) {
        if (parent[x] == x) return x;

        return parent[x] = findParent(parent[x]);
    }
}