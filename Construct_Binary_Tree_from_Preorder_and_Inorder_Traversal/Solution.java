package Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || preorder == null ||
                inorder.length == 0 || preorder.length == 0)
            return null;

        return buildHelper(preorder, 0, preorder.length-1,
                            inorder,0, inorder.length-1);
    }

    private TreeNode buildHelper(int[] preorder, int preLeft, int preRight,
                                 int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight)
            return null;

        // 根据 preorder 的第一个节点创建根节点
        TreeNode root = new TreeNode(preorder[preLeft]);

        if (preLeft == preRight)
            return root;

        // 根据此 root 确定在中序遍历中的位置
        int rootIndex = -1;
        for (int i=inLeft; i <= inRight; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }
        // rootIndex 左边的部分即为 root 的左子树
        int leftTreeSize = rootIndex - inLeft;

        // 递归构造左子树和右子树
        root.left = buildHelper(preorder, preLeft + 1, preLeft + leftTreeSize,
                                inorder, inLeft, rootIndex - 1);
        root.right = buildHelper(preorder, preLeft + leftTreeSize + 1, preRight,
                                 inorder, rootIndex + 1, inRight);

        return root;
    }

}