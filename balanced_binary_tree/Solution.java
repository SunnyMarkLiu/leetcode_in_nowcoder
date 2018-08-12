package balanced_binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as
 * a binary tree in which the depth of the two subtrees of every node
 * never differ by more than 1.
 */
public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Map<TreeNode, Integer> subTreeHeight = new HashMap<>();

    /**
     * 递归法
     * 递归获取左子树和右子树的深度，判断差值是否大于1,
     * 注意 getMaxHeight 存在大量重复计算，采用全局变量进行优化
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null)
            return true;

        int leftH = getMaxHeight(root.left);
        int rightH = getMaxHeight(root.right);
        if (Math.abs(leftH - rightH) > 1)
            return false;

        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    private int getMaxHeight2(TreeNode root) {

        if (root == null)
            return 0;

        if (subTreeHeight.containsKey(root))
            return subTreeHeight.get(root);

        int leftH = getMaxHeight2(root.left) + 1;
        int rightH = getMaxHeight2(root.right) + 1;
        // 计算 root 为根节点的深度
        int height = leftH > rightH ? leftH : rightH;
        subTreeHeight.put(root, height);
        return height;
    }

    /**
     * 假设只要高度大于1，就将每层的返回值设定为-1，如果最终返回到root的值没有-1，代表高度都小于1，
     * 但凡返回了-1，就证明这颗树在某一层出现unbalance的情况。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        return getMaxHeight(root) != -1;
    }

    private int getMaxHeight(TreeNode root) {
        if (root == null)
            return 0;

        int leftH = getMaxHeight(root.left);
        int rightH = getMaxHeight(root.right);

        // 如果此时不平衡，返回 -1，或者左子树或右子树为-1，也直接返回 -1
        if ((Math.abs(leftH - rightH) > 1) || (leftH == -1) || rightH == -1)
            return -1;

        // 返回此时的深度
        return Math.max(leftH, rightH) + 1;
    }
}