package maximum_depth_of_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
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

    public int maxDepth2(TreeNode root) {
        return helper(root);
    }

    /**
     * 递归的方式实现
     */
    private int helper(TreeNode root) {
        if (root == null)
            return 0;

        // 如果只存在一个左右节点
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }

    /**
     * 层次遍历，保存最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);

        int depth = 0;
        while (!levelQueue.isEmpty()) {
            int cur_level_size = levelQueue.size();
            depth++;

            for (int i = 0; i < cur_level_size; i++) {
                TreeNode node = levelQueue.poll();
                if (node.left != null)
                    levelQueue.offer(node.left);
                if (node.right != null)
                    levelQueue.offer(node.right);
            }
        }
        return depth;
    }
}