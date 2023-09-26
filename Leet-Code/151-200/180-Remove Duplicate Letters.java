// Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is 
// the smallest in lexicographical order
//  among all possible results.

 

// Example 1:

// Input: s = "bcabc"
// Output: "abc"
// Example 2:

// Input: s = "cbacdcbc"
// Output: "acdb"
 

// Constraints:

// 1 <= s.length <= 104
// s consists of lowercase English letters.

public class Solution {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> seen = new HashSet<>();
        Map<Character, Integer> lastOcc = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOcc.put(s.charAt(i), i);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && i < lastOcc.get(stack.peek())) {
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }
}