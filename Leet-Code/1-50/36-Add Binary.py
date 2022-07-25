# Given two binary strings a and b, return their sum as a binary string.

 

# Example 1:

# Input: a = "11", b = "1"
# Output: "100"
# Example 2:

# Input: a = "1010", b = "1011"
# Output: "10101"
 

# Constraints:

# 1 <= a.length, b.length <= 104
# a and b consist only of '0' or '1' characters.
# Each string does not contain leading zeros except for the zero itself.

# First approach
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        out = ""
        carry = 0
        i = len(a)-1
        j = len(b)-1
        while(i > -1 and j > -1):
            if a[i] == "1" and b[j] == "1":
                if carry == 0:
                    out = "0" + out
                    carry = 1
                else:
                    out = "1" + out
                    carry = 1
            elif a[i] == "1" or b[j] == "1":
                if carry == 0:
                    out = "1" + out
                else:
                    out = "0" + out
                    carry = 1
            else:
                if carry == 0:
                    out = "0" + out
                else:
                    out = "1" + out
                    carry = 0
                carry = 0
            print(a[i], b[j], out)
            i -= 1
            j -= 1
        
        while(i>-1):
            if carry == 1:
                if a[i] == "1":
                    out = "0" + out
                    carry = 1
                else:
                    out = "1" + out
                    carry = 0
            else:
                out = a[i] + out
            i -= 1
            
        while(j>-1):
            if carry == 1:
                if b[j] == "1":
                    out = "0" + out
                    carry = 1
                else:
                    out = "1" + out
                    carry = 0
            else:
                out = b[j] + out
            j -= 1
        if carry == 1:
            out = "1"+ out
        return out

# Second approach
class Solution:
    def addBinary(self, a: str, b: str) -> str:
        out = ""
        carry = 0
        aL, bL = -len(a), -len(b)
        i = -1
        while(i >= aL or i >= bL):
            aBit = int(a[i]) if i >= aL else 0
            bBit = int(b[i]) if i >= bL else 0
            
            summ = aBit + bBit + carry
            out = str(summ % 2) + out
            carry = 1 if summ == 2 else summ//2
            i -= 1
            
        return "1"+out if carry == 1 else out