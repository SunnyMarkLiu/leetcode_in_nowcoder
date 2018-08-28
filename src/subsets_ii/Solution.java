package subsets_ii;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a collection of integers that might contain duplicates, S,
 * return all possible subsets.
 *
 * Note:
 *
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 *
 * For example,
 * If S =[1,2,2], a solution is:
 *
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        // 注意先排序
        Arrays.sort(nums);
        backtracking(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtracking(int[] nums,
                              int start,
                              ArrayList<Integer> curResult,
                              ArrayList<ArrayList<Integer>> result) {
        result.add(new ArrayList<>(curResult));
        for (int i = start; i < nums.length; i++) {
            // 过滤重复值
            if (i > start && nums[i-1] == nums[i])
                continue;

            curResult.add(nums[i]);
            backtracking(nums, i+1, curResult, result);
            curResult.remove(curResult.size() - 1);
        }
    }
}