package validate_binary_search_tree;

/**
 * 判断二叉树是否是二叉搜索树
 * BST 满足：
 * 1.若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2.若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3.任意节点的左、右子树也分别为二叉查找树；
 * 4.没有键值相等的节点。
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

    public boolean isValidBST(TreeNode root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
     
    /**
     * 递归的方法，注意引入当前根节点的左右子树所满足的上下界
     */
    private boolean helper(TreeNode root, int min, int max) {
        if (root == null)
            return true;
         
        // 每个节点都有一个对应的上下界
        if (root.val <= min || root.val >= max)
            return false;
         
        // 递归判断root的左右子树是否满足 BST
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}