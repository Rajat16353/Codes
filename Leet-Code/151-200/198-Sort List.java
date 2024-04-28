// Given the head of a linked list, return the list after sorting it in ascending order.

 

// Example 1:


// Input: head = [4,2,1,3]
// Output: [1,2,3,4]
// Example 2:


// Input: head = [-1,5,3,4,0]
// Output: [-1,0,3,4,5]
// Example 3:

// Input: head = []
// Output: []
 

// Constraints:

// The number of nodes in the list is in the range [0, 5 * 104].
// -105 <= Node.val <= 105
 

// Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

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
    private ListNode mergeSort(ListNode left, ListNode right) {
        ListNode result = null;
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (left.val <= right.val) {
            result = left;
            result.next = mergeSort(left.next, right);
        } else {
            result = right;
            result.next = mergeSort(left, right.next);
        }

        return result;
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode divideList(ListNode head) {
        if (head == null || head.next == null) {
             return head;
        }

        ListNode mid = findMid(head);
        ListNode nextOfMid = mid.next;
        mid.next = null;

        ListNode left = divideList(head);
        ListNode right = divideList(nextOfMid);

        return mergeSort(left, right);
    }

    public ListNode sortList(ListNode head) {
        return divideList(head);
    }
}