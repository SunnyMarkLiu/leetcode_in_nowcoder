package palindrome_partitioning;

import java.util.ArrayList;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s ="aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class Solution {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> result = new Solution().partition("a");
        System.out.print(result.toString());
    }

    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0)
            return result;

        ArrayList<String> curResult = new ArrayList<>();
        dfs(s, 0, curResult, result);
        return result;
    }

    /**
     * 回溯法
     */
    private void dfs(String s,
                     int start,
                     ArrayList<String> curResult,
                     ArrayList<ArrayList<String>> result) {
        // 回溯返回的条件
        if (start == s.length()) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String curSubString = s.substring(start, i + 1);

            if (isPalindrome(curSubString)) {
                // 添加回文
                curResult.add(curSubString);
                // 从 i+1 开始递归
                dfs(s, i + 1, curResult, result);
                // 回溯，移除刚刚添加的元素，也就是回到之前的状态，以便走其他分支
                curResult.remove(curSubString);
            }
        }

    }

    /**
     * 判断是否是回文
     */
    private boolean isPalindrome(String s) {

        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
}