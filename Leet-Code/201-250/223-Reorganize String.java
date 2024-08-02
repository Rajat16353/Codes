// Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

// Return any possible rearrangement of s or return "" if not possible.

 

// Example 1:

// Input: s = "aab"
// Output: "aba"
// Example 2:

// Input: s = "aaab"
// Output: ""
 

// Constraints:

// 1 <= s.length <= 500
// s consists of lowercase English letters.

class Solution {
    private Map<Character, Integer> calculateFrequency(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch: s.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    private PriorityQueue<Pair<Integer, Character>> buildMaxHeap(Map<Character, Integer> freqMap) {
        PriorityQueue<Pair<Integer, Character>> maxHeap = new PriorityQueue<>(new Comparator<Pair<Integer, Character>>() {
            @Override
            public int compare(Pair<Integer, Character> a, Pair<Integer, Character> b) {
                return Integer.compare(b.getKey(), a.getKey());
            }
        });
        for(Map.Entry<Character, Integer> entry: freqMap.entrySet()) {
            maxHeap.offer(new Pair(entry.getValue(), entry.getKey()));
        }
        return maxHeap;
    }

    public String reorganizeString(String s) {
        PriorityQueue<Pair<Integer, Character>> maxHeap = buildMaxHeap(calculateFrequency(s));
        
        StringBuilder reorganizedString = new StringBuilder();
        Pair<Integer, Character> prev = null;

        while(!maxHeap.isEmpty() || prev != null) {
            if (prev != null && maxHeap.isEmpty()) {
                return "";
            }
            Pair<Integer, Character> current = maxHeap.poll();
            reorganizedString.append(current.getValue());
            if (prev != null) {
                maxHeap.offer(prev);
                prev = null;
            }
            if (current.getKey() - 1 != 0) {
                prev = new Pair(current.getKey() - 1, current.getValue());
            }
        }

        return reorganizedString.toString();
    }
}