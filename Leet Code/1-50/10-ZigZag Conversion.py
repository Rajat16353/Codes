""" 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
 

Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"
 

Constraints:

1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
 """


class Solution:
    def convert(self, s: str, numRows: int) -> str:
        #level = [0]*len(s)
        #lev = 1
        p = 0
        if numRows != 1:
            p = numRows-2
        n = numRows
        out = ""
        level = [""] * n
        i = 0
        while(i < len(s)):
            t = i
            for j in range(n):
                if j == 0 or j == n-1:
                    level[j] += s[t]
                else:
                    level[j] += s[t]
                    pos = t+2*(n-(t % (n+p))-1)
                    if pos >= len(s):
                        t += 1
                        if t >= len(s):
                            break
                        continue

                    #print(t, pos, s[pos])
                    level[j] += s[pos]
                t += 1
                if t >= len(s):
                    break
            i += n+p
        for x in level:
            # print(x)
            out += x
        return out
