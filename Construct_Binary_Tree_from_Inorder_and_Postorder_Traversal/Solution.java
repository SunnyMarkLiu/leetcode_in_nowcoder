package Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;


public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0)
            return null;

        return buildHelper(inorder, 0, inorder.length - 1,
                           postorder, 0, postorder.length - 1);
    }


    private TreeNode buildHelper(int[] inorder, int inLeft, int inRight,
                                 int[] postorder, int posLeft, int posRight) {
        if (inLeft > inRight)
            return null;

        // 根据后续遍历的最后一个元素获取根节点
        TreeNode root = new TreeNode(postorder[posRight]);
        if (inLeft == inRight)
            return root;

        // 获取根节点在中序遍历的位置
        int root_index = -1;
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == root.val) {
                root_index = i;
                break;
            }
        }
        int leftTreeSize =  root_index - inLeft;

        // 递归构造左右子树
        root.left = buildHelper(inorder, inLeft, root_index - 1,
                                postorder, posLeft, posLeft + leftTreeSize - 1);
        root.right = buildHelper(inorder, root_index + 1, inRight,
                                 postorder, posLeft + leftTreeSize, posRight - 1);
        return root;
    }
}