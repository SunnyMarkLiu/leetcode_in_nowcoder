package permutations;

import java.util.ArrayList;

/**
 * 数组的全排列问题
 * Given a collection of numbers, return all possible permutations.
 *
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2], and [3,2,1].
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (num == null || num.length == 0)
            return result;
        backtracking(num, new ArrayList<>(), result);
        return result;
    }

    private void backtracking(int[] num,
                              ArrayList<Integer> curResult,
                              ArrayList<ArrayList<Integer>> result) {
        if (curResult.size() == num.length) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        // 注意这里一直从 0 开始，subset 中从 start 开始
        for (int i = 0; i < num.length; i++) {
            // 排列中不包含重复值
            if (curResult.contains(num[i]))
                continue;
            curResult.add(num[i]);
            backtracking(num, curResult, result);
            curResult.remove(curResult.size() - 1);
        }
    }
}