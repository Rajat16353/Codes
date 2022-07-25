# Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

# Example 1:

# Input: n = 3
# Output: ["((()))","(()())","(())()","()(())","()()()"]
# Example 2:

# Input: n = 1
# Output: ["()"]
 

# Constraints:

# 1 <= n <= 8

class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        stack = []
        res = []
        
        def backtrack(openP, closeP):
            if openP == closeP == n:
                res.append("".join(stack))
                return 
            
            if openP < n:
                stack.append("(")
                backtrack(openP+1, closeP)
                stack.pop()
            if closeP < openP:
                stack.append(")")
                backtrack(openP, closeP+1)
                stack.pop()
                
        backtrack(0,0)
        return res