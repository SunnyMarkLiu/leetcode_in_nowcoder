package convert_sorted_array_to_binary_search_tree;

/**
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * 把一个有序数组转换成一颗二分查找树
 */
public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 把中间元素转化为根，然后递归构造左右子树。
     */
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0)
            return null;

        return helper(num, 0, num.length - 1);
    }

    /**
     * 0, 1, 2, 3, 4
     */
    private TreeNode helper(int[] num, int left, int right) {
        if (left > right)
            return null;

        // 只剩下一个节点，直接返回
        if (left == right)
            return new TreeNode(num[left]);

        // 中间节点作为根节点
        int mid = left + (right - left + 1) / 2;
        TreeNode root = new TreeNode(num[mid]);

        root.left = helper(num, left, mid - 1);
        root.right = helper(num, mid + 1, right);
        return root;
    }
}
