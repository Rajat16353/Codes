// Problem statement
// You are given a string 'str' and an integer ‘K’. Your task is to find the length of the largest substring with at most ‘K’ distinct characters.

// For example:
// You are given ‘str’ = ‘abbbbbbc’ and ‘K’ = 2, then the substrings that can be formed are [‘abbbbbb’, ‘bbbbbbc’]. Hence the answer is 7.
// Detailed explanation ( Input/output format, Notes, Images )
// Constraints:
// 1 <= T <= 10
// 1 <= K <= 26
// 1 <= |str| <= 10^6

// The string str will contain only lowercase alphabets.    

// Time Limit: 1 sec
// Note:
// You do not need to print anything. It has already been taken care of. Just implement the function.
// Sample Input 1:
// 2
// 2
// abbbbbbc
// 3
// abcddefg
// Sample Output 1:
// 7
// 4
// Explanation:
// For the first test case, ‘str’ = ‘abbbbbbc’ and ‘K’ = 2, then the substrings that can be formed are [‘abbbbbb’, ‘bbbbbbc’]. Hence the answer is 7.

// For the second test case, ‘str’ = ‘abcddefg’ and ‘K’ = 3, then the substrings that can be formed is [‘cdde’, ‘ddef’]. Hence the answer is 4.
// Sample Input 2:
// 2
// 3
// aaaaaaaa
// 1
// abcefg
// Sample Output 2:
// 8   
// 1   


// Hints:
// 1. Try to think of a brute force approach.
// 2. Try to think of a two-pointer solution.

import java.util.*;

public class Solution {

	public static int kDistinctChars(int k, String str) {
		// Write your code here
		int l = 0, r = 0;
		int maxLen = 0;
		Map<Character, Integer> freq = new HashMap<>();

		while (r < str.length()) {
			freq.put(str.charAt(r), freq.getOrDefault(str.charAt(r), 0) + 1);

			if (freq.size() > k) {
				char c = str.charAt(l);
				freq.put(c, freq.get(c) - 1);
				if (freq.get(c) == 0) freq.remove(c);
				l += 1;
			}

			if (freq.size() <= k) {
				maxLen = Math.max(maxLen, r - l + 1);
			}
			r += 1;
		}

		return maxLen;
	}

}
