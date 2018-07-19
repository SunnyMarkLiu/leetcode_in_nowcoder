
package Minimum_Depth_of_Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 递归的方式实现, 比较左右子树的深度
     */
    public int runRecursion(TreeNode root) {
        if (root == null)
            return 0;

        // 如果 root 的左右子树都存在，则递归获取左右子树的 min + 1
        if (root.left != null && root.right != null)
            return Math.min(runRecursion(root.left), runRecursion(root.right)) + 1;

        // 如果当前结点只存在一个左右结点，选择有叶子结点的分支，即 depth 较大的，同时注意加上当前的结点 + 1
        return Math.max(runRecursion(root.left), runRecursion(root.right)) + 1;
    }

    /**
     * 层次遍历，找到第一个左右节点都是 null 的叶子节点，直接返回此时的 depth
     */
    public int run(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;

        int depth = 0;
        // 层次遍历，先进先出，采用队列
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);

        while (!levelQueue.isEmpty()) {
            // 当前待遍历 level 的节点数
            int cur_level_size = levelQueue.size();
            depth++;

            // 遍历当前层
            for (int i=0; i < cur_level_size; i++) {
                TreeNode node = levelQueue.poll();
                // 如果此 node 为叶子节点，直接返回此时的 depth
                if (node.left == null && node.right == null)
                    return depth;

                if (node.left != null)
                    levelQueue.offer(node.left);

                if (node.right != null)
                    levelQueue.offer(node.right);
            }
        }
        return 0;
    }
}
