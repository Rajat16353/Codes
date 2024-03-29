// Design a HashMap without using any built-in hash table libraries.

// Implement the MyHashMap class:

// MyHashMap() initializes the object with an empty map.
// void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
// int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
// void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 

// Example 1:

// Input
// ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
// [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
// Output
// [null, null, null, 1, -1, null, 1, null, -1]

// Explanation
// MyHashMap myHashMap = new MyHashMap();
// myHashMap.put(1, 1); // The map is now [[1,1]]
// myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
// myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
// myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
// myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
// myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
// myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
// myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 

// Constraints:

// 0 <= key, value <= 106
// At most 104 calls will be made to put, get, and remove.

class MyHashMap {
    LinkedList<Entry>[] map;
    static final int SIZE = 769;

    public MyHashMap() {
        map = new LinkedList[SIZE];
    }
    
    public void put(int key, int value) {
        int bucket = key % SIZE;
        if (map[bucket] == null) {
            map[bucket] = new LinkedList<>();
            map[bucket].add(new Entry(key, value));
        } else {
            for(Entry entry: map[bucket]) {
                if (entry.key == key) {
                    entry.value = value;
                    return;
                }
            }
            map[bucket].add(new Entry(key, value));
        }
    }
    
    public int get(int key) {
        int bucket = key % SIZE;
        if (map[bucket] == null) {
            return -1;
        } else {
            for(Entry entry: map[bucket]) {
                if (entry.key == key) {
                    return entry.value;
                }
            }
            return -1;
        }
    }
    
    public void remove(int key) {
        int bucket = key % SIZE;
        Entry toRemove = null;
        if (map[bucket] == null) {
            return;
        }
        for(Entry entry: map[bucket]) {
                if (entry.key == key) {
                    toRemove = entry;
                }
        }
        if (toRemove == null) return;
        map[bucket].remove(toRemove);
        
    }
}

class Entry {
    int key;
    int value;
    
    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */