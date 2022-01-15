# Given an array of integers arr, you are initially positioned at the first index of the array.

# In one step you can jump from index i to index:

# i + 1 where: i + 1 < arr.length.
# i - 1 where: i - 1 >= 0.
# j where: arr[i] == arr[j] and i != j.
# Return the minimum number of steps to reach the last index of the array.

# Notice that you can not jump outside of the array at any time.

 

# Example 1:

# Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
# Output: 3
# Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
# Example 2:

# Input: arr = [7]
# Output: 0
# Explanation: Start index is the last index. You do not need to jump.
# Example 3:

# Input: arr = [7,6,9,6,9,6,9,7]
# Output: 1
# Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

# Constraints:

# 1 <= arr.length <= 5 * 104
# -108 <= arr[i] <= 108

class Solution:
    def minJumps(self, arr: List[int]) -> int:
        if len(arr) == 1:
            return 0
        step = 0
        d = {}
        l = len(arr)
        for i, n in enumerate(arr):
            if n not in d.keys():
                d[n] = []
                d[n].append(i)
            else:
                d[n].append(i)
                
        
        queue = [0]
        while queue:
            step += 1
            
            size = len(queue)
            
            for i in range(size):
                j = queue.pop(0)
                if j-1 > 0 and arr[j-1] in d.keys():
                    queue.append(j-1)
                
                if j+1 < l and arr[j+1] in d.keys():
                    if j+1 == l-1:
                        return step
                    queue.append(j+1)
                    
                if arr[j] in d.keys():
                    for k in d[arr[j]]:
                        if k != j:
                            if k == l-1:
                                return step
                            queue.append(k)
                            
                if arr[j] in d.keys():
                    del d[arr[j]]
        return step