package pascals_triangle_ii;

import java.util.ArrayList;

/**
 * Given an index k, return the k th row of the Pascal's triangle.
 * For example, given k = 3,
 *
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1]
 * ]
 *
 * Return[1,3,3,1].
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
public class Solution {

    public ArrayList<Integer> getRow(int rowIndex) {
        ArrayList<Integer> result = new ArrayList<>();
        if (rowIndex < 0)
            return result;

        result.add(1);  // rowIndex = 0 的情况
        if (rowIndex == 0)
            return result;

        result.add(1);
        if (rowIndex == 1) {
            return result;
        }

        ArrayList<Integer> preRow = result;
        for (int i = 2; i <= rowIndex; i++) {
            ArrayList<Integer> rowResult = new ArrayList<>();
            // 添加开头的 1
            rowResult.add(1);
            for (int j = 1; j < i; j++) {
                rowResult.add(preRow.get(j-1) + preRow.get(j));
            }
            rowResult.add(1);
            preRow = rowResult;
        }
        return preRow;
    }
}
