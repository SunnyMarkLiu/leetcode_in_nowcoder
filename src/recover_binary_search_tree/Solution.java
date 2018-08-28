package recover_binary_search_tree;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode misLeft, misRight;
    // 中序遍历的前一个节点，用于和当前节点进行比较，判断是否有序
    private TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    /**
     * 利用：二叉搜索树的中序遍历为有序数组 的性质
     * 中序遍历，记录不满足顺序排列的节点
     */
    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);

        int tmp = misLeft.val;
        misLeft.val = misRight.val;
        misRight.val = tmp;
    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        inOrderTraverse(root.left);
        // misstake left 未记录
        if (misLeft == null && preNode.val >= root.val)
            misLeft = preNode;

        if (misLeft != null && preNode.val >= root.val)
            misRight = root;

        preNode = root;
        inOrderTraverse(root.right);
    }

}