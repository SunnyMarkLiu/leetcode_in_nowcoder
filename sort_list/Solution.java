package sort_list;


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Sort a linked list in O(nlogn) time using constant space complexity.
 */
public class Solution {

    /**
     * 时间复杂度为 O(nlogn)，采用归并排序
     *
     * 归并排序的一般步骤为：
     * 1）将待排序数组（链表）取中点并一分为二；
     * 2）递归地对左半部分进行归并排序；
     * 3）递归地对右半部分进行归并排序；
     * 4）将两个半部分进行合并（merge）,得到结果。
     *
     * 所以对应此题目，可以划分为三个小问题:
     * 1、快慢指针找到链表的中点
     * 2、拆分链表
     * 3、合并有序链表
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // 快慢指针找链表的中点
        ListNode middle = findMiddle(head);
        // 右边排序
        ListNode right = sortList(middle.next);
        middle.next = null;
        // 左边排序
        ListNode left = sortList(head);
        // 合并
        return mergeTwoLists(left, right);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 辅助头节点
        ListNode head = new ListNode(-1);
        ListNode node = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        // 剩余的直接拼接
        if (l1 != null)
            node.next = l1;
        if (l2 != null)
            node.next = l2;

        return head.next;
    }
}