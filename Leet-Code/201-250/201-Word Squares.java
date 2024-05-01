// Problem statement
// Given a set of words without duplicates, find all word squares you can build from them.

// A sequence of words forms a valid word square if the Kth row and column read the exact same string, where 0 ≤ K < max(NUM_ROWS, NUM_COLUMNS). You can return word square in any order.

// Note:

// 1) All words will have the exact same length.
// 2) Each word contains only lowercase English alphabet a-z.
// Example:

// The word sequence ["ball", "area", "lead", "lady"] forms a word square because each word reads the same both horizontally and vertically i.e.
// b a l l
// a r e a
// l e a d
// l a d y
// Detailed explanation ( Input/output format, Notes, Images )
// Constraints:
// 1 <= T <= 10
// 1 <= N <= 10^3
// 1 <= |WORD| <= 5

// Time Limit: 1 sec
// Sample Input 1:
// 1
// 5
// wall ball lead lady area  
// Sample Output 1:
// wall
// area
// lead
// lady

// ball
// area
// lead
// lady
// Explanation 1:
// For the first test case, 
// The output consists of two word squares. As it is evident from the 
// output that the kth row and the kth column contain the same 
// word.The order of output does not matter just the order of words 
// in each word square matters.
// Sample Input 2:
// 1
// 4
// abat baba atan atal
// Sample Output 2:
//  baba
//  abat
//  baba
//  atan

//  baba
//  abat
//  baba
//  atal
// Explanation 2:
// For the first test case, 
// The output consists of two word squares. As it is evident from the 
// output that the kth row and the kth column contain the same 
// word. The order of output does not matter just the order of words 
// in each word square matters.


// By creating a map containing all the possible prefixes for the array and then backtracking
import java.util.* ;
import java.io.*;

public class Solution {
    public static ArrayList<ArrayList<String>> wordSquares(ArrayList<String> arr) {
        // Write your code here.
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Map<String, List<String>> prefixMap = new HashMap<>();
        createPrefixMap(arr, prefixMap);

        for (String word: arr) {
            LinkedList<String> list = new LinkedList<>();
            list.add(word);
            backTrack(1, list, result, word.length(), prefixMap);
        }

        return result;
    }

    private static void backTrack(int step, LinkedList<String> list, ArrayList<ArrayList<String>> result, int size, Map<String, List<String>> prefixMap) {
        if (size == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        StringBuilder prefix = new StringBuilder();

        for (String word: list) {
            prefix.append(word.charAt(step));
        }
        
        List<String> prefixList = prefixMap.getOrDefault(prefix.toString(), new ArrayList<>());
        for (String word: prefixList) {
            list.add(word);
            backTrack(step + 1, list, result, size, prefixMap);
            list.removeLast();
        }
    }

    private static void createPrefixMap(ArrayList<String> words, Map<String, List<String>> prefixMap) {
        for(String word: words) {
            for(int i = 0; i < word.length(); i++) {
                String prefix = word.substring(0, i);
                prefixMap.putIfAbsent(prefix, new ArrayList<>());
                List<String> list = prefixMap.get(prefix);
                list.add(word);
            }
        }
    }
}


// By creating Trie and then backtracking
import java.util.* ;
import java.io.*; 
import java.util.ArrayList;


public class Solution {
    static class TrieNode {
        private HashMap<Character, TrieNode> children;
        private List<Integer> indices;
        private boolean isWord;

        TrieNode (){
            this.children = new HashMap<>();
            this.indices = new ArrayList<>();
            this.isWord = false;
        }
        private HashMap<Character, TrieNode> getChildren() {
            return this.children;
        }

        private void setEndOfWord(Boolean isWord) {
            this.isWord = isWord;
        }
    }

    private  static void insert(String word, TrieNode root, int index) {
        TrieNode current = root;

        for(char c: word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(c, k -> new TrieNode());
            current.indices.add(index);
        }

        current.setEndOfWord(true);
    }

    private static List<Integer> searchTrie(String prefix, TrieNode root) {
        TrieNode current = root;

        for (char c: prefix.toCharArray()) {
            current = current.getChildren().get(c);
            if (current == null) {
                return new ArrayList<>();
            }
        }
        if (current.indices != null) {
            return current.indices;
        }
        return new ArrayList<>();
    }

    private static void backTrack(LinkedList<String> list, ArrayList<ArrayList<String>> result, TrieNode root, ArrayList<String> words, int size) {
        if (size == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        StringBuilder prefix = new StringBuilder();
        int step = list.size();
        for (String word: list) {
            prefix.append(word.charAt(step));
        }

        List<Integer> prefixList = searchTrie(prefix.toString(), root);
        for (int i: prefixList) {
            list.add(words.get(i));
            backTrack(list, result, root, words, size);
            list.removeLast();
        }
    }

    public static ArrayList<ArrayList<String>> wordSquares(ArrayList<String> arr) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        TrieNode root = new TrieNode();

        for (int i = 0; i < arr.size(); i++) {
            insert(arr.get(i), root, i);
        }

        // System.out.println(root.getChildren().get('l').indices.toString());
        // System.out.println(root.getChildren().toString());

        for (String word: arr) {
            LinkedList<String> list = new LinkedList<>();
            list.add(word);
            backTrack(list, result, root, arr, word.length());
        }

        return result;
    }
}