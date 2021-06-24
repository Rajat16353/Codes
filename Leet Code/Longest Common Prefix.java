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