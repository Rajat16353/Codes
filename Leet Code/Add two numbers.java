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
