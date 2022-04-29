// Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).

 

// Example 1:


// Input: root = [3,9,20,null,null,15,7]
// Output: [[3],[9,20],[15,7]]
// Example 2:

// Input: root = [1]
// Output: [[1]]
// Example 3:

// Input: root = []
// Output: []
 

// Constraints:

// The number of nodes in the tree is in the range [0, 2000].
// -1000 <= Node.val <= 1000

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
    private void bfs(Queue<TreeNode> q, int level, List<List<Integer>> result) {
        if (q.isEmpty())
            return;
        List<Integer> lev = new ArrayList<>();
        
        q.add(null);
        while(q.peek() != null) {
            TreeNode node = q.poll();
            if (node.left != null) {
                q.add(node.left);
                lev.add(node.left.val);
            }
            if (node.right != null) {
                q.add(node.right);
                lev.add(node.right.val);
            }
        }
        q.remove();
        if (!lev.isEmpty()) result.add(lev);
        
        bfs(q, level+1, result);
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.add(root);
        
        result.add(Arrays.asList(root.val));
        bfs(q, 0, result);
            return result;
    }
}