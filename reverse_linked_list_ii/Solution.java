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
