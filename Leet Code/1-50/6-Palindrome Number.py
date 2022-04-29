""" 
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.

 

Example 1:

Input: x = 121
Output: true
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Example 4:

Input: x = -101
Output: false
 

Constraints:

-231 <= x <= 231 - 1
 """


class Solution:
    def isPalindrome(self, x: int) -> bool:
        s = str(x)
        #i = 0
        #j = len(s)-1
        '''while(i < j):
            if s[i] != s[j]:
                break
            i += 1
            j -= 1
        if i >= j:
            return True
        else:
            return False'''
        '''n = len(s)
        #print(s[:n//2],s[:n//2:-1])
        if n%2 == 0:
            if s[:n//2] == s[:n//2-1:-1]:
                return True
            return False
        else:
            if s[:n//2] == s[:n//2:-1]:
                return True
            return False'''
        if s == s[::-1]:
            return True
        else:
            return False
