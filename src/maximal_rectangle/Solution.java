package maximal_rectangle;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing all ones and return its area.
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class Solution {

    /**
     * 这道题的二维矩阵每一层向上都可以看做一个直方图，输入矩阵有多少行，就可以形成多少个直方图，
     * 对每个直方图都调用 Largest Rectangle in Histogram 直方图中最大的矩形 中的方法，
     * 就可以得到最大的矩形面积。那么这道题唯一要做的就是将每一层构成直方图，由于题目限定了输入
     * 矩阵的字符只有 '0' 和 '1' 两种，所以处理起来也相对简单。方法是，对于每一个点，
     * 如果是‘0’，则赋0，如果是 ‘1’，就赋 之前的height值加上1。
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int rows = matrix.length;
        int columns = matrix[0].length;

        int maxArea = 0;
        int[] height = new int[columns];
        // 便利每一行，每一行向上连续到底的1构成直方图📊，每个直方图利用 largestRectangleArea 计算面试
        for (int i = 0; i < rows; i++) {
            // 便利当前行的每一列数据，构造直方图
            for (int j = 0; j < columns; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : 1 + height[j];
            }
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();
        // 注意 i 指向待处理的下一个矩形
        int i = 0;
        int maxArea = 0;
        // 末尾添加一个元素 0
        int[] h = new int[height.length + 1];
        h = Arrays.copyOf(height, height.length + 1);
        while(i < h.length){
            // 当前元素大于栈顶元素，stack 中保存递增的下标
            if(stack.isEmpty() || h[stack.peek()] <= h[i]){
                stack.push(i++);
            }else {
                int t = stack.pop();
                int area = 0;
                if (stack.isEmpty())
                    area = h[t] * i;
                else
                    area = h[t] * (i - stack.peek() - 1);

                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }
}