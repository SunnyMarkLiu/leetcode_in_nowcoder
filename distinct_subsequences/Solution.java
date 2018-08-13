package distinct_subsequences;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (ie,"ACE"is a subsequence of"ABCDE"while"AEC"is not).
 *
 * 只通过删除（而不是重新排序）S中的字符就可以生成字符串T的方法有多少？
 *
 * Here is an example:
 * S ="rabbbit", T ="rabbit"
 *
 * Return3.
 *
 * 看到有关字符串的子序列或者配准类的问题，首先应该考虑的就是用动态规划求解，这个应成为条件反射
 */
public class Solution {

    /**
     *      S（source）
     *   Ø r a b b b i t
     * Ø 1 1 1 1 1 1 1 1
     * r 0 1 1 1 1 1 1 1
     * a 0 0 1 1 1 1 1 1
     * b 0 0 0 1 2 3 3 3
     * b 0 0 0 0 1 3 3 3
     * i 0 0 0 0 0 0 3 3
     * t 0 0 0 0 0 0 0 3
     *
     * 首先，若原字符串和子序列都为空时，返回1，因为空串也是空串的一个子序列。
     * 若原字符串不为空，而子序列为空，也返回1，因为空串也是任意字符串的一个子序列。
     * 而当原字符串为空，子序列不为空时，返回0，因为非空字符串不能当空字符串的子序列。
     * 理清这些，二维数组dp的边缘便可以初始化了，下面只要找出递推式，就可以更新整个dp数组了。
     * 我们通过观察上面的二维数组可以发现，
     * 当更新到 dp[i][j] 时，dp[i][j] >= dp[i][j - 1] 总是成立，
     * 再进一步观察发现，
     * 当 T[i - 1] == S[j - 1] 时，dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]，
     * 若不等， dp[i][j] = dp[i][j - 1]，
     * 所以，综合以上，递推式为：
     *
     * dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1] : 0)
     */
    public int numDistinct(String S, String T) {
        if (S == null || T == null)
            return 0;

        int row = T.length() + 1;
        int col = S.length() + 1;
        int[][] dp = new int[row][col];
        // 初始化 dp 的第一行、第一列
        for (int i=0; i < row; i++) {
            dp[i][0] = 0;
        }
        for (int i=0; i < col; i++) {
            dp[0][i] = 1;
        }

        for (int i=1; i<row; i++) {
            for (int j = 1; j < col; j++) {
                // T[i - 1] == S[j - 1]
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[row-1][col-1];
    }
}
