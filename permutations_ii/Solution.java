package permutations_ii;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 包含重复值的数组的全排列问题
 * Given a collection of numbers that might contain duplicates,
 * return all possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2],[1,2,1], and [2,1,1].
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (num == null || num.length == 0)
            return result;

        // 先对数组进行排序
        Arrays.sort(num);
        backtracking(num, new boolean[num.length], new ArrayList<>(), result);
        return result;
    }

    /**
     * numUsed 标记该元素是否被访问过，回溯时注意设置为 false
     */
    private void backtracking(int[] num,
                              boolean[] numUsed,
                              ArrayList<Integer> curResult,
                              ArrayList<ArrayList<Integer>> result) {
        if (curResult.size() == num.length) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            // 普通 permutations 的过滤，因为从 0 开始，需要判断是否使用过
            if (numUsed[i])
                continue;

            // 如果未使用，但和前面的值有重复（重复值一定相邻的），只需要判断 i-1 和 i 的值是否相等，
            // 如果 i-1 和 i 的值相等，并且 i-1 的值也还没使用过，则当前的 i 可以跳过
            if (i > 0 && num[i] == num[i-1] && !numUsed[i-1])
                continue;

            curResult.add(num[i]);
            numUsed[i] = true;
            backtracking(num, numUsed, curResult, result);
            // 回溯
            curResult.remove(curResult.size() - 1);
            numUsed[i] = false;
        }

    }
}