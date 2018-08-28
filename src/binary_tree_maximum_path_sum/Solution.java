package binary_tree_maximum_path_sum;

/**
 *
 Given a binary tree, find the maximum path sum.

 The path may start and end at any node in the tree.

 For example:
 Given the below binary tree,

   1
  / \
 2   3

 Return6.


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
     * 在递归函数中，如果当前结点不存在，那么直接返回0。否则就分别对其左右子节点调用递归函数，
     * 由于路径和有可能为负数，而我们当然不希望加上负的路径和，所以我们和0相比，取较大的那个，
     * 就是要么不加，加就要加正数。然后我们来更新全局最大值结果res，就是以左子结点为终点的
     * 最大path之和加上以右子结点为终点的最大path之和，还要加上当前结点值，这样就组成了一个
     * 条完整的路径。而我们返回值是取left和right中的较大值加上当前结点值，因为我们返回值的
     * 定义是以当前结点为终点的path之和，所以只能取left和right中较大的那个值，而不是两个都要
     */
    private Integer result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return result;
    }

    /**
     * 从叶子节点开始到当前结点为终点的 path 的最大路径之和，然后全局路径最大值放在参数中
     */
    private int helper(TreeNode root) {
        if (root == null)
            return 0;

        // 左右子节点调用递归函数，获取当前节点为根节点的最大路径和
        // 注意要和 0 进行比较
        int leftNodeResult = Math.max(helper(root.left), 0);
        int rightNodeResult = Math.max(helper(root.right), 0);
        // 计算全局的result为：当前根节点的值加上左右节点为终点的最大路径和
        result = Math.max(result, root.val + leftNodeResult + rightNodeResult);

        // 返回当前结点为终点的 path 的最大路径之和
        return Math.max(leftNodeResult, rightNodeResult) + root.val;
    }
}
