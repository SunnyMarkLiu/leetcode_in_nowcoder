package Binary_Tree_Level_Order_Traversal;

import java.util.ArrayList;


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
     */
    public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        recursionHelper(result, 0, root);
        return result;
    }

    private void recursionHelper(ArrayList<ArrayList<Integer>> result, int level, TreeNode root) {
        if (root == null)
            return;

        // 遍历新的行
        if (level == result.size()) {
            ArrayList<Integer> levelResult = new ArrayList<>();
            levelResult.add(root.val);
            result.add(levelResult);
        } else {    // 已经遍历过的 level 行
            ArrayList<Integer> levelResult = result.get(level);
            levelResult.add(root.val);
            result.set(level, levelResult);
        }
        // 遍历下一行
        recursionHelper(result, level + 1, root.left);
        recursionHelper(result, level + 1, root.right);
    }

    /**
     *  非递归的方式，遍历当前层同时保存对应的左右节点
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            // 当前层的遍历结果
            ArrayList<Integer> curResult = new ArrayList<>();
            // 保存下一层的节点
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : curLevel) {
                curResult.add(node.val);
                if (node.left != null)
                    nextLevel.add(node.left);

                if (node.right != null)
                    nextLevel.add(node.right);
            }
            result.add(curResult);
            curLevel = nextLevel;
        }
        return result;
    }
}