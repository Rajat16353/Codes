// Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

// Implement the LRUCache class:

// LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
// int get(int key) Return the value of the key if the key exists, otherwise return -1.
// void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
// The functions get and put must each run in O(1) average time complexity.

 

// Example 1:

// Input
// ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
// Output
// [null, null, null, 1, null, -1, null, -1, 3, 4]

// Explanation
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 1); // cache is {1=1}
// lRUCache.put(2, 2); // cache is {1=1, 2=2}
// lRUCache.get(1);    // return 1
// lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
// lRUCache.get(2);    // returns -1 (not found)
// lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
// lRUCache.get(1);    // return -1 (not found)
// lRUCache.get(3);    // return 3
// lRUCache.get(4);    // return 4
 

// Constraints:

// 1 <= capacity <= 3000
// 0 <= key <= 104
// 0 <= value <= 105
// At most 2 * 105 calls will be made to get and put.

class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            prev = null;
        }
    }

    int capacity;
    Map<Integer, Node> cache = new HashMap<>();
    Node first;
    Node end;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        initializeDoublyLinkedList();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node node = cache.get(key);
        removeNode(node);
        markRecentlyUsed(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = null;
        if (cache.containsKey(key)) {
            removeNode(cache.get(key));
        }
        node = new Node(key, value);
        markRecentlyUsed(node);
        cache.put(key, node);

        if(cache.size() > capacity) removeLRU();
    }

    private void initializeDoublyLinkedList() {
        first = new Node(0, 0);
        end = new Node(0, 0);

        first.next = end;
        end.prev = first;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert at beginning
    private void markRecentlyUsed(Node node) {
        node.next = first.next;
        first.next.prev = node;
        first.next = node;
        node.prev = first;
    }

    private void removeLRU() {
        Node lru = end.prev;
        removeNode(lru);
        cache.remove(lru.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */