package Binary_Tree_Level_Order_Traversal_II;

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

    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            // 遍历当前层的结果
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
            result.add(0, curResult);
            curLevel = nextLevel;
        }
        return result;
    }
}