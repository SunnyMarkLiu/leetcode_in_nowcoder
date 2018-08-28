package combination_sum_ii;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 一个正数数组 candidate[],一个目标值target，寻找里面所有的不重复组合，让其和等于target
 * 数组里的数字最多只能使用一次。
 *
 * 注意这种情况：candidate = [1,1], target=1，会给出 [[1],[1]] 的答案，需要去重
 */
public class Solution {

    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return result;

        Arrays.sort(candidates);
        backtracking(candidates, target, 0, new ArrayList<>());
        return result;
    }

    private void backtracking(int[] candidates,
                              int target,
                              int start,
                              ArrayList<Integer> curResult) {
        if (target < 0) return;

        if (target == 0) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 过滤数组中重复的数值，针对这种情况：candidate = [1,1], target=1
            if (i > start && candidates[i-1] == candidates[i])
                continue;

            curResult.add(candidates[i]);
            backtracking(candidates, target - candidates[i], i + 1, curResult);
            curResult.remove(curResult.size() - 1);
        }
    }
}
