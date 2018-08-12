package Linked_List_Cycle_II;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, returnnull.
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
     * 判断是否有环，如果有，则返回环中的一个节点
     */
    private ListNode getNodeInLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != null && fast != null && fast.next != null) {
            if (fast == slow)
                return fast;

            fast = fast.next.next;
            slow = slow.next;
        }

        return null;
    }

    /**
     * 检测环的入口节点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        // 1. 先判断 list 是否有环
        ListNode loopNode = getNodeInLoop(head);

        if (loopNode == null)
            return null;

        // 2. 统计环的长度
        ListNode node1 = loopNode;
        int loopCount = 1;
        while (node1.next != loopNode) {
            node1 = node1.next;
            loopCount++;
        }

        // 3. 两个指针从头开始，node1 先走 loopCount，再同时走，直到相遇
        node1 = head;
        ListNode node2 = head;

        for (int i = 0; i < loopCount; i++)
            node1 = node1.next;

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        // 此时的相遇点即为环入口点
        return node1;
    }
}