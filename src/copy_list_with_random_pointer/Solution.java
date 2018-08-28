package copy_list_with_random_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 复杂链表的复制
 */
public class Solution {
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        // 先复制原始链表，同时记录 random 映射关系
        Map<RandomListNode, RandomListNode> randomMap = new HashMap<>();

        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode oldTmp = head.next;
        RandomListNode newTmp = newHead;
        randomMap.put(newHead, head);

        while (oldTmp != null) {
            RandomListNode newNode = new RandomListNode(oldTmp.label);
            randomMap.put(newNode, oldTmp);
            oldTmp = oldTmp.next;
            newTmp.next = newNode;
            newTmp = newTmp.next;
        }

        // 更新 random 内容
        newTmp = newHead;
        while (newTmp != null) {
            newTmp.random = randomMap.get(newTmp).random;
            newTmp = newTmp.next;
        }
        return newHead;
    }
}