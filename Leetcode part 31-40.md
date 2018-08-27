## 31. path_sum_ii
```java
package path_sum_ii;

import java.util.ArrayList;

public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        dfs(root, sum, new ArrayList<>(), result);

        return result;
    }

    private void dfs2(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {

        if (root == null)
            return;

        curResult.add(root.val);
        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val) {
            // 注意需要 new 一个新的变量
            result.add(new ArrayList<>(curResult));
            // 注意此处需要删除最后一个，以探索更多的可能组合
            curResult.remove(curResult.size() - 1);
            return;
        }

        // 递归左子树和右子树
        dfs2(root.left, sum - root.val, curResult, result);
        dfs2(root.right, sum - root.val, curResult, result);
        // 注意此处需要删除最后一个，以探索更多的可能组合
        curResult.remove(curResult.size() - 1);
    }

    /**
     * 递归调用时，直接传入新创建的 curResult，避免add remove 操作
     */
    private void dfs(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {
        if (root == null)
            return;

        curResult.add(root.val);
        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val) {
            // 注意需要 new 一个新的变量
            result.add(new ArrayList<>(curResult));
            return;
        }

        dfs(root.left, sum - root.val, new ArrayList<>(curResult), result);
        dfs(root.right, sum - root.val, new ArrayList<>(curResult), result);
    }
}
```

## 32. populating_next_right_pointers_in_each_node
```java
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

```

## 33. populating_next_right_pointers_in_each_node_ii
```java
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
```

## 34. recover_binary_search_tree
```java
package recover_binary_search_tree;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode misLeft, misRight;
    // 中序遍历的前一个节点，用于和当前节点进行比较，判断是否有序
    private TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    /**
     * 利用：二叉搜索树的中序遍历为有序数组 的性质
     * 中序遍历，记录不满足顺序排列的节点
     */
    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);

        int tmp = misLeft.val;
        misLeft.val = misRight.val;
        misRight.val = tmp;
    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        inOrderTraverse(root.left);
        // misstake left 未记录
        if (misLeft == null && preNode.val >= root.val)
            misLeft = preNode;

        if (misLeft != null && preNode.val >= root.val)
            misRight = root;

        preNode = root;
        inOrderTraverse(root.right);
    }

}
```

## 35. reorder_list
```java
package reorder_list;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}


/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln →L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given{1,2,3,4}, reorder it to{1,4,2,3}.
 *
 * 快慢指针找到中间节点，将后面的链表反转（前插法），合并链表
 * 1,2
 * 3,4
 */
public class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        // 1、快慢指针找到链表的中点
        ListNode middle = findMiddle(head);

        // 2、反转 middle.next开始的链表
        ListNode start = middle.next;
        middle.next = null;
        ListNode newhead = reverseLinkedList(start);

        // 3. 交替合并两个链表
        merge(head, newhead);
    }

    /**
     * 交叉合并链表
     */
    private void merge(ListNode oddHead, ListNode evenHead) {
        while (oddHead != null && evenHead != null) {
            ListNode oddTmp = oddHead.next;
            oddHead.next = evenHead;
            ListNode evenTmp = evenHead.next;
            evenHead.next = oddTmp;
            oddHead = oddTmp;
            evenHead = evenTmp;
        }
    }

    /**
     * 快慢指针获取链表中点
     */
    private ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow即指向中间节点
        return slow;
    }

    /**
     * 递归的方式反转链表, 返回反转后的头结点，原头结点指向了null
     */
    private ListNode reverseLinkedList(ListNode head) {
        if (head.next == null)
            return head;

        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

```

## 36. restore_ip_addresses
```java
package restore_ip_addresses;

import java.util.ArrayList;

/**
 * Given a string containing only digits, restore it by
 * returning all possible valid IP address combinations.
 *
 * For example:
 * Given"25525511135",
 * return["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class Solution {

    /**
     * 由于每段数字最多只能有三位，而且只能分为四段，所以情况并不是很多，使用暴力搜索的方法
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();

        for (int a = 1; a < 4; a++)
        for (int b = 1; b < 4; b++)
        for (int c = 1; c < 4; c++)
        for (int d = 1; d < 4; d++) {
            // 此时的划分，总长度等于 s 的长度
            if (a + b + c + d == s.length()) {
                int A = Integer.parseInt(s.substring(0, a));
                int B = Integer.parseInt(s.substring(a, a+b));
                int C = Integer.parseInt(s.substring(a+b, a+b+c));
                int D = Integer.parseInt(s.substring(a+b+c, a+b+c+d));

                // 每段要满足 [0, 255]
                if ( A<=255 && B<=255 && C<=255 && D<=255) {
                    // 拼接此时的 ip
                    String ip = A + "." + B + "." + C + "." + D;
                    // 需要判断此时的 ip 长度是否合法，排除 100.001.123.456 的不合法 ip
                    if (ip.length() == s.length() + 3)
                        result.add(ip);
                }
            }
        }
        return result;

    }
}
```

## 37. reverse_linked_list_ii
```java
package reverse_linked_list_ii;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *
 * For example:
 * Given1->2->3->4->5->NULL, m = 2 and n = 4,
 * return1->4->3->2->5->NULL.
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 初始位置   ：自上而下为 A B C D
     * 第一次操作后：自上而下为 B A C D
     * 第二次操作后：自上而下为 C B A D
     * 第三次操作后：自上而下为 D C B A
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preStart = dummy; // 记录反转的开始节点的前一个节点，防止链表断开
        ListNode start = head; // 反转的开始节点
        // 找到第 m 个节点，注意从 1 开始
        for (int i = 1; i < m; i++) {
            preStart = start;
            start = start.next;
        }

        // 反转链表，同时记录不超过 n, 画图解释
        for (int j = 0; j < n - m; j++) {
            ListNode temp = start.next;
            start.next = temp.next;
            temp.next = preStart.next;
            preStart.next = temp;
        }
        return dummy.next;
    }
}

```

## 38. same_tree
```java
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
```

## 39. single_number
```java
package single_number;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it
 * without using extra memory?
 */
public class Solution {

    /**
     * 1.异或满足交换律。
     * 2.相同两个数异或为0。
     * 3.0异或一个数为那个数本身。
     */
    public int singleNumber(int[] A) {
        int result = 0;
        for (int a : A) {
            result ^= a;
        }
        return result;
    }
}

```

## 40. single_number_ii
```java
package single_number_ii;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it
 * without using extra memory?
 */
public class Solution {

public int singleNumber(int[] A) {
        int ones = 0;//记录只出现过1次的bits
        int twos = 0;//记录只出现过2次的bits
        int threes;
        for(int i = 0; i < A.length; i++){
            int t = A[i];
            twos |= ones&t;//要在更新ones前面更新twos
            ones ^= t;
            threes = ones&twos;//ones和twos中都为1即出现了3次
            ones &= ~threes;//抹去出现了3次的bits
            twos &= ~threes;
        }
        return ones;
    }
}
```
