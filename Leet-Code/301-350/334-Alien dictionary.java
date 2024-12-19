// You have been given a sorted (lexical order) dictionary of an alien language.



// Write a function that returns the order of characters as a string in the alien language. This dictionary will be given to you as an array of strings called 'dictionary', of size 'N'.



// Example :
// If the dictionary consists of the following words:-
// ["caa", "aaa", "aab"], and 'K' is 3.

// Then, the order of the alphabet is -
// ['c', 'a', 'b']
// Note:
// If the language consists of four letters, the four letters should be the starting four letters of the English language. 

// However, their order might differ in the alien language.
// Detailed explanation ( Input/output format, Notes, Images )
// Sample Input 1 :
// 3 1
// a aa aaa
// Sample Output 1 :
// true
// Explanation For Sample Output 1 :
// The words are 'a', 'aa', and 'aaa'. Since the unique character here is 'a', the array to be returned will just be ['a']. 

// The 'true' being printed signifies that the output returned by the function is valid.
// Sample Input 2 :
// 3 3
// caa aaa aab
// Sample Output 2 :
// true
// Constraints :
// 1 ≤ N ≤ 300
// 1 ≤ K ≤ 26
// 1 ≤ Length of words ≤ 50

// Time Limit: 1 sec

import java.util.*;

public class Solution {
    public static String getAlienLanguage(String []dictionary, int k) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();

        for (int i = 0; i < dictionary.length - 1; i++) {
            String curr = dictionary[i];
	        String next = dictionary[i + 1];
	
	        int lim = Math.min(curr.length(), next.length());
            int j = 0;
	        while (j < lim) {
		        char c1 = curr.charAt(j);
		        char c2 = next.charAt(j);

                inDegree.putIfAbsent(c1, 0);

		        if (c1 != c2) {
                    if (graph.containsKey(c1)) {
                        if (!graph.get(c1).contains(c2)) {
                            graph.get(c1).add(c2);
                            inDegree.put(c2, inDegree.getOrDefault(c2, 0) + 1);
                        }
                    } else {
                        Set<Character> set = new HashSet<>();
                        set.add(c2);
                        graph.put(c1, set);
                        inDegree.put(c2, inDegree.getOrDefault(c2, 0) + 1);
                    }
                    break;
		        }
                j++;
	        }

            if (j == lim && curr.length() > next.length()) {
                return "";
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char ch: inDegree.keySet()) {
            if (inDegree.get(ch) == 0) queue.offer(ch);
        }

        StringBuilder res = new StringBuilder();

        while (!queue.isEmpty()) {
            char ch = queue.poll();
            res.append(ch);
        
            if (graph.containsKey(ch)) {
                for (Character vertex: graph.get(ch)) {
                    inDegree.put(vertex, inDegree.get(vertex) - 1);
                    if (inDegree.get(vertex) == 0) {
                        queue.offer(vertex);
                    }
                }
            }

        }

        if (inDegree.size() == res.length()) return res.toString();
        return "";
    }
}