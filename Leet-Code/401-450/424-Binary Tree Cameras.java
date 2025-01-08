// You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.

// Return the minimum number of cameras needed to monitor all nodes of the tree.

 

// Example 1:


// Input: root = [0,0,null,0,0]
// Output: 1
// Explanation: One camera is enough to monitor all nodes if placed as shown.
// Example 2:


// Input: root = [0,0,null,0,null,0,null,null,0]
// Output: 2
// Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.
 

// Constraints:

// The number of nodes in the tree is in the range [1, 1000].
// Node.val == 0

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

//  need cameras: -1
//  have camera: 0
//  already covered: 1
class Solution {
    private enum Cameras {
        NEED_CAMERA,
        HAVE_CAMERA,
        NOT_NEEDED
    }
    int cameras;

    public int minCameraCover(TreeNode root) {
        cameras = 0;
        if (backtrack(root) == Cameras.NEED_CAMERA) cameras += 1;
        return cameras;
    }

    private Cameras backtrack(TreeNode root) {
        if (root == null) return Cameras.NOT_NEEDED;

        Cameras lChild = backtrack(root.left);
        Cameras rChild = backtrack(root.right);

        if (lChild == Cameras.NEED_CAMERA || rChild == Cameras.NEED_CAMERA) {
            cameras += 1;
            return Cameras.HAVE_CAMERA;
        }

        if (lChild == Cameras.HAVE_CAMERA || rChild == Cameras.HAVE_CAMERA) {
            return Cameras.NOT_NEEDED;
        }

        return Cameras.NEED_CAMERA;
    }
}