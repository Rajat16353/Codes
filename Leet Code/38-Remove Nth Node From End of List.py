# Given the head of a linked list, remove the nth node from the end of the list and return its head.

 

# Example 1:


# Input: head = [1,2,3,4,5], n = 2
# Output: [1,2,3,5]
# Example 2:

# Input: head = [1], n = 1
# Output: []
# Example 3:

# Input: head = [1,2], n = 1
# Output: [1]
 

# Constraints:

# The number of nodes in the list is sz.
# 1 <= sz <= 30
# 0 <= Node.val <= 100
# 1 <= n <= sz
 

# Follow up: Could you do this in one pass?

# Using stack - faster

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        temp = head;
        stack1 = []
        while(temp != None):
            stack1.append(temp)
            temp = temp.next
        
        while(n > 0):
            temp = stack1.pop()
            n -= 1
            
        if len(stack1) == 0:
            return head.next
        else:
            prev = stack1.pop()
            prev.next = temp.next
            
        return head
        
# Using two pointers

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        dummy = ListNode(0, head)
        l = dummy
        r = head
        
        while n > 0 and r:
            r = r.next
            n -= 1
            
        while(r):
            l = l.next
            r = r.next
            
        l.next = l.next.next
        
        return dummy.next