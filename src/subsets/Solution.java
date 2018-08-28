package subsets;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定数组的全子数组问题, The solution set must not contain duplicate subsets.
 *
 * For example,
 * If S =[1,2,3], a solution is:
 *
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []     空集合也是
 * ]
 */
public class Solution {

    public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return result;

        Arrays.sort(nums);
        backtracking(nums,0, new ArrayList<>(), result);
        return result;
    }

    /**
     * 回溯法
     */
    private void backtracking(int[] nums,
                              int start,
                              ArrayList<Integer> curResult,
                              ArrayList<ArrayList<Integer>> result) {
        result.add(new ArrayList<>(curResult));
        for (int i = start; i < nums.length; i++) {
            curResult.add(nums[i]);
            backtracking(nums, i + 1, curResult, result);
            curResult.remove(curResult.size() - 1);
        }

    }
}