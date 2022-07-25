# Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.

# Each letter in magazine can only be used once in ransomNote.

 

# Example 1:

# Input: ransomNote = "a", magazine = "b"
# Output: false
# Example 2:

# Input: ransomNote = "aa", magazine = "ab"
# Output: false
# Example 3:

# Input: ransomNote = "aa", magazine = "aab"
# Output: true
 

# Constraints:

# 1 <= ransomNote.length, magazine.length <= 105
# ransomNote and magazine consist of lowercase English letters.

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        for(int i=0;i<ransomNote.length();i++)
        {
            int t = magazine.indexOf(ransomNote.charAt(i));
            if (t == -1)
                return false;
            String temp = magazine.substring(0,t) + magazine.substring(t+1,magazine.length());
            magazine = temp;
        }
        return true;
    }
}