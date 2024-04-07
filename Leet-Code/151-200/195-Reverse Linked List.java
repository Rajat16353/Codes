// Given the head of a singly linked list, reverse the list, and return the reversed list.

 

// Example 1:


// Input: head = [1,2,3,4,5]
// Output: [5,4,3,2,1]
// Example 2:


// Input: head = [1,2]
// Output: [2,1]
// Example 3:

// Input: head = []
// Output: []
 

// Constraints:

// The number of nodes in the list is the range [0, 5000].
// -5000 <= Node.val <= 5000
 

// Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

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
    public ListNode recursiveReverse(ListNode head, ListNode prev) {
		if (head == null ){
			return null;
		}

		if (head.next == null) {
			head.next = prev;
			return head;
		}
		ListNode nextNode = head.next;
		head.next = prev;
		return recursiveReverse(nextNode, head);
	}

    public ListNode manualStackReverse(ListNode head) {
        if (head == null ){
			return null;
		}

        ListNode firstNode = new ListNode(0);
		Stack<ListNode> stack = new Stack<>();
		
		while(head != null) {
			stack.push(head);			
			head = head.next;
			
		}
		head = stack.pop();
		firstNode.next = head;
		while(!stack.isEmpty()) {
			head.next = stack.pop();
			head = head.next;
		}
        head.next = null;
		return firstNode.next;
    }

    public ListNode reverseList(ListNode head) {
        // return recursiveReverse(head, null);
        // return manualStackReverse(head);
        if (head == null || head.next == null){
			return head;
		}
        ListNode prev = head;
        head = head.next;
        prev.next = null;
        while(head.next != null) {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            head = nextNode;
        }
        head.next = prev;
        return head;
    }
}