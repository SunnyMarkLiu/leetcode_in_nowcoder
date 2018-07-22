package Binary_Tree_Zigzag_Level_Order_Traversal;

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

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        recursionHelper(result, 0, root);

        return result;
    }

    /**
     * 递归的方式实现层次遍历，注意奇偶 leve 添加数据的顺序
     */
    private void recursionHelper(ArrayList<ArrayList<Integer>> result, int level, TreeNode root) {
        if (root == null)
            return;

        // 遍历新的行
        if (level == result.size()) {
            ArrayList<Integer> newLevelResult = new ArrayList<>();
            // 注意奇偶 leve 添加数据的顺序
            if (level % 2 == 0)
                newLevelResult.add(root.val);
            else
                newLevelResult.add(0, root.val);
            result.add(newLevelResult);
        } else {
            ArrayList<Integer> levelResult = result.get(level);
            // 注意奇偶 leve 添加数据的顺序
            if (level % 2 == 0)
                levelResult.add(root.val);
            else
                levelResult.add(0, root.val);
            result.set(level, levelResult);
        }

        // 递归遍历左右节点
        recursionHelper(result, level + 1, root.left);
        recursionHelper(result, level + 1, root.right);
    }

    /**
     * 非递归的方式，层次遍历，注意奇偶 leve 添加数据的顺序
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);
        int level = 0;

        while (!curLevel.isEmpty()) {
            // 当前层的遍历结果
            ArrayList<Integer> curResult = new ArrayList<>();
            // 保存下一层的节点
            ArrayList<TreeNode> nextLevel = new ArrayList<>();

            for (TreeNode node : curLevel) {
                if (level % 2 == 0)
                    curResult.add(node.val);
                else
                    curResult.add(0, node.val);

                if (node.left != null)
                    nextLevel.add(node.left);

                if (node.right != null)
                    nextLevel.add(node.right);
            }
            level += 1;
            result.add(curResult);
            curLevel = nextLevel;
        }
        return result;
    }
}