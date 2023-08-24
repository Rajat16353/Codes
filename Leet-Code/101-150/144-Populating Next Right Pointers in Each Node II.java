// Given a binary tree

// struct Node {
//   int val;
//   Node *left;
//   Node *right;
//   Node *next;
// }
// Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

// Initially, all next pointers are set to NULL.

 

// Example 1:


// Input: root = [1,2,3,4,5,null,7]
// Output: [1,#,2,3,#,4,5,7,#]
// Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
// Example 2:

// Input: root = []
// Output: []
 

// Constraints:

// The number of nodes in the tree is in the range [0, 6000].
// -100 <= Node.val <= 100
 

// Follow-up:

// You may only use constant extra space.
// The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

// Using queue
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        Queue<Pair<Node, Integer>> que = new LinkedList<Pair<Node, Integer>>();
        que.offer(new Pair(root, 0));
        int curLevel = 0;
        while(!que.isEmpty()) {
            if ( que.peek().getKey() == null ) break;
            while( !que.isEmpty() && que.peek().getValue() == curLevel ) {
                Pair<Node, Integer> nodeLevel = que.poll();
                Node tempNode = nodeLevel.getKey();
                int level = nodeLevel.getValue();
                if ( que.peek() == null ) {
                    tempNode.next = null;
                } else if ( level != que.peek().getValue() ) {
                    tempNode.next = null;
                } else {
                    tempNode.next = que.peek().getKey();
                }
                if ( tempNode.left != null ) que.offer(new Pair(tempNode.left, level + 1));
                if ( tempNode.right != null ) que.offer(new Pair(tempNode.right, level + 1));
            }
            curLevel += 1;
        }
        return root;
    }
}