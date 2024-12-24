// The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.

// Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.

// Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.

 

// Example 1:


// Input: root = [3,2,3,null,3,null,1]
// Output: 7
// Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
// Example 2:


// Input: root = [3,4,5,1,3,null,1]
// Output: 9
// Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 

// Constraints:

// The number of nodes in the tree is in the range [1, 104].
// 0 <= Node.val <= 104

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
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> dp = new HashMap<>();
        return recursion(root, dp);
    }

    private int recursion(TreeNode current, Map<TreeNode, Integer> dp) {
        if (current == null) return 0;

        if (dp.containsKey(current)) return dp.get(current);
        int steal = current.val;
        if (current.left != null && current.left.left != null) steal += recursion(current.left.left, dp);
        if (current.left != null && current.left.right != null) steal += recursion(current.left.right, dp);
        if (current.right != null && current.right.left != null) steal += recursion(current.right.left, dp);
        if (current.right != null && current.right.right != null) steal += recursion(current.right.right, dp);

        int notSteal = 0;
        if (current.left != null) notSteal += recursion(current.left, dp);
        if (current.right != null) notSteal += recursion(current.right, dp);

        dp.put(current, Math.max(steal, notSteal));
        return dp.get(current);
    }
}

// without DP
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
    public int rob(TreeNode root) {
        int[] ans = recursion(root);
        return Math.max(ans[0], ans[1]);
    }

    private int[] recursion(TreeNode current) {
        if (current == null) return new int[2];

        int[] left = recursion(current.left);
        int[] right = recursion(current.right);
        int[] result = new int[2];

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = current.val + left[0] + right[0];

        return result;
    }
}