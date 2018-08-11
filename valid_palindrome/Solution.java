package valid_palindrome;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama"is a palindrome.
 * "race a car"is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() == 0) return true;

        // 两边开始扫描，遇到非字母或数字的（alphanumeric）的跳过
        int left = 0;
        int right = s.length() - 1;

        for (; left < right; left++, right--) {
            while (left < right && !isAlphaNumeric(s.charAt(left)))
                left++;
            while (left < right && !isAlphaNumeric(s.charAt(right)))
                right--;

            // 判断此时left 和 right 的字符是否相等
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
        }

        return true;
    }

    private boolean isAlphaNumeric(char c) {
        return ((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z'));
    }
}