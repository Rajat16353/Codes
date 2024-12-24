/*
Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"
*/
class Solution {
    public String longestPalindrome(String s) {
        String res = "";
        int resLen = 0;
        for(int i=0; i<s.length(); i++){
            int l = i,r = i;
            while(l>=0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if ((r-l+1) > resLen){
                    res = s.substring(l,r+1);
                    resLen = r-l+1;
                }
                l -= 1;
                r += 1;
            }
            
            //even length
            l = i;
            r = i+1;
            while(l>=0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if ((r-l+1) > resLen){
                    res = s.substring(l,r+1);
                    resLen = r-l+1;
                }
                l -= 1;
                r += 1;
            }
        }
        //System.out.println(res)a;
        
        return res;
    }
}

// Using Dynamic programming
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        int maxLength = 1;
        int pl = 0, pr = 0;
        int l = 0, r = 1;

        while (r < n) {
            if (s.charAt(l) == s.charAt(r)) {
                maxLength = 2;
                pl = l;
                pr = r;
                dp[l][r] = 1;
            } else {
                dp[l][r] = 0;
            }
            l++;
            r++;
        }

        int diff = 2;
        for (int i = 0; i < n && diff < n; diff++) {
            for (int j = i; j < n; j++) {
                l = j;
                r = j + diff;
                if (r >= n) break;
                if (s.charAt(l) == s.charAt(r) && dp[l + 1][r - 1] == 1) {
                    dp[l][r] = 1;
                    if (maxLength < r - l + 1) {
                        maxLength = r - l + 1;
                        pl = l;
                        pr = r;
                    }
                } else {
                    dp[l][r] = 0;
                }
            }
        }
        return s.substring(pl, pr + 1);
    }
}