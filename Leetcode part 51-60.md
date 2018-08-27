## 51. partition_list
```java
package partition_list;

/**
 * Given a linked list and a value x, partition it
 * such that all nodes less than x come before
 * nodes greater than or equal to x.
 * You should preserve the original relative order of
 * the nodes in each of the two partitions.
 *
 * 给定一个单链表和一个x，把链表中小于x的放到前面，大于等于x的放到后面，
 * 每部分元素的原始相对位置不变。
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return1->2->2->3->4->5.
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 把小于x的都挂到 left 链表, 大于等于x的都放到 right 链表，再把 right 和 left 拼接
     */
    public ListNode partition(ListNode head, int x) {
        ListNode left = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode curleft = left;
        ListNode curright = right;

        while (head != null) {
            if (head.val < x) {
                curleft.next = head;
                curleft = curleft.next;
            } else {
                curright.next = head;
                curright = curright.next;
            }
            head = head.next;
        }

        // 拼接 right 和 left，注意需要设置 right 最有一个节点的 next 为 null
        curleft.next = right.next;
        curright.next = null;
        return left.next;
    }
}

```

## 52. remove_duplicates_from_sorted_list
```java
package remove_duplicates_from_sorted_list;

/**
 * Given a **sorted** linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 注意是已经排序的链表，采用双指针法，从头开始扫描
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null)
            return null;

        ListNode pre = head;
        ListNode cur = head.next;

        while (cur != null) {
            if (pre.val == cur.val)
                // 过滤重复值，注意此处有节点断开，说明下 JVM 进行内存的回收
                pre.next = cur.next;
            else
                pre = cur;
            cur = cur.next;
        }
        return head;
    }

    /**
     * 对双指针进行优化
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;

        while (cur != null) {
            // 相邻的节点进行比较
            while (cur.next != null && cur.val == cur.next.val) {
                cur.next = cur.next.next;
            }
            // 扫描整个节点，cur 后移
            cur = cur.next;
        }
        return head;
    }
}
```

## 53. remove_duplicates_from_sorted_list_ii
```java
package remove_duplicates_from_sorted_list_ii;

/**
 * Given a **sorted** linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 同样采用双指针法，只是现在要把前驱指针的 next 指向下一个不重复的元素中，
     * 如果找到不重复元素，则把前驱指针指向该元素，否则删除此元素。
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;

        // 由于涉及到可能修改头节点，因此定义 dummy 节点
        ListNode dummy = new ListNode(head.val - 1); // tricky, 创建的节点和head 不想等
        dummy.next = head;

        // 定义两个指针
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }
            // 如果不存在重复节点，则 pre.next == cur
            if (pre.next == cur)
                pre = pre.next;
            else // 过滤重复节点
                pre.next = cur.next;

            cur = cur.next;
        }
        return dummy.next;
    }
}
```

## 54. largest_rectangle_in_histogram
```java
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
```

## 55. maximal_rectangle
```java
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
```

## 56. search_in_rotated_sorted_array
```java
package search_in_rotated_sorted_array;

/**
 * 旋转数组的查找、类似旋转数组的最小值
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index,
 * otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */
public class Solution {

    /**
     * 采用二分查找，关键是决定查找的左右分支
     *
     * 先判断哪个分支有序，再判断 target 属于哪个分支
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0)
            return -1;

        int left = 0;
        int right = A.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (A[mid] == target)
                return mid;

            // 中间值大于最左边的值，则左分支递增有序，注意此处的 <=
            if (A[left] <= A[mid]) {
                // 注意此处 A[left] <= target
                if (A[left] <= target && target < A[mid]) {  // target 在左分支
                    right = mid - 1;
                } else { // target 在右分支
                    left = mid + 1;
                }
            } else { // 右分支递增有序
                // 注意此处 target <= A[right]
                if (A[mid] < target && target <= A[right]) {  // target 在右分支
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}

```

## 57. search_in_rotated_sorted_array_ii
```java
package search_in_rotated_sorted_array_ii;

/**
 * 旋转数组的查找，数组存在重复值
 */
public class Solution {
    /**
     * 同样采用二分搜索：
     * 1、确定哪个分支是有序的，再利用有序分支判断 target 属于哪个分支
     * 2、注意如果 left 和 mid 值重复，说明左分支都是重复的,left++
     */
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0)
            return false;

        int left = 0;
        int right = A.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (A[mid] == target)
                return true;

            // 中间值和左右值都相等的情况，（比如：[1,3,1,1,1]查找 3）3 在左右分支均合理，此时只能遍历数组
            if (A[left] == A[mid] && A[mid] == A[right]) {
                // 此处可以优化，出现左中右相等的情况时，left++，right--，肯定会找到不满足左中右相等的情况
                left++;
                right--;
                // for (int a : A) {
                //     if (a == target) return true;
                // }
                // return false;
            }
            // 以下就是 search_in_rotated_sorted_array 的解法，注意此处的 A[left] <= A[mid]
            else if (A[left] <= A[mid]) {  // 左分支有序
                if (A[left] <= target && target < A[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {  // 右分支有序
                if (A[mid] < target && target <= A[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return false;
    }
}
```

## 58. remove_duplicates_from_sorted_array
```java
package remove_duplicates_from_sorted_array;

/**
 * Given a sorted array, remove the duplicates in place such
 * that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do
 * this in place with constant memory.
 * For example,
 * Given input array A =[1,1,2],
 * Your function should return length =2, and A is now[1,2].
 */
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        // count 用于记录有效数的个数，初始化第一个数肯定满足，从 1 开始
        int count = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i-1])
                A[count++] = A[i];
        }
        return count;
    }
}
```

## 59. remove_duplicates_from_sorted_array_ii
```java
package remove_duplicates_from_sorted_array_ii;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array A =[1,1,1,2,2,3],
 * Your function should return length =5, and A is now[1,1,2,2,3].
 */
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < A.length; i++) {
            // 满足重复 3 次及以上的,继续查找该重复的值，直到找到该重复值的最后一个值，接下来替换 count 下表的值
            if (i > 0 && i < A.length - 1 && A[i-1] == A[i] && A[i] == A[i+1])
                continue;

            A[count++] = A[i];
        }
        return count;
    }
}
```

## 60. word_search
```java
package word_search;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class Solution {

    private boolean[][] visited;

    /**
     * DFS + 回溯法
     * 定义一个 visited 数组，标记数组元素是否访问。然后遍历数组，以每个元素为起点开始 DFS 查找
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0)
            return false;

        // 标记访问过的char
        visited = new boolean[board.length][board[0].length];
        // 遍历char数组,以里面每一个board[i][j]作为dfs的起始点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果数组里能匹配word的第一个字母 &&能匹配剩下的
                if (word.charAt(0) == board[i][j] && dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    /**
     * 以（i，j）为起点 DFS 递归的检测剩下的字符，matchedIndex 记录匹配的长度，
     * 长度和 word 相等则匹配成功。
     */
    private boolean dfs(char[][] board, String word, int i, int j, int matchedIndex) {
        // true 返回条件: matchedIndex 长度和word一样
        if (matchedIndex == word.length())
            return true;

        // false 返回条件: ij 越界 / 元素不相等 / 当前元素已经访问过
        if (i >= board.length || i < 0 || j < 0 || j >= board[0].length ||
                board[i][j] != word.charAt(matchedIndex) || visited[i][j])
            return false;

        // 标记当前 （i，j）访问过
        visited[i][j] = true;
        // 递归判断左右上下分支
        if (dfs(board, word, i - 1, j, matchedIndex + 1) ||
            dfs(board, word, i + 1, j, matchedIndex + 1) ||
            dfs(board, word, i, j - 1, matchedIndex + 1) ||
            dfs(board, word, i, j + 1, matchedIndex + 1))
            return true;

        // 回溯，清除 （i，j）访问过的标记
        visited[i][j] = false;
        return false;
    }
}
```
