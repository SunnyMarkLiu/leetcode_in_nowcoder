package populating_next_right_pointers_in_each_node_ii;

import java.util.*;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 *
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */
public class Solution {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    /**
     * 层次遍历的方法
     */
    public void connect1(TreeLinkNode root) {
        if (root == null)
            return;

        ArrayList<TreeLinkNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            ArrayList<TreeLinkNode> nextLevel = new ArrayList<>();
            // 遍历当前层，从最左边开始
            TreeLinkNode node = curLevel.get(0);
            TreeLinkNode nextNode;

            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);

            for (int i = 1; i < curLevel.size(); i++) {
                // 修改 next 指针
                nextNode = curLevel.get(i);
                node.next = nextNode;
                node = nextNode;

                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            curLevel = nextLevel;
        }
    }

    /**
     * 递归的方式实现
     */
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        TreeLinkNode cur = root;

        while (cur != null) {
            if (cur.left != null)
                cur.left.next = cur.right != null ? cur.right : getNext(cur);

            if (cur.right != null)
                cur.right.next = getNext(cur);

            cur = cur.next;
        }

        connect(root.left);
        connect(root.right);
    }

    /**
     * 根据当前 root 获取 root 下一层合适的节点
     */
    private TreeLinkNode getNext(TreeLinkNode root) {

        TreeLinkNode nextRoot = root.next;
        while (nextRoot != null) {
            if (nextRoot.left != null) return nextRoot.left;
            if (nextRoot.right != null) return nextRoot.right;
            nextRoot = nextRoot.next;
        }
        return null;
    }

}