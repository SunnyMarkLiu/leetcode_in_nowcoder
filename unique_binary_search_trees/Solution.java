package unique_binary_search_trees;

/**
 * Given n, how many structurally unique BST's
 * (binary search trees) that store values 1...n?
 *
 * 思路：
 *     考虑根节点，设对于任意根节点k，有f(k)种树的可能。
 *     比k小的k-1个元素构成k的左子树。则左子树有f(k-1)种情况。
 *     比k大的n-k个元素构成k的右子树。则右子树有f(n-k)种情况。
 *     易知，左右子树相互独立，所以f(k)=f(k-1)*f(n-k)。
 *     综上，对于n，结果为k取1,2,3,...,n时，所有f(k)的和。
 */
public class Solution {

    /**
     * 递归的方式
     */
    public int numTrees1(int n) {
        if (n <= 1)
            return 1;

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int leftResult = numTrees1(i - 1);
            int rightResult = numTrees1(n - i);
            // 注意求和的方式
            result += leftResult * rightResult;
        }
        return result;
    }

    /**
     * 动态规划
     *
     * n = 0, f(0) = 1
     * n = 1, f(1) = 1
     * 针对k有 f(k)=f(k-1)*f(n-k)，所以对应的 f(n) 就是 f(k) 的求和
     */
    public int numTrees(int n) {
        if (n < 0)
            return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int k = 1; k <= i; k++) {
                int dp_k = dp[k - 1] * dp[i - k];
                dp[i] += dp_k;
            }
        }
        return dp[n];
    }
}
