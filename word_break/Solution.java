package word_break;

import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 *
 * For example, given
 * s ="leetcode",
 * dict =["leet", "code"].
 *
 * Return true because"leetcode"can be segmented as"leet code".
 */
public class Solution {
    /**
     * 动态规划题目的基本思路:
     * 首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。
     * 然后是最重要的递推式，就是如何从存储的历史信息中得到当前步的结果。
     * 最后我们需要考虑的就是起始条件的值。
     *
     * 接下来我们套用上面的思路来解这道题：
     * 首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，
     * 我们需要一个长度为 n 的布尔数组来存储信息。然后假设我们现在拥有 res[0,...,i-1] 的结果，我们来获得res[i]的表达式。
     * 思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true
     *
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        // 定义 n+1 长度的标记数组，用于标记从0开始到第 i 元素满足要求
        boolean[] results = new boolean[s.length() + 1];
        // 初始化 s 为空满足条件
        results[0] = true;

        for (int i = 0; i < s.length() + 1; i++) {
            // 判断第 i 个元素，先判断第 i 之前的元素
            for (int j = 0; j < i; j++) {
                // i 之前的 result 为 true，并且当前剩下的 j->i 的子串在 dict 中
                if (results[j] && dict.contains(s.substring(j, i))) {
                    results[i] = true;
                    break;
                }
            }
        }
        return results[s.length()];
    }
}
