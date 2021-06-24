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