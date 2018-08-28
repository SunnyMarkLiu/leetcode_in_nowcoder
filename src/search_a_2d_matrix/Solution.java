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