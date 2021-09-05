/* You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order, and each of their nodes contains a single digit. 
Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros. */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n,n1 = 0;
        ListNode head = null,temp=null;
        while(l1 != null && l2 != null)
        {
            //System.out.println("l1 : "+l1.val+" l2 : "+l2.val);
            n = l1.val+l2.val + n1;
            ListNode ln = new ListNode(n%10);
            ln.next = null;
            if(head == null)
            {
                head = ln;
                temp = head;
            }
            else
            {
                temp.next = ln;
                temp = temp.next;
            }
            n1 = n/10; 
            l1 = l1.next;
            l2 = l2.next;
        }
        while(l1 == null && l2 != null)
        {
            n = l2.val + n1;
            ListNode ln = new ListNode(n%10);
            n1 = n/10;
            temp.next = ln;
            temp = temp.next;
            l2 = l2.next;
        }
        while(l2 == null && l1 != null)
        {
            n = l1.val + n1;
            ListNode ln = new ListNode(n%10);
            n1 = n/10;
            temp.next = ln;
            temp = temp.next;
            l1 = l1.next;
        }
        while(l1 == null && l2 == null && n1 != 0)
        {
            ListNode ln = new ListNode(n1);
            temp.next = ln;
            temp = temp.next;
            n1 = n1/10;
        }
        return head;
    }
}
