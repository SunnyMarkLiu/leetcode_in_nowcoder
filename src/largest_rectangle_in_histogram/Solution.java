package largest_rectangle_in_histogram;

import java.util.Arrays;
import java.util.Stack;

/**
 * 容器装水问题
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1, find the area of largest rectangle
 * in the histogram.
 */
public class Solution {

    /**
     * 暴力搜索，从左到右扫描边界，针对每个边界扫描左边的矩阵，计算面积，
     * 注意保留最小高度和最大面积，时间复杂度 O(n^2)
     */
    public int largestRectangleArea1(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        int maxArea = 0;
        for (int boundary = 0; boundary < height.length; boundary++) {
            int minHeight = height[boundary];

            // 从右边界到左扫描计算面积，保存最大值
            for (int left = boundary; left >= 0; left--) {
                minHeight = Math.min(minHeight, height[left]);
                int area = minHeight * (boundary - left + 1);
                if (area > maxArea)
                    maxArea = area;
            }
        }
        return maxArea;
    }

    /**
     * 暴力搜索优化的地方，只对合适的右边界（峰顶），往左遍历面积。
     * 对于 boundary 递增序列的情况，可 continue
     */
    public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        int maxArea = 0;
        for (int boundary = 0; boundary < height.length; boundary++) {

            // 暴力搜索优化的地方，对于 boundary 递增序列的情况，可 continue
            if (boundary < height.length - 1 && height[boundary] <= height[boundary + 1])
                continue;

            int minHeight = height[boundary];

            // 从右边界到左扫描计算面积，保存最大值
            for (int left = boundary; left >= 0; left--) {
                minHeight = Math.min(minHeight, height[left]);
                int area = minHeight * (boundary - left + 1);
                if (area > maxArea)
                    maxArea = area;
            }
        }
        return maxArea;
    }

    /**
     * 维护一个栈，用来保存递增序列，相当于上面那种方法的找局部峰值。我们可以看到，
     * 直方图矩形面积要最大的话，需要尽可能的使得连续的矩形多，并且最低一块的高度要高。
     * 有点像木桶原理一样，总是最低的那块板子决定桶的装水量。那么既然需要用单调栈来做，
     * 首先要考虑到底用递增栈，还是用递减栈来做。我们想啊，递增栈是维护递增的顺序，
     * 当遇到小于栈顶元素的数就开始处理，而递减栈正好相反，维护递减的顺序，当遇到大于
     * 栈顶元素的数开始处理。那么根据这道题的特点，我们需要按从高板子到低板子的顺序处理，
     * 先处理最高的板子，宽度为1，然后再处理旁边矮一些的板子，此时长度为2，因为之前的高
     * 板子可组成矮板子的矩形 ，因此我们需要一个递增栈，当遇到大的数字直接进栈，而当遇到
     * 小于栈顶元素的数字时，就要取出栈顶元素进行处理了，那取出的顺序就是从高板子到矮板子了，
     * 于是乎遇到的较小的数字只是一个触发，表示现在需要开始计算矩形面积了，为了使得最后一块
     * 板子也被处理，这里用了个小trick，在高度数组最后面加上一个0，这样原先的最后一个
     * 板子也可以被处理了。
     */
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