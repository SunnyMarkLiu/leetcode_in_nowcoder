package combinations;

import java.util.ArrayList;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [1，2，3，4]
 *
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Solution {

    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    /**
     * 回溯法
     */
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        if (n == 0 || k == 0)
            return result;

        backtracking(n, k, 1, new ArrayList<>());
        return result;
    }

    /**
     * 回溯法
     *
     * @param start      当前回溯的开始数字
     * @param subCombine 当前回溯的 list 结果
     */
    private void backtracking(int n, int k, int start, ArrayList<Integer> subCombine) {
        // 回溯返回条件
        if (k < 0) return;

        if (k == 0) { // 选择完所有数字
            result.add(new ArrayList<>(subCombine));
            return;
        }

        // 从 start 开始，combine 的 k - 1
        for (int i = start; i <= n; i++) {
            subCombine.add(i);
            // 要匹配的 k - 1，从 i 的下一个数开始
            backtracking(n, k - 1, i + 1, subCombine);
            // 回溯
            subCombine.remove(subCombine.size() - 1);
        }

    }
}