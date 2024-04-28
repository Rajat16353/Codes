// Given the root of a complete binary tree, return the number of the nodes in the tree.

// According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

// Design an algorithm that runs in less than O(n) time complexity.

 

// Example 1:


// Input: root = [1,2,3,4,5,6]
// Output: 6
// Example 2:

// Input: root = []
// Output: 0
// Example 3:

// Input: root = [1]
// Output: 1
 

// Constraints:

// The number of nodes in the tree is in the range [0, 5 * 104].
// 0 <= Node.val <= 5 * 104
// The tree is guaranteed to be complete.

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

// Time complexity O(n)
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int noOfNodes = 0;
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            noOfNodes += size;
            while(size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return noOfNodes;
    }
}

// Time complexity O(log(n)^2)
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
    private int calculateLeftHeight(TreeNode root) {
        int height = 0;
        while(root.left != null) {
            height += 1;
            root = root.left;
        }
        return height;
    }

    private int calculateRightHeight(TreeNode root) {
        int height = 0;
        while(root.right != null) {
            height += 1;
            root = root.right;
        }
        return height;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = calculateLeftHeight(root);
        int right = calculateRightHeight(root);

        if (left == right) {
            return ((2<<(left)) -1);
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
}