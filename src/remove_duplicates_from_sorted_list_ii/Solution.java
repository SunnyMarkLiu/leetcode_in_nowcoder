package remove_duplicates_from_sorted_list_ii;

/**
 * Given a **sorted** linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
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
     * 同样采用双指针法，只是现在要把前驱指针的 next 指向下一个不重复的元素中，
     * 如果找到不重复元素，则把前驱指针指向该元素，否则删除此元素。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;

        // 由于涉及到可能修改头节点，因此定义 dummy 节点
        ListNode dummy = new ListNode(head.val - 1); // tricky, 创建的节点和head 不想等
        dummy.next = head;

        // 定义两个指针
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // 如果不存在重复节点，则 pre.next == cur
            if (pre.next == cur)
                pre = pre.next;
            else // 过滤重复节点
                pre.next = cur.next;

            cur = cur.next;
        }
        return dummy.next;
    }
}