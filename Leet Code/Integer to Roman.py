class Solution:
    def intToRoman(self, num: int) -> str:
        out = ""
        d = {1:'I',4:'IV', 5:'V',9:'IX', 10:'X',40:'XL', 50:'L',90:'XC', 100:'C',400:'CD', 500:'D',900:'CM', 1000:'M'}
        t = list(d.keys())
        while(num != 0):
            if num in d:
                #print(num)
                out += d[num]
                return out
            else:
                #print(num)
                i = 0
                while (num > t[i]):
                    i += 1
                    if i == len(t):
                        break
                i -= 1
                if (t[i] != 1):
                    num = num - t[i]
                    out += d[t[i]]
                else:
                    out += d[t[i]]*num
                    num = 0
        #print(out)
        return out