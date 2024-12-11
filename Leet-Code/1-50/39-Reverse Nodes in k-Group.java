// # Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

// # k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

// # You may not alter the values in the list's nodes, only nodes themselves may be changed.

 

// # Example 1:


// # Input: head = [1,2,3,4,5], k = 2
// # Output: [2,1,4,3,5]
// # Example 2:


// # Input: head = [1,2,3,4,5], k = 3
// # Output: [3,2,1,4,5]
 

// # Constraints:

// # The number of nodes in the list is n.
// # 1 <= k <= n <= 5000
// # 0 <= Node.val <= 1000
 

// # Follow-up: Can you solve the problem in O(1) extra memory space?

// # Using stack and queue

// # Definition for singly-linked list.
// # class ListNode:
// #     def __init__(self, val=0, next=None):
// #         self.val = val
// #         self.next = next
// class Solution:
//     def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
//         stack1, queue1 = [], []
//         sz = k
//         l = head
//         while(l != None):
//             stack1.append(l)
//             sz -= 1
//             l = l.next
//             if sz == 0:
//                 temp = stack1.pop()
//                 head = temp

//                 sz += 1
//                 while(sz != k):
//                     temp.next = stack1.pop()
//                     sz += 1
//                     temp = temp.next
//                 temp.next = None
//                 queue1.append([head,temp])
                
//         temp = queue1.pop(0)
//         head = temp[0]
//         prev = temp[1]
//         while(len(queue1) != 0):
//             temp = queue1.pop(0)
//             prev.next = temp[0]
//             prev = temp[1]
            
//         while(len(stack1) != 0):
//             prev.next = stack1.pop(0)
//             prev = prev.next
                
//         return head

// # kth node approach with at least 4 pointers
// # Definition for singly-linked list.
// # class ListNode:
// #     def __init__(self, val=0, next=None):
// #         self.val = val
// #         self.next = next
// class Solution:
//     def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
//         def getKthNode(curr, k):
//             while curr and k>0:
//                 curr = curr.next
//                 k -= 1
//             return curr
//         dummy = ListNode(0, head)
//         groupPrev = dummy
        
//         while True:
//             kth = getKthNode(groupPrev, k)
//             if not kth:
//                 break
//             groupNext = kth.next
        
//             prev, curr = kth.next, groupPrev.next 
//             while curr != groupNext:
//                 temp = curr.next
//                 curr.next = prev
//                 prev = curr
//                 curr = temp

//             temp = groupPrev.next
//             groupPrev.next = kth
//             groupPrev = temp

//         return dummy.next
        
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;

        return newHead;
    }

    private ListNode getKthNode(ListNode node, int k) {
        k -= 1;
        while (node != null && k > 0) {
            k--;
            node = node.next;
        }

        return node;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode temp = head, prevNode = null;

        while (temp != null) {
            ListNode kthNode = getKthNode(temp, k);

            if (kthNode == null) {
                if (prevNode != null) {
                    prevNode.next = temp;
                }
                break;
            }

            ListNode nextNode = kthNode.next;
            kthNode.next = null;

            reverse(temp);

            if (temp == head) {
                head = kthNode;
            } else {
                prevNode.next = kthNode;
            }

            prevNode = temp;
            temp = nextNode;
        }

        return head;
    }
}