package path_sum;


/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree andsum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path5->4->11->2which sum is 22.
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

    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    /**
     * 先序遍历的思想，从根节点开始一直到叶子节点，判断和是否为sum
     */
    private boolean dfs(TreeNode root, int sum) {
        if (root == null)
            return false;

        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val)
            return true;

        // 非叶子节点，递归其左右子树
        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }
}