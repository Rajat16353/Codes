// Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

// Note that after backspacing an empty text, the text will continue empty.

 

// Example 1:

// Input: s = "ab#c", t = "ad#c"
// Output: true
// Explanation: Both s and t become "ac".
// Example 2:

// Input: s = "ab##", t = "c#d#"
// Output: true
// Explanation: Both s and t become "".
// Example 3:

// Input: s = "a#c", t = "b"
// Output: false
// Explanation: s becomes "c" while t becomes "b".
 

// Constraints:

// 1 <= s.length, t.length <= 200
// s and t only contain lowercase letters and '#' characters.
 

// Follow up: Can you solve it in O(n) time and O(1) space?

class Solution {
    private void pushToStack(String s, Stack<Character> stack) {
        for (char c: s.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) stack.pop();
            }
        }
    }
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();

        pushToStack(s, sStack);
        pushToStack(t, tStack);

        if (sStack.size() != tStack.size()) {
            return false;
        }

        while(!sStack.isEmpty()) {
            if (sStack.pop() != tStack.pop()) {
                return false;
            }
        }
        return true;
    }
}