package longest_palindromic_substring;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there
 * exists one unique longest palindromic substring.
 */
public class Solution {

    /**
     * 中心扩展法
     *
     * 基本思路是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）
     * 往两边同时进行扫描，直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。
     * 对于每个中心往两边扫描。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";

        int maxlen = 0; // 最大长度
        String result = "";

        // 考虑两种情况，bab中间是a(n)，bb中间是间隙(n-1)
        for (int i = 0; i < 2*s.length() - 1; i++) {

            int left = i / 2;
            int right = i / 2;

            // 如果之间为间隙
            if (i % 2 == 1)
                right++;

            String curMaxPalindrome = findMaxPalindrome(s, left, right);
            if (maxlen < curMaxPalindrome.length()) {
                maxlen = curMaxPalindrome.length();
                result = curMaxPalindrome;
            }
        }
        return result;
    }

    private String findMaxPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 前面进行了 left--，注意此处需要left+1
        return s.substring(left + 1, right);
    }
}
