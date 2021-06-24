class Solution:
    def romanToInt(self, s: str) -> int:
        d = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000}
        a = ['I','X','C']
        num = 0
        prev = None
        for x in s:
            if x == 'I':
                num += d[x]
            else:
                if prev in a and d[x] > d[prev]:
                    num += d[x] - 2*d[prev]
                else:
                    num += d[x]
            prev = x
        return num