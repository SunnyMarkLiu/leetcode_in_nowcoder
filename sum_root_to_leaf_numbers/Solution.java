package sum_root_to_leaf_numbers;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * <p>
 * 1
 * / \
 * 2   3
 * \
 * 3
 * <p>
 * The root-to-leaf path 1->2->3 represents the number 123.
 * The root-to-leaf path 1->3 represents the number 13.
 * <p>
 * Return the sum = 123 + 13 = 136.
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

    private int sum = 0;

    /**
     * 先序遍历的思想(根左右) + 数字求和(每一层都比上层和*10+当前根节点的值)
     */
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        preorderSum(root, root.val);
        return sum;
    }

    /**
     * 先序遍历求和
     */
    private void preorderSum(TreeNode root, int pathNum) {
        if (root == null)
            return;

        // 到达叶子节点，计算此时的 sum
        if (root.left == null && root.right == null) {
            sum += pathNum;
        }

        if (root.left != null) {
            preorderSum(root.left, 10 * pathNum + root.left.val);
        }

        if (root.right != null) {
            preorderSum(root.right, 10 * pathNum + root.right.val);
        }
    }
}
