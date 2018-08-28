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

## 68. minimum_window_substring
```java
package minimum_window_substring;

import java.util.HashMap;
import java.util.Map;

/**
 * 从母串S中找到这样一个子串W，这个子串包含目标T串中的所有字符，并且该子串的长度最短。
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S ="XADOBECODEBANC"
 * T ="ABC"
 *
 * Minimum window is"BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string"".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length())
            return "";

        // HashMap的key为t中各个字符，value为对应字符个数
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // minLeft为最小窗口左下标，minLen为最小长度，count用来计数
        int minLeft = 0, minLen = s.length() + 1, count = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                // 如果map.get(c)说明t中还有字符没有包含，计数器+1
                if (map.get(c) > 0){
                    count++;
                }
                map.put(c, map.get(c) - 1);
            }
            // 如果left到i中包含t中所有字符
            while (count == t.length()) {
                if (i - left + 1 < minLen) {
                    minLeft = left;
                    minLen = i - left + 1;
                }
                c = s.charAt(left);
                // 去除左边无效的不符合的字符，试图缩短 window 的大小
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                    if (map.get(c) > 0)
                        count--;
                }
                left++;
            }
        }

        if (minLen > s.length())
            return "";

        return s.substring(minLeft, minLeft + minLen);

    }
}
```

## 69. sort_colors
```java
package sort_colors;

/**
 * 将数组排序，数组中只有三个元素0,1,2，排列后的顺序为 0 1 2 (red, white, and blue)
 */
public class Solution {

    /**
     * 方法一：计数排序
     */
    public void sortColors2(int[] A) {
       int[] count = new int[3];

       // 计数
       for (int i=0; i< A.length; i++)
           count[A[i]]++;

       int idx = 0;
       for (int i=0; i < 3; i++) {
           for (int j = 0; j < count[i]; j++)
               A[idx++] = i;
       }

    }

    /**
     * 双指针法，分别从原数组的首尾往中心移动
     * 定义 red 指针指向开头位置，blue 指针指向末尾位置
     * 从头开始遍历原数组：
     *    如果遇到 0，则属于左半边，和 red 指向的值交换，此时一个red确认，然后red++；
     *    如果遇到 1，不交换，继续遍历；
     *    如果遇到 2，则属于右半边，和 blue 指向的值交换，此时一个 blue 确认，所以 blue--，
     *              但由于交换的i不知道大小所以 i--
     */
    public void sortColors(int[] A) {
        if (A == null) return;

        int red = 0;
        int blue = A.length - 1;

        for (int i = 0; i <= blue; i++) {
            if (A[i] == 0) {
                int tmp = A[i];
                A[i] = A[red];
                A[red] = tmp;
                red++;
            }
            if (A[i] == 2) {
                int tmp = A[i];
                A[i] = A[blue];
                A[blue] = tmp;
                i--;
                blue--;
            }
        }
    }
}
```

## 70. search_a_2d_matrix
```java
package search_a_2d_matrix;

/**
 * m x n matrix: Integers in each row are sorted from left to right.
 *
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 */
public class Solution {

    /**
     * 从左下角开始检索，如果 target 比左下角的值小向上，比左下角值大向右
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = rows - 1, j = 0; i >= 0 && j < cols;) {
            if (matrix[i][j] == target)
                return true;

            else if (target < matrix[i][j])
                i--;
            else
                j++;
        }
        return false;
    }
}
```

## 71. set_matrix_zeroes
```java
package set_matrix_zeroes;

/**
 * 将矩阵中有 0 值的行列都设置为 0
 *
 * 直接的思路：创建一个和 matrix 一样大小的矩阵，遍历 matrix，空间复杂度 O(mn)
 *   => 创建两个数组，一个标记哪一行有 0，一个标记那一列有 0，空间复杂度 O(m+n)
 *   => 直接用 matrix 的第一行和第一列作为标记数组，不用创建新的数组， O(1)
 */
public class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null)
            return;

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean rowZero = false;
        boolean colZero = false;

        // 判断第一行和第一列是否有零，最后用于更新第一行和第一列
        for (int i=0; i<rows; i++) {
            if (matrix[i][0] == 0) {
                rowZero = true;
                break;
            }
        }

        for (int i=0; i<cols; i++) {
            if (matrix[0][i] == 0) {
                colZero = true;
                break;
            }
        }

        // 第二行和第二列遍历数组，在第一行和第一列标记剩下的行列0的情况
        for (int i=1; i<rows; i++) {
            for (int j=1; j<cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // 标记第 i 行出现 0
                    matrix[0][j] = 0; // 标记第 j 列出现 0
                }
            }
        }

        // 根据第一行和第一列是否有 0 设置 matrix
        for (int i=1; i<rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        // 设置第一行第一列
        if (rowZero)
            for (int i = 0; i < rows; i++)
                matrix[i][0] = 0;

        if (colZero)
            for (int i = 0; i < cols; i++)
                matrix[0][i] = 0;
    }
}
```
