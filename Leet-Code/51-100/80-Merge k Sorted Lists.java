// You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

// Merge all the linked-lists into one sorted linked-list and return it.

 

// Example 1:

// Input: lists = [[1,4,5],[1,3,4],[2,6]]
// Output: [1,1,2,3,4,4,5,6]
// Explanation: The linked-lists are:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// merging them into one sorted list:
// 1->1->2->3->4->4->5->6
// Example 2:

// Input: lists = []
// Output: []
// Example 3:

// Input: lists = [[]]
// Output: []
 

// Constraints:

// k == lists.length
// 0 <= k <= 104
// 0 <= lists[i].length <= 500
// -104 <= lists[i][j] <= 104
// lists[i] is sorted in ascending order.
// The sum of lists[i].length will not exceed 104.

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
    private ListNode merge2List(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        if (l1 != null) {
            curr.next = l1;
        }
        
        else if (l2 != null) {
            curr.next = l2;
        }
        
        return dummy.next;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || lists == null) {
            return null;
        }
        List<ListNode> list = Arrays.asList(lists);
        while (list.size() > 1) {
            List<ListNode> mergedLists = new ArrayList<>();
            for (int i =0; i<list.size(); i += 2) {
                ListNode l1 = list.get(i);
                ListNode l2 = i+1 < list.size() ? list.get(i+1): null;

                mergedLists.add(merge2List(l1, l2));
            }
            list = mergedLists;
        }
        return list.get(0);
    }
}

// Using min heap

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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode head = new ListNode();
        ListNode temp = new ListNode();
        head = temp;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new ListNodeComparator());

        for(ListNode node: lists) {
            if (node != null) minHeap.offer(node);
        }

        while (!minHeap.isEmpty()) {
            temp.next = minHeap.poll();
            temp = temp.next;
            if (temp.next != null) minHeap.offer(temp.next);
        }

        return head.next;
    }

    private class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode a, ListNode b) {
            return Integer.compare(a.val, b.val);
        }
    }

}