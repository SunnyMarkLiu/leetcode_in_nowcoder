package insertion_sort_list;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Sort a linked list using insertion sort.
 *
 * 每次找到最小的节点，摘出来，插入rHead的链表后面，复杂度 O(n^2)
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode rHead = new ListNode(-1);
        rHead.next = head;

        ListNode cur = head;
        ListNode pre = null;    // 当前节点的前面的节点
        ListNode tmp = null;    // 当前节点的前面的节点
        while (cur.next != null) {
            // 如果当前节点比下一个节点值大
            if (cur.val > cur.next.val) {
                // 开始计算cur.next插入的位置，从rHead开始查找
                pre = rHead;
                while (cur.next.val >= pre.next.val)
                    pre = pre.next;

                // 找到 cur.next 该插入的位置 pre的后面后，进行插入
                tmp = cur.next;
                cur.next = cur.next.next;
                tmp.next = pre.next;
                pre.next = tmp;
                // cur.next 插在 pre 和 cur.next.next 之间
            } else {    // 当前节点比下一个节点小，cur 后移
                cur = cur.next;
            }
        }
        return rHead.next;
    }
}