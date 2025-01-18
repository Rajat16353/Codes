// A string s is called good if there are no two different characters in s that have the same frequency.

// Given a string s, return the minimum number of characters you need to delete to make s good.

// The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

 

// Example 1:

// Input: s = "aab"
// Output: 0
// Explanation: s is already good.
// Example 2:

// Input: s = "aaabbbcc"
// Output: 2
// Explanation: You can delete two 'b's resulting in the good string "aaabcc".
// Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
// Example 3:

// Input: s = "ceabaacb"
// Output: 2
// Explanation: You can delete both 'c's resulting in the good string "eabaab".
// Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 

// Constraints:

// 1 <= s.length <= 105
// s contains only lowercase English letters.

// Using HashMap and HashSet
class Solution {
    public int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (char c: s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        int deleteChars = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (set.contains(val)) {
                val--;
                while (val != 0) {
                    if (set.contains(val)) {
                        val--;
                        continue;
                    }
                    set.add(val);
                    break;
                }
                deleteChars += (entry.getValue() - val);
            } else {
                set.add(val);
            }
        }

        return deleteChars;
    }
}

class Solution {
    public int minDeletions(String s) {
        int[] freqMap = new int[26];
        for (char c: s.toCharArray()) {
            freqMap[c - 'a'] += 1;
        }

        Arrays.sort(freqMap);
        int deletions = 0;

        for (int i = 24; i >= 0; i--) {
            if (freqMap[i] == 0) {
                break;
            }

            if (freqMap[i] >= freqMap[i + 1]) {
                int prev = freqMap[i];
                freqMap[i] = Math.max(0, freqMap[i + 1] - 1);
                deletions += prev - freqMap[i];
            }
        }

        return deletions;
    }
}