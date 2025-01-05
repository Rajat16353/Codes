// Design and implement a data structure for a Least Frequently Used (LFU) cache.

// Implement the LFUCache class:

// LFUCache(int capacity) Initializes the object with the capacity of the data structure.
// int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
// void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
// To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.

// When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.

// The functions get and put must each run in O(1) average time complexity.

 

// Example 1:

// Input
// ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
// [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
// Output
// [null, null, null, 1, null, -1, 3, null, -1, 3, 4]

// Explanation
// // cnt(x) = the use counter for key x
// // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
// LFUCache lfu = new LFUCache(2);
// lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
// lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
// lfu.get(1);      // return 1
//                  // cache=[1,2], cnt(2)=1, cnt(1)=2
// lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
//                  // cache=[3,1], cnt(3)=1, cnt(1)=2
// lfu.get(2);      // return -1 (not found)
// lfu.get(3);      // return 3
//                  // cache=[3,1], cnt(3)=2, cnt(1)=2
// lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
//                  // cache=[4,3], cnt(4)=1, cnt(3)=2
// lfu.get(1);      // return -1 (not found)
// lfu.get(3);      // return 3
//                  // cache=[3,4], cnt(4)=1, cnt(3)=3
// lfu.get(4);      // return 4
//                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 

// Constraints:

// 1 <= capacity <= 104
// 0 <= key <= 105
// 0 <= value <= 109
// At most 2 * 105 calls will be made to get and put.

class LFUCache {
    private class Node {
        int key;
        int val;
        int freq;
        Node next;
        Node prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 1;
        }
    }

    private class DLL {
        Node head;
        Node last;
        int listSize;

        public DLL() {
            this.listSize = 0;
            this.head = new Node(-1, -1);
            this.last = new Node(-1, -1);
            this.head.next = last;
            this.last.prev = head;
        }

        public void addNode(Node cur) {
            head.next.prev = cur;
            cur.next = head.next;
            cur.prev = head;
            head.next = cur;
            listSize += 1;
        }

        public void removeNode(Node cur) {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            cur.next = null;
            cur.prev = null;
            listSize -= 1;
        }
    }

    Map<Integer, Node> cache;
    Map<Integer, DLL> freqMap;
    final int capacity;
    int curSize;
    int minFreq;

    public LFUCache(int capacity) {
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.curSize = 0;
        this.minFreq = 0;
    }
    
    public int get(int key) {
        Node curNode = cache.get(key);
        
        if (curNode == null) return -1;

        updateNode(curNode);
        return curNode.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            Node curNode = cache.get(key);
            curNode.val = value;
            updateNode(curNode);
        } else {
            curSize += 1;
            if (curSize > capacity) {
                DLL minFreqList = freqMap.get(minFreq);
                cache.remove(minFreqList.last.prev.key);
                minFreqList.removeNode(minFreqList.last.prev);
                curSize--;
            }

            minFreq = 1;
            Node newNode = new Node(key, value);
            DLL minFreqList = freqMap.computeIfAbsent(1,k -> new DLL());
            minFreqList.addNode(newNode);
            cache.put(key, newNode);
        }
    }

    private void updateNode(Node node) {
        int curFreq = node.freq;
        DLL curList = freqMap.get(curFreq);
        curList.removeNode(node);

        if (curFreq == minFreq && curList.listSize == 0) {
            minFreq++;
        }

        node.freq++;

        DLL newList = freqMap.computeIfAbsent(node.freq, k -> new DLL());
        newList.addNode(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */