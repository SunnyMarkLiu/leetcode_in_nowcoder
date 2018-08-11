package populating_next_right_pointers_in_each_node;


import java.util.ArrayList;

/**
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set toNULL.
 * Initially, all next pointers are set toNULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 *
 * For example,
 * Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 *
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 */
public class Solution {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    /**
     * 树的层次遍历，由于使用了空间复杂度 O(n)
     */
    public void connect1(TreeLinkNode root) {
        if (root == null)
            return;

        ArrayList<TreeLinkNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            ArrayList<TreeLinkNode> nextLevel = new ArrayList<>();
            // 遍历当前层
            TreeLinkNode node = curLevel.get(0);
            TreeLinkNode nextNode;

            // 保存下一层节点
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);

            for (int i = 1; i < curLevel.size(); i++) {
                // 修改 next 指针
                nextNode = curLevel.get(i);
                node.next = nextNode;
                node = nextNode;

                // 保存下一层节点
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            curLevel = nextLevel;
        }
    }

    /**
     * 递归的方式实现，空间复杂度为调用栈的深度
     * 边界条件几种方式
     * 1、root 为 null 直接返回
     * 2、root 根节点下的左右子树的 next 链接
     * 3、跨 root 根节点的子树的 next 链接
     */
    public void connect2(TreeLinkNode root) {
        if (root == null)
            return;

        // root 根节点下的左右子树的 next 链接
        if (root.left != null && root.right != null)
            root.left.next = root.right;
        // 跨 root 根节点的子树的 next 链接
        if (root.right != null && root.next != null)
            root.right.next = root.next.left;

        // 递归connect以root左右子树节点为根节点的子树
        connect2(root.left);
        connect2(root.right);
    }

    /**
     * 非递归的方式实现
     *
     * 从最左边的节点开始更新 next 指针，同样两种情况：
     * root.left.next -> root.right
     * root.right.next -> root.next.left
     */
    public void connect(TreeLinkNode root) {
        if (root ==null)
            return;

        TreeLinkNode p = root;
        TreeLinkNode updateRoot;

        // 从根节点 p 开始，从最左边的节点开始修改p的下一层的左右子树节点的 next 指针
        while (p.left != null) {
            updateRoot = p;
            while (updateRoot != null) {
                // 第一种情况
                updateRoot.left.next = updateRoot.right;
                // 第二种情况
                if (updateRoot.next != null) {
                    updateRoot.right.next = updateRoot.next.left;
                }
                // 更新的节点向右移动
                updateRoot = updateRoot.next;
            }
            p = p.left;
        }
    }
}
