// Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

 

// Example 1:


// Input: head = [1,2,3,4,5], left = 2, right = 4
// Output: [1,4,3,2,5]
// Example 2:

// Input: head = [5], left = 1, right = 1
// Output: [5]
 

// Constraints:

// The number of nodes in the list is n.
// 1 <= n <= 500
// -500 <= Node.val <= 500
// 1 <= left <= right <= n
 

// Follow up: Could you do it in one pass?

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

// Using recursion
class Solution {
    private ListNode reverseLinkedList(ListNode node, int position, int left, int right) {
        if (position == left) {
            ListNode prev = null;
            ListNode current = node;
            while (position <= right) {
                ListNode temp = current.next;
                current.next = prev;
                prev = current;
                current = temp;
                position += 1;
            }
            node.next = current;
            return prev;
        } else if (position < left) {
            node.next = reverseLinkedList(node.next, position + 1, left, right);
        }
        return node;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right || head == null || head.next == null) return head;

        return reverseLinkedList(head, 1, left, right);
    }
}

// Using two pointers
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right || head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode current = prev.next;

        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = current.next;
            current.next = nextNode.next;
            nextNode.next = prev.next;
            prev.next = nextNode;
        }

        return dummy.next;
    }
}