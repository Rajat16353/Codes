// Problem statement
// You are given a binary tree having 'n' nodes.



// The boundary nodes of a binary tree include the nodes from the left and right boundaries and the leaf nodes, each node considered once.



// Figure out the boundary nodes of this binary tree in an Anti-Clockwise direction starting from the root node.



// Example :
// Input: Consider the binary tree A as shown in the figure:

// Output: [10, 5, 3, 7, 18, 25, 20]

// Explanation: As shown in the figure

// The nodes on the left boundary are [10, 5, 3]

// The nodes on the right boundary are [10, 20, 25]

// The leaf nodes are [3, 7, 18, 25].

// Please note that nodes 3 and 25 appear in two places but are considered once.
// Detailed explanation ( Input/output format, Notes, Images )
// Sample Input 1:
// 10 5 20 3 8 18 25 -1 -1 7 -1 -1 -1 -1 -1 -1 -1
// Sample Output 1:
// 10 5 3 7 18 25 20
// Explanation of Sample Input 1:
// The nodes on the left boundary are [10, 5, 3]

// The nodes on the right boundary are [10, 20, 25]

// The leaf nodes are [3, 7, 18, 25].

// Please note that nodes 3 and 25 appear in two places but are considered once.
// Sample Input 2:
// 100 50 150 25 75 140 200 -1 30 70 80 -1 -1 -1 -1 -1 35 -1 -1 -1 -1 -1 -1
// Sample Output 2:
// 100 50 25 30 35 70 80 140 200 150
// Constraints:
// 1 <= n <= 10000

// Where 'n' is the total number of nodes in the binary tree.

// Time Limit: 1 sec

/************************************************************

 Following is the Binary Tree node structure:

 class TreeNode {
     int data;
     TreeNode left;
     TreeNode right;

     TreeNode(int data) {
         this.data = data;
         this.left = null;
         this.right = null;
     }
 }

 ************************************************************/

import java.util.*;

public class Solution {
    static boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    // Function to add the
    // left boundary of the tree
    static void addLeftBoundary(TreeNode root, List<Integer> res) {
        TreeNode curr = root.left;
        while (curr != null) {
            // If the current TreeNode is not a leaf,
            // add its value to the result
            if (!isLeaf(curr)) {
                res.add(curr.data);
            }
            // Move to the left child if it exists,
            // otherwise move to the right child
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    // Function to add the
    // right boundary of the tree
    static void addRightBoundary(TreeNode root, List<Integer> res) {
        TreeNode curr = root.right;
        List<Integer> temp = new ArrayList<>();
        while (curr != null) {
            // If the current TreeNode is not a leaf,
            // add its value to a temporary list
            if (!isLeaf(curr)) {
                temp.add(curr.data);
            }
            // Move to the right child if it exists,
            // otherwise move to the left child
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        // Reverse and add the values from
        // the temporary list to the result
        for (int i = temp.size() - 1; i >= 0; --i) {
            res.add(temp.get(i));
        }
    }

    // Function to add the
    // leaves of the tree
    static void addLeaves(TreeNode root, List<Integer> res) {
        // If the current TreeNode is a
        // leaf, add its value to the result
        if (isLeaf(root)) {
            res.add(root.data);
            return;
        }
        // Recursively add leaves of
        // the left and right subtrees
        if (root.left != null) {
            addLeaves(root.left, res);
        }
        if (root.right != null) {
            addLeaves(root.right, res);
        }
    }

    // Main function to perform the
    // boundary traversal of the binary tree
    public static List<Integer> traverseBoundary(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // If the root is not a leaf,
        // add its value to the result
        if (!isLeaf(root)) {
            res.add(root.data);
        }

        // Add the left boundary, leaves,
        // and right boundary in order
        addLeftBoundary(root, res);
        addLeaves(root, res);
        addRightBoundary(root, res);

        return res;
    }
}