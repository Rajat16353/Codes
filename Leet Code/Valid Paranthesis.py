class Solution:
    def isValid(self, s: str) -> bool:
        stack_list = []
        d = {'}':'{', ')':'(', ']':'['}
        for c in s:
            if c not in d.keys():
                stack_list.append(c)
            else:
                if len(stack_list) > 0:
                    temp = stack_list.pop()
                else:
                    return False
                if d[c] != temp:
                    return False
        if len(stack_list) != 0:
            return False
        return True