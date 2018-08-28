package Binary_Tree_Inorder_Traversal;

import java.util.ArrayList;
import java.util.Stack;

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
     * 递归的方式实现，算法的时间复杂度是O(n),
     * 而空间复杂度则是递归栈的大小，即树的深度，O(logn)
     */
    public ArrayList<Integer> inorderTraversal1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        recursionHelper(result, root);
        return result;
    }

    private void recursionHelper(ArrayList<Integer> result, TreeNode root) {
        if (root == null)
            return;

        // 访问左子树
        if (root.left != null)
            recursionHelper(result, root.left);
        // 访问根节点
        result.add(root.val);
        // 访问右子树
        if (root.right != null)
            recursionHelper(result, root.right);

    }

    /**
     * 迭代（iteratively）的方式实现，用栈模拟实现递归，
     * 因此算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmpNode = root;

        while (tmpNode != null || stack.size() > 0) {
            if (tmpNode != null) { // 当前节点不为空，继续遍历左节点
                stack.add(tmpNode);
                tmpNode = tmpNode.left;
            } else {    // 访问到最左节点之后，从 stack 中 pop 取数据
                tmpNode = stack.pop();
                result.add(tmpNode.val);
                // 访问右子树
                tmpNode = tmpNode.right;
            }
        }
        return result;
    }
}