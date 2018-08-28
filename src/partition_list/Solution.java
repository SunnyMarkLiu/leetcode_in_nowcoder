package partition_list;

/**
 * Given a linked list and a value x, partition it
 * such that all nodes less than x come before
 * nodes greater than or equal to x.
 * You should preserve the original relative order of
 * the nodes in each of the two partitions.
 *
 * 给定一个单链表和一个x，把链表中小于x的放到前面，大于等于x的放到后面，
 * 每部分元素的原始相对位置不变。
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return1->2->2->3->4->5.
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
     * 把小于x的都挂到 left 链表, 大于等于x的都放到 right 链表，再把 right 和 left 拼接
     */
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode curleft = left;
        ListNode curright = right;

        while (head != null) {
            if (head.val < x) {
                curleft.next = head;
                curleft = curleft.next;
            } else {
                curright.next = head;
                curright = curright.next;
            }
            head = head.next;
        }

        // 拼接 right 和 left，注意需要设置 right 最有一个节点的 next 为 null
        curleft.next = right.next;
        curright.next = null;
        return left.next;
    }
}
