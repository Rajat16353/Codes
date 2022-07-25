// A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

// For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
// Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 

// Example 1:

// Input: s = "25525511135"
// Output: ["255.255.11.135","255.255.111.35"]
// Example 2:

// Input: s = "0000"
// Output: ["0.0.0.0"]
// Example 3:

// Input: s = "101023"
// Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 

// Constraints:

// 1 <= s.length <= 20
// s consists of digits only.

class Solution {
    private void backtrack(String s, int i, int dots, String curIp, List<String> result, int l) {
        if (dots == 4 && i == l) {
            result.add(curIp.substring(0, curIp.length()-1));
            return;
        }
        
        if (dots > 4)
            return;
        
        for(int j = i; j<Math.min(i+3, l); j++) {
            if (Integer.parseInt(s.substring(i, j+1)) < 256 && (i == j || s.charAt(i) != '0'))
                backtrack(s, j+1, dots+1, curIp+s.substring(i, j+1)+".", result, l);
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int l = s.length();
        if (l > 12)
            return result;
        
        backtrack(s, 0, 0, "", result, l);
        
        return result;
    }
}