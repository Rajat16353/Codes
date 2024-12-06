// You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.

// Return the maximum possible length of s.

// A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

 

// Example 1:

// Input: arr = ["un","iq","ue"]
// Output: 4
// Explanation: All the valid concatenations are:
// - ""
// - "un"
// - "iq"
// - "ue"
// - "uniq" ("un" + "iq")
// - "ique" ("iq" + "ue")
// Maximum length is 4.
// Example 2:

// Input: arr = ["cha","r","act","ers"]
// Output: 6
// Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
// Example 3:

// Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
// Output: 26
// Explanation: The only string in arr has all 26 characters.
 

// Constraints:

// 1 <= arr.length <= 16
// 1 <= arr[i].length <= 26
// arr[i] contains only lowercase English letters.

class Solution {
    private boolean isUnique(String s, int[] characterSet) {
        int[] wordCheck = new int[26];
        for(char c: s.toCharArray()) {
            if (wordCheck[c - 'a'] == 1) return false;

            wordCheck[c - 'a'] = 1;
        }

        for(char c: s.toCharArray()) {
            if (characterSet[c - 'a'] == 1)  return false;
        }
        return true;
    }

    private void updateCharacterSet(String s, int[] characterSet, int val) {
        for(char c: s.toCharArray()) {
            characterSet[c - 'a'] = val;
        }
    }

    private int maxLength(List<String> arr, int index, int curLen, int[] characterSet) {
        if (index == arr.size()) return curLen;

        if (!isUnique(arr.get(index), characterSet)) {
            return maxLength(arr, index + 1, curLen, characterSet);
        } else {
            updateCharacterSet(arr.get(index), characterSet, 1);
            int pick = maxLength(arr, index + 1, curLen + arr.get(index).length(), characterSet);

            updateCharacterSet(arr.get(index), characterSet, 0);
            int skip = maxLength(arr, index + 1, curLen, characterSet);

            return Math.max(pick, skip);
        }
    }

    public int maxLength(List<String> arr) {
        // int maxLength = 0;
        int[] characterSet = new int[26];

        return maxLength(arr, 0, 0, characterSet);
    }
}