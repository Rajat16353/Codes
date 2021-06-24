class Solution:
    def myAtoi(self, str: str) -> int:
        str = str.lstrip()
        num = ""
        if str == "":
            return 0
        elif not ord(str[0]) in range(48, 58):
            if str[0] == '-' or str[0] == '+':
                
                for x in str[1:]:
                    if not ord(x) in range(48, 58):
                        break
                    else:
                        num += x
                
                if num == "":
                    return 0
                else:
                    num = str[0] + num
            else:
                return 0
        else:
            for x in str:
                if not ord(x) in range(48, 58):
                    break
                else:
                    num += x
            if num == "":
                return 0
        if int(num) < pow(-2,31):
            return pow(-2,31)
        elif int(num) > pow(2,31)-1:
            return pow(2,31)-1
        return int(num)