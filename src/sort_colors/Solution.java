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