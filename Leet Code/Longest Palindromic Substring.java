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