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
