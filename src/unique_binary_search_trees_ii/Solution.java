package unique_binary_search_trees_ii;

import java.util.ArrayList;

/**
 * Given n, generate all structurally unique BST's
 * that store values 1...n.
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
     * 递归的方式
     */
    public ArrayList<TreeNode> generateTrees(int n) {
        return createTree(1, n);
    }

    /**
     * 每次一次选取一个结点为根，然后递归求解左右子树的所有结果，
     * 最后根据左右子树的返回的所有子树，然后针对左右子树的结果进行全排列，
     * 总共有左右子树数量的乘积种情况。
     *
     * 因为需要递归求得左右子树的数目，因此需要定义 left 和 right 划定范围
     */
    private ArrayList<TreeNode> createTree(int left, int right) {
        ArrayList<TreeNode> result = new ArrayList<>();

        if (left > right) {
            // tricky！此处的 null可以用于在全排列时作为叶子节点的 null子节点
            result.add(null);
            return result;
        }

        // 以i为根节点的树，左子树的范围 [left,i-1], 右子树的范围 [i+1, right]
        for (int i = left; i <= right; i++) {
            ArrayList<TreeNode> leftResult = createTree(left, i - 1);
            ArrayList<TreeNode> rightResult = createTree(i+1, right);
            // 对左右子树的结果进行排列拼接
            int leftSize = leftResult.size();
            int rightSize = rightResult.size();
            for (int l = 0; l < leftSize; l++) {
                for (int r = 0; r < rightSize; r++) {
                    // 此时的 i 为根节点
                    TreeNode root = new TreeNode(i);
                    root.left = leftResult.get(l);
                    root.right = rightResult.get(r);
                    // 添加此时拼接好的结果
                    result.add(root);
                }
            }
        }

        return result;
    }
}
