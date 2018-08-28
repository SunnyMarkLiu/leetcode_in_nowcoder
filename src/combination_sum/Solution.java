package combination_sum;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 一个正数数组candidate[],一个目标值target，寻找里面所有的不重复组合，让其和等于target
 * 数组里的数字可以多次使用。
 *
 * Note: 注意数值均大于 0，结果的每个数组排序，结果不包含重复数组
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 */
public class Solution {

    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0)
            return result;

        Arrays.sort(candidates);
        backtracking(candidates, target, 0, new ArrayList<>());
        return result;
    }

    /**
     * 回溯法
     */
    private void backtracking(int[] candidates,
                              int target,
                              int start,    // 从不同的 start 开始，防止最后的结果用重复的
                              ArrayList<Integer> curResult) {
        if (target < 0) return;

        // 此时求和成功
        if (target == 0) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            curResult.add(candidates[i]);
            // 注意 start 从 i 开始，因为选取的数字可重复
            backtracking(candidates, target - candidates[i], i, curResult);
            // 回溯
            curResult.remove(curResult.size() - 1);
        }

    }
}