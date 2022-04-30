// Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum. Each path should be returned as a list of the node values, not node references.

// A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.

 

// Example 1:


// Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
// Output: [[5,4,11,2],[5,8,4,5]]
// Explanation: There are two paths whose sum equals targetSum:
// 5 + 4 + 11 + 2 = 22
// 5 + 8 + 4 + 5 = 22
// Example 2:


// Input: root = [1,2,3], targetSum = 5
// Output: []
// Example 3:

// Input: root = [1,2], targetSum = 0
// Output: []
 

// Constraints:

// The number of nodes in the tree is in the range [0, 5000].
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000

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
    private void dfs(TreeNode root, int targetSum, List<Integer> curList, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        
        curList.add(root.val);
        if (root.val == targetSum && root.left == null && root.right == null) {
            result.add(curList);
            return;
        }
        
        dfs(root.left, targetSum-root.val,new ArrayList<>(curList), result);
        dfs(root.right, targetSum-root.val,new ArrayList<>(curList), result);
        
        // curList.remove(curList.size()-1);
    }
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;        
    }
}


// Not creating new current list for every recursive call
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
    private void findPaths(TreeNode root, int targetSum, List<Integer> current, List<List<Integer>> paths) {
        if (root == null) return;
        
        current.add(root.val);
        if (targetSum == root.val && root.left == null && root.right == null) {
            paths.add(new ArrayList<>(current));
            current.remove(current.size()-1);
            return;
        }
        
        findPaths(root.left, targetSum-root.val, current, paths);
        findPaths(root.right, targetSum-root.val, current, paths);
        
        current.remove(current.size()-1);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        
        findPaths(root, targetSum, new ArrayList<>(), paths);
        
        return paths;
    }
}