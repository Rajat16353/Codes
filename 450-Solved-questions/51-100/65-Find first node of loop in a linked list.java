// Write a function findFirstLoopNode() that checks whether a given Linked List contains a loop. If the loop is present then it returns point to the first node of the loop. Else it returns NULL.

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    static ListNode returnStartingPoint(ListNode loop, ListNode head) {
        ListNode ptr1 = loop;
        ListNode ptr2 = loop;
        
        int k = 1;
        while(ptr2.next != ptr1) {
            ptr2 = ptr2.next;
            k++;
        }
        ptr1 = head;
        ptr2 = head;
        while(k != 0) {
            ptr2 = ptr2.next;
            k--;
        }
        
        while(ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }

    static ListNode findLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return returnStartingPoint(slow, head);
            }
        }
        return null;
    }
    
    public ListNode detectCycle(ListNode head) {
        return findLoop(head);
    }
}