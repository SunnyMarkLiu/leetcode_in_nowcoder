package longest_consecutive_sequence;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of
 * the longest consecutive elements sequence.
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4].
 * Return its length:4.
 * Your algorithm should run in O(n) complexity.
 */
public class Solution {

    /**
     * 要求时间复杂度为 O(n)，联想到用 n 次调用 HashMap 的查询 O(1) 方法
     *
     * 1.先把数字放到一个集合中，拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，
     * 3.最后比较当前串是不是比当前最大串要长，是则更新。如此继续直到集合为空。
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        Map<Integer, Boolean> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.put(num, false); // 初始化数组的元素都没有被访问
        }

        int longest = 0;

        // 遍历数组的元素
        for (int num : nums) {
            // 如果这个数组被访问过
            if (numMap.get(num))
                continue;

            // 当前 num 开始的连续的最大长度
            int cur_longest = 1;
            int left = num - 1;
            int right = num + 1;

            // 更新数组访问状态
            numMap.put(num, true);

            // 扫描左右两边
            while (numMap.containsKey(left)) {
                // 更新数组访问状态
                numMap.put(left, true);
                left--;
                cur_longest++;
            }

            while (numMap.containsKey(right)) {
                // 更新数组访问状态
                numMap.put(right, true);
                right++;
                cur_longest++;
            }

            if (cur_longest > longest)
                longest = cur_longest;
        }
        return longest;
    }
}