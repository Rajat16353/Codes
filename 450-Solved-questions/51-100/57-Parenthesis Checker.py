# Given an expression string x. Examine whether the pairs and the orders of “{“,”}”,”(“,”)”,”[“,”]” are correct in exp.
# For example, the function should return 'true' for exp = “[()]{}{[()()]()}” and 'false' for exp = “[(])”.

# Example 1:

# Input:
# {([])}
# Output: 
# true
# Explanation: 
# { ( [ ] ) }. Same colored brackets can form 
# balaced pairs, with 0 number of 
# unbalanced bracket.
# Example 2:

# Input: 
# ()
# Output: 
# true
# Explanation: 
# (). Same bracket can form balanced pairs, 
# and here only 1 type of bracket is 
# present and in balanced way.
# Example 3:

# Input: 
# ([]
# Output: 
# false
# Explanation: 
# ([]. Here square bracket is balanced but 
# the small bracket is not balanced and 
# Hence , the output will be unbalanced.
# Your Task:
# This is a function problem. You only need to complete the function ispar() that takes a string as a parameter and returns a boolean value true if brackets are balanced else returns false. The printing is done automatically by the driver code.

# Expected Time Complexity: O(|x|)
# Expected Auixilliary Space: O(|x|)

# Constraints:
# 1 ≤ |x| ≤ 32000

# Note: The drive code prints "balanced" if function return true, otherwise it prints "not balanced".


#User function Template for python3

class Solution:
    
    #Function to check if brackets are balanced or not.
    def ispar(self,x):
        # code here
        stack_list = []
        d = {'}': '{', ')': '(', ']': '['}
        for c in x:
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

#{ 
 # Driver Code Starts
#Initial Template for Python 3

import atexit
import io
import sys

#Contributed by : Nagendra Jha


_INPUT_LINES = sys.stdin.read().splitlines()
input = iter(_INPUT_LINES).__next__
_OUTPUT_BUFFER = io.StringIO()
sys.stdout = _OUTPUT_BUFFER

@atexit.register

def write():
    sys.__stdout__.write(_OUTPUT_BUFFER.getvalue())


if __name__ == '__main__':
    test_cases = int(input())
    for cases in range(test_cases) :
        #n = int(input())
        #n,k = map(int,imput().strip().split())
        #a = list(map(int,input().strip().split()))
        s = str(input())
        obj = Solution()
        if obj.ispar(s):
            print("balanced")
        else:
            print("not balanced")
# } Driver Code Ends