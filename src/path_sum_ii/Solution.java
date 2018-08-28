package path_sum_ii;

import java.util.ArrayList;

public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        dfs(root, sum, new ArrayList<>(), result);

        return result;
    }

    private void dfs2(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {

        if (root == null)
            return;

        curResult.add(root.val);
        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val) {
            // 注意需要 new 一个新的变量
            result.add(new ArrayList<>(curResult));
            // 注意此处需要删除最后一个，以探索更多的可能组合
            curResult.remove(curResult.size() - 1);
            return;
        }

        // 递归左子树和右子树
        dfs2(root.left, sum - root.val, curResult, result);
        dfs2(root.right, sum - root.val, curResult, result);
        // 注意此处需要删除最后一个，以探索更多的可能组合
        curResult.remove(curResult.size() - 1);
    }

    /**
     * 递归调用时，直接传入新创建的 curResult，避免add remove 操作
     */
    private void dfs(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {
        if (root == null)
            return;

        curResult.add(root.val);
        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val) {
            // 注意需要 new 一个新的变量
            result.add(new ArrayList<>(curResult));
            return;
        }

        dfs(root.left, sum - root.val, new ArrayList<>(curResult), result);
        dfs(root.right, sum - root.val, new ArrayList<>(curResult), result);
    }
}