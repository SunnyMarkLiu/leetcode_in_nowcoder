## 61. combinations
```java
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
```

## 62. combination_sum
```java
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
```

## 63. combination_sum_ii
```java
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

```

## 64. subsets
```java
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
```

## 65. permutations
```java
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
```

## 66. subsets_ii
```java
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
```

## 67. permutations_ii
```java
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
```
