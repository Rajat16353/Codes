/* 
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

 

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 

Constraints:

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
 */

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String pattern = strs[0];
        for (String s: strs) {
            if (s.length() < pattern.length()) {
                pattern = s;
            }
        }
        
        for (int i = 0; i < strs.length && pattern.length() != 0; i++) {
            if (strs[i].startsWith(pattern)) {
                continue;
            }
            while(!pattern.isEmpty()) {
                if (!strs[i].startsWith(pattern))
                    pattern = pattern.substring(0, pattern.length()-1);
                else {
                    break;
                }
            }
        }
        return pattern;
    }
}