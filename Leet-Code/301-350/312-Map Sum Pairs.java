// Design a map that allows you to do the following:

// Maps a string key to a given value.
// Returns the sum of the values that have a key with a prefix equal to a given string.
// Implement the MapSum class:

// MapSum() Initializes the MapSum object.
// void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
// int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 

// Example 1:

// Input
// ["MapSum", "insert", "sum", "insert", "sum"]
// [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
// Output
// [null, null, 3, null, 5]

// Explanation
// MapSum mapSum = new MapSum();
// mapSum.insert("apple", 3);  
// mapSum.sum("ap");           // return 3 (apple = 3)
// mapSum.insert("app", 2);    
// mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

// Constraints:

// 1 <= key.length, prefix.length <= 50
// key and prefix consist of only lowercase English letters.
// 1 <= val <= 1000
// At most 50 calls will be made to insert and sum.

class MapSum {
    class TrieNode {
        Map<Character, TrieNode> prefix = new HashMap<>();
        int val;
        
        public TrieNode() {
            this.prefix = new HashMap<>();
            this.val = 0;
        }
        
        private boolean contains(char c) {
            return this.prefix.containsKey(c);
        }
        
        private void add(char c) {
            this.prefix.put(c, new TrieNode());
        }
        
        private TrieNode get(char c) {
            return this.prefix.get(c);
        }
        
        public void insert(String key, int val) {
            TrieNode current = this;
            for (char c: key.toCharArray()) {
                if (!current.contains(c)) {
                    current.add(c);
                }
                current = current.get(c);
                current.val += val;
            }
        }
        
        public int search(String prefix) {
            TrieNode current = this;
            for (char c: prefix.toCharArray()) {
                if (!current.contains(c)) {
                    return 0;
                }
                current = current.get(c);
            }
            return current.val;
        }
    }
    
    TrieNode root;
    Map<String, Integer> valMap;

    public MapSum() {
        root = new TrieNode();
        valMap = new HashMap<>();
    }
    
    public void insert(String key, int val) {
        int diff = val - valMap.getOrDefault(key, 0);
        valMap.put(key, val);
        root.insert(key, diff);
    }
    
    public int sum(String prefix) {
        return root.search(prefix);
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */