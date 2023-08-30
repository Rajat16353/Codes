// Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

 

// Example 1:


// Input: root = [4,2,6,1,3]
// Output: 1
// Example 2:


// Input: root = [1,0,48,null,null,12,49]
// Output: 1
 

// Constraints:

// The number of nodes in the tree is in the range [2, 104].
// 0 <= Node.val <= 105
 

// Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int getMinimumDifference(TreeNode root) {
        return inorderTraversal(root, null, Integer.MAX_VALUE);
    }
    
    private int inorderTraversal(TreeNode node, TreeNode prev, int minDifference) {
        if (node == null) {
            return minDifference;
        }
        
        minDifference = inorderTraversal(node.left, prev, minDifference);
        
        if (prev != null) {
            minDifference = Math.min(minDifference, node.val - prev.val);
            System.out.println(node.val + " " + prev.val);
        }
        prev = node;
        
        minDifference = inorderTraversal(node.right, prev, minDifference);

        return minDifference;
    }
}