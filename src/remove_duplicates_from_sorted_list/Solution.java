package remove_duplicates_from_sorted_list;

/**
 * Given a **sorted** linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
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
     * 注意是已经排序的链表，采用双指针法，从头开始扫描
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null)
            return null;

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (pre.val == cur.val)
                // 过滤重复值，注意此处有节点断开，说明下 JVM 进行内存的回收
                pre.next = cur.next;
            else
                pre = cur;
            cur = cur.next;
        }
        return head;
    }

    /**
     * 对双指针进行优化
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while (cur != null) {
            // 相邻的节点进行比较
            while (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }
            // 扫描整个节点，cur 后移
            cur = cur.next;
        }
        return head;
    }
}