package minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

/**
 * 从母串S中找到这样一个子串W，这个子串包含目标T串中的所有字符，并且该子串的长度最短。
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S ="XADOBECODEBANC"
 * T ="ABC"
 *
 * Minimum window is"BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string"".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return "";

        // HashMap的key为t中各个字符，value为对应字符个数
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // minLeft为最小窗口左下标，minLen为最小长度，count用来计数
        int minLeft = 0, minLen = s.length() + 1, count = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 如果map.get(c)说明t中还有字符没有包含，计数器+1
                if (map.get(c) > 0){
                    count++;
                }
                map.put(c, map.get(c) - 1);
            }
            // 如果left到i中包含t中所有字符
            while (count == t.length()) {
                if (i - left + 1 < minLen) {
                    minLeft = left;
                    minLen = i - left + 1;
                }
                c = s.charAt(left);
                // 去除左边无效的不符合的字符，试图缩短 window 的大小
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    if (map.get(c) > 0)
                        count--;
                }
                left++;
            }
        }

        if (minLen > s.length())
            return "";

        return s.substring(minLeft, minLeft + minLen);

    }
}