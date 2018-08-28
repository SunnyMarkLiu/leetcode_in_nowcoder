package pascals_triangle;

import java.util.ArrayList;

/**
 * 杨辉三角问题
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 */
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (numRows <= 0)
            return result;

        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> rowResult = new ArrayList<>();
            // 添加开头的 1
            rowResult.add(1);
            for (int j = 1; j < i; j++) {
                // result.get(i-1) 表示上一行的结果
                rowResult.add(result.get(i-1).get(j-1) + result.get(i - 1).get(j));
            }
            // 添加末尾的 1
            if (i > 0)
                rowResult.add(1);
            result.add(rowResult);
        }
        return result;
    }
}