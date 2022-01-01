# Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.

 

# Example 1:


# Input: root = [5,3,6,2,4,null,7], k = 9
# Output: true
# Example 2:


# Input: root = [5,3,6,2,4,null,7], k = 28
# Output: false
 

# Constraints:

# The number of nodes in the tree is in the range [1, 104].
# -104 <= Node.val <= 104
# root is guaranteed to be a valid binary search tree.
# -105 <= k <= 105

# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# First approach
class Solution:
    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        visited = set()
        queue = []
        queue.append(root)
        visited.add(root.val)
        
        while queue:
            n = queue.pop(0)
            diff = k - n.val
            if diff in visited and diff != n.val:
                return True
            if n.left != None:
                if n.left.val not in visited:
                    visited.add(n.left.val)
                    queue.append(n.left)
            if n.right != None:
                if n.right.val not in visited:
                    queue.append(n.right)
                    visited.add(n.right.val)
        return False

# Second approach
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def dfs(self, root, k, visited) -> bool:
        if root == None:
            return False
        if k - root.val in visited:
            return True
        visited.add(root.val)
        return self.dfs(root.left, k, visited) or self.dfs(root.right, k, visited)
    
    def findTarget(self, root: Optional[TreeNode], k: int) -> bool:
        visited = set()
        return self.dfs(root, k, visited)  