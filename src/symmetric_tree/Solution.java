package symmetric_tree;

/**
 * 判断二叉树是否自对称
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }
 
    // 递归： leftNode 的右子树和 rightNode 的左子树是否对称，leftNode 的左子树和 rightNode 的右子树是否对称
    private boolean helper(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null)
            return true;
 
        if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null))
            return false;
 
        // leftNode 和 rightNode 都不为空，比较 val 是否相等
        // 同时看 leftNode 的右子树和 rightNode 的左子树是否对称，leftNode 的左子树和 rightNode 的右子树是否对称
        return (leftNode.val == rightNode.val) &&
                helper(leftNode.right, rightNode.left) &&
                helper(leftNode.left, rightNode.right);
    }
}