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
                    pos = t+2*(n-(t%(n+p))-1)
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
            #print(x)
            out += x
        return out