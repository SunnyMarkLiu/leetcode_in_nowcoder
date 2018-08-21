package same_tree;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
         
        if (p == null || q == null)
            return false;
         
        // 都不为空，则比较 val 是否相等，并进行递归比较
        return (p.val == q.val) && (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}