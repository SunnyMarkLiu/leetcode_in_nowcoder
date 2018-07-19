package Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
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

    /**
     * 递归的方式实现二叉树的前序遍历
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即树的深度，O(logn)
     */
    public ArrayList<Integer> preorderTraversal1(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        recursionHelper(result, root);
        return result;
    }

    private void recursionHelper(ArrayList<Integer> result, TreeNode root) {

        if (root == null)
            return;

        // 访问根节点
        result.add(root.val);

        // 访问左子树
        if (root.left != null)
            recursionHelper(result, root.left);

        // 访问右子树
        if (root.right != null)
            recursionHelper(result, root.right);
    }

    /**
     * 迭代（iteratively）的方式实现，用栈模拟实现递归，
     * 因此算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (stack.size() > 0) {
            // 访问 root
            TreeNode curr_node = stack.pop();
            result.add(curr_node.val);

            // 注意栈访问的顺序，先 push right node
            if (curr_node.right != null)
                stack.add(curr_node.right);

            if (curr_node.left != null)
                stack.add(curr_node.left);
        }

        return result;
    }
}
