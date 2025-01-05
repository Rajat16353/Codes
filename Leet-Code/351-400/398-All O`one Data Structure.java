// Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

// Implement the AllOne class:

// AllOne() Initializes the object of the data structure.
// inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
// dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
// getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
// getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
// Note that each function must run in O(1) average time complexity.

 

// Example 1:

// Input
// ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
// [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
// Output
// [null, null, null, "hello", "hello", null, "hello", "leet"]

// Explanation
// AllOne allOne = new AllOne();
// allOne.inc("hello");
// allOne.inc("hello");
// allOne.getMaxKey(); // return "hello"
// allOne.getMinKey(); // return "hello"
// allOne.inc("leet");
// allOne.getMaxKey(); // return "hello"
// allOne.getMinKey(); // return "leet"
 

// Constraints:

// 1 <= key.length <= 10
// key consists of lowercase English letters.
// It is guaranteed that for each call to dec, key is existing in the data structure.
// At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.

class AllOne {
    private class Node {
        Set<String> keys;
        int freq;
        Node prev;
        Node next;

        public Node (String key, int freq) {
            this.keys = new HashSet<>();
            this.keys.add(key);

            this.freq = freq;
        }
    }

    private class DLL {
        Node head;
        Node tail;

        public DLL () {
            this.head = getNewNode("", -1);
            this.tail = getNewNode("", -1);
            this.head.next = tail;
            this.tail.prev = head;
        }

        private Node getNewNode(String key, int freq) {
            return new Node(key, freq);
        }

        public Node addNewNode(Node curr, String key, int freq) {
            if (curr.next.freq == 1 + freq) {
                curr.next.keys.add(key);
            } else {
                addRightNode(curr, key, freq + 1);
            }
            curr.keys.remove(key);
            Node nextNode = curr.next;
            if (curr.keys.isEmpty()) removeNode(curr);
            return nextNode;
        }

        private void addRightNode(Node curr, String key, int freq) {
            Node newNode = new Node(key, freq);
            curr.next.prev = newNode;
            newNode.next = curr.next;
            curr.next = newNode;
            newNode.prev = curr;
        }

        public Node removeKey(Node curr, String key) {
            curr.keys.remove(key);
            int freq = curr.freq - 1;

            if (curr.prev.freq == freq) {
                curr.prev.keys.add(key);
            } else if (freq != 0) {
                addRightNode(curr.prev, key, freq);
            }

            Node prevNode = curr.prev;

            if (curr.keys.isEmpty()) {
                removeNode(curr);
            }

            return prevNode == head ? null : prevNode;
        }

        private void removeNode(Node curr) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            curr.next = null;
            curr.prev = null;
        }
    }

    Map<String, Node> keyStore;
    DLL list;

    public AllOne() {
        keyStore = new HashMap();
        list = new DLL();
    }
    
    public void inc(String key) {
        if (keyStore.containsKey(key)) {
            Node last = keyStore.remove(key);
            
            
            keyStore.put(key, list.addNewNode(last, key, last.freq));
        } else {
            keyStore.put(key, list.addNewNode(list.head, key, 0));
        }
    }
    
    public void dec(String key) {
        Node curr = keyStore.get(key);

        Node newPos = list.removeKey(curr, key);
        if (newPos == null) keyStore.remove(key);
        else keyStore.put(key, newPos);
    }
    
    public String getMaxKey() {
        return list.tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        return list.head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */