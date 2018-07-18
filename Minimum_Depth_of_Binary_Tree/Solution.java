
package Minimum_Depth_of_Binary_Tree;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /*
    递归的方式实现, 比较左右子树的深度
     */
    public int run(TreeNode root) {
        if (root == null)
            return 0;

        // 如果 root 的左右子树都存在，则递归获取左右子树的 min + 1
        if (root.left != null && root.right != null)
            return Math.min(run(root.left), run(root.right)) + 1;

        // 如果当前结点只存在一个左右结点，选择有叶子结点的分支，即 depth 较大的，同时注意加上当前的结点 + 1
        return Math.max(run(root.left), run(root.right)) + 1;
    }
}
