// Given the root of a binary tree, return all root-to-leaf paths in any order.

// A leaf is a node with no children.

 

// Example 1:


// Input: root = [1,2,3,null,5]
// Output: ["1->2->5","1->3"]
// Example 2:

// Input: root = [1]
// Output: ["1"]
 

// Constraints:

// The number of nodes in the tree is in the range [1, 100].
// -100 <= Node.val <= 100

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
    private void depthFirstSearch(TreeNode root, List<String> path, List<String> paths) {
        path.add("" + root.val);
        if (root.left == null && root.right == null) {
            paths.add(String.join("->", path));
        }
        if (root.left != null) {
            depthFirstSearch(root.left, path, paths);
        }
        if (root.right != null) {
            depthFirstSearch(root.right, path, paths);
        }
        path.remove(path.size() - 1);
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        depthFirstSearch(root, new ArrayList<>(), paths);
        return paths;
    }
}

// Using String
class Solution {
    List<String> paths;

    private void depthFirstSearch(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        if (path.length() != 0) {
            path += "->" + root.val;
        } else {
            path += root.val;
        }

        if (root.left == null && root.right == null) {
            paths.add(path);
            return;
        }
        depthFirstSearch(root.left, path);
        depthFirstSearch(root.right, path);
    }
    public List<String> binaryTreePaths(TreeNode root) {
        paths = new ArrayList<>();
        depthFirstSearch(root, "");
        return paths;
    }
}