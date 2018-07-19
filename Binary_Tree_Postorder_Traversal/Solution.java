package Binary_Tree_Postorder_Traversal;

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
     * 递归的方式实现
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即树的深度，O(logn)
     */
    public ArrayList<Integer> postorderTraversal1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        recursionHelper(result, root);
        return result;
    }

    private void recursionHelper(ArrayList<Integer> result, TreeNode root) {
        if (root == null)
            return;

        if (root.left != null)
            recursionHelper(result, root.left);

        if (root.right != null)
            recursionHelper(result, root.right);

        result.add(root.val);
    }

    /**
     * 迭代（iteratively）的方式实现，用栈模拟实现递归，
     * 因此算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     *
     * 先从根往左一直入栈，直到为空，然后判断栈顶元素的右孩子，如果不为空且未被访问过，则从它开始重复左孩子入栈的过程；
     * 否则说明此时栈顶为要访问的节点（因为左右孩子都是要么为空要么已访问过了），出栈然后访问即可，
     * 接下来再判断栈顶元素的右孩子...直到栈空。
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        TreeNode node = root;

        while (stack.size() > 0) {
            // 去除栈顶元素
            TreeNode top = stack.peek();

            // 如果 (当前处理的结点没有左右结点) 或者 (当前结点的左结点已经处理）或者（当前结点的右结点已经处理）

            // 叶子节点，直接访问
            // top 节点的左节点或右节点已经访问过，直接访问 top 节点（后序遍历保证）
            if ((top.left == null && top.right == null) || (top.left == node) || (top.right == node)) {
                result.add(top.val);
                node = stack.pop();
            } else {
                if (top.right != null)
                    stack.add(top.right);

                if (top.left != null)
                    stack.add(top.left);
            }
        }
        return result;
    }
}