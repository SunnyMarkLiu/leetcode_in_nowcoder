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
