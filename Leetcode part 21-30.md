## 21. insertion_sort_list
```java
package insertion_sort_list;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Sort a linked list using insertion sort.
 *
 * 每次找到最小的节点，摘出来，插入rHead的链表后面，复杂度 O(n^2)
 */
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode rHead = new ListNode(-1);
        rHead.next = head;

        ListNode cur = head;
        ListNode pre = null;    // 当前节点的前面的节点
        ListNode tmp = null;    // 当前节点的前面的节点
        while (cur.next != null) {
            // 如果当前节点比下一个节点值大
            if (cur.val > cur.next.val) {
                // 开始计算cur.next插入的位置，从rHead开始查找
                pre = rHead;
                while (cur.next.val >= pre.next.val)
                    pre = pre.next;

                // 找到 cur.next 该插入的位置 pre的后面后，进行插入
                tmp = cur.next;
                cur.next = cur.next.next;
                tmp.next = pre.next;
                pre.next = tmp;
                // cur.next 插在 pre 和 cur.next.next 之间
            } else {    // 当前节点比下一个节点小，cur 后移
                cur = cur.next;
            }
        }
        return rHead.next;
    }
}
```

## 22. longest_consecutive_sequence
```java
package longest_consecutive_sequence;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an unsorted array of integers, find the length of
 * the longest consecutive elements sequence.
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4].
 * Return its length:4.
 * Your algorithm should run in O(n) complexity.
 */
public class Solution {

    /**
     * 要求时间复杂度为 O(n)，联想到用 n 次调用 HashMap 的查询 O(1) 方法
     *
     * 1.先把数字放到一个集合中，拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，
     * 3.最后比较当前串是不是比当前最大串要长，是则更新。如此继续直到集合为空。
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        Map<Integer, Boolean> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.put(num, false); // 初始化数组的元素都没有被访问
        }

        int longest = 0;

        // 遍历数组的元素
        for (int num : nums) {
            // 如果这个数组被访问过
            if (numMap.get(num))
                continue;

            // 当前 num 开始的连续的最大长度
            int cur_longest = 1;
            int left = num - 1;
            int right = num + 1;

            // 更新数组访问状态
            numMap.put(num, true);

            // 扫描左右两边
            while (numMap.containsKey(left)) {
                // 更新数组访问状态
                numMap.put(left, true);
                left--;
                cur_longest++;
            }

            while (numMap.containsKey(right)) {
                // 更新数组访问状态
                numMap.put(right, true);
                right++;
                cur_longest++;
            }

            if (cur_longest > longest)
                longest = cur_longest;
        }
        return longest;
    }
}
```

## 23. longest_palindromic_substring
```java
package longest_palindromic_substring;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, and there
 * exists one unique longest palindromic substring.
 */
public class Solution {

    /**
     * 中心扩展法
     *
     * 基本思路是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙）
     * 往两边同时进行扫描，直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。
     * 对于每个中心往两边扫描。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return "";

        int maxlen = 0; // 最大长度
        String result = "";

        // 考虑两种情况，bab中间是a(n)，bb中间是间隙(n-1)
        for (int i = 0; i < 2*s.length() - 1; i++) {

            int left = i / 2;
            int right = i / 2;

            // 如果之间为间隙
            if (i % 2 == 1)
                right++;

            String curMaxPalindrome = findMaxPalindrome(s, left, right);
            if (maxlen < curMaxPalindrome.length()) {
                maxlen = curMaxPalindrome.length();
                result = curMaxPalindrome;
            }
        }
        return result;
    }

    private String findMaxPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 前面进行了 left--，注意此处需要left+1
        return s.substring(left + 1, right);
    }
}

```

## 24. max_points_on_a_line
```java
package max_points_on_a_line;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that
 * lie on the same straight line.
 * <p>
 * 给定点两两之间都可以算一个斜率，每个斜率代表一条直线，对每一条直线，
 * 带入所有的点看是否共线并计算个数，这是整体的思路。但是还有两点特殊情况需要考虑，
 * 一是当两个点重合时，无法确定一条直线，但这也是共线的情况，需要特殊处理。
 * 二是斜率不存在的情况，由于两个点(x1, y1)和(x2, y2)的斜率k表示为(y2 - y1) / (x2 - x1)，
 * 那么当x1 = x2时斜率不存在，这种共线情况需要特殊处理。我们需要用到哈希表来记录斜率和共线点个数之间的映射，
 * 其中第一种重合点的情况我们假定其斜率为INT_MIN，第二种情况我们假定其斜率为INT_MAX，这样都可以用map映射了。
 * 我们还需要顶一个变量duplicate来记录重合点的个数，最后只需和哈希表中的数字相加即为共线点的总数
 */
public class Solution {
    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    /**
     * 穷举法
     */
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;

        int result = 0;
        // 第一重循环遍历起始点a
        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;  // 重复点

            // key：斜率，value：点数
            Map<Double, Integer> map = new HashMap<>();
            // 第二重循环遍历剩余点b
            for (int j = i + 1; j < points.length; j++) {
                // 重复点
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicate++;
                } else if (points[i].x == points[j].x) {    // 垂直点
                    map.put(Double.MAX_VALUE, map.getOrDefault(Double.MAX_VALUE, 0) + 1);
                } else if (points[i].y == points[j].y) {
                    map.put(0.0, map.getOrDefault(0.0, 0) + 1);
                } else {
                    // 注意此处的分子先转为 double
                    double slope = (double) (points[i].y - points[j].y) / (points[i].x - points[j].x);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }

            // 计算当前点作为起始点，共线点数
            result = Math.max(result, duplicate);
            for (Map.Entry<Double, Integer> e : map.entrySet()) {
                // 此处很巧妙，如果此时的斜率数最多，如果再次出现依然是最多的
                result = Math.max(result, e.getValue() + duplicate);
            }
        }
        return result;
    }
}

```

## 25. maximum_depth_of_binary_tree
```java
package maximum_depth_of_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth2(TreeNode root) {
        return helper(root);
    }

    /**
     * 递归的方式实现
     */
    private int helper(TreeNode root) {
        if (root == null)
            return 0;

        // 如果只存在一个左右节点
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }

    /**
     * 层次遍历，保存最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);

        int depth = 0;
        while (!levelQueue.isEmpty()) {
            int cur_level_size = levelQueue.size();
            depth++;

            for (int i = 0; i < cur_level_size; i++) {
                TreeNode node = levelQueue.poll();
                if (node.left != null)
                    levelQueue.offer(node.left);
                if (node.right != null)
                    levelQueue.offer(node.right);
            }
        }
        return depth;
    }
}
```

## 26. merge_two_sorted_lists
```java
package merge_two_sorted_lists;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 辅助头节点
        ListNode head = new ListNode(-1);
        ListNode node = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }

        // 剩余的直接拼接
        if (l1 != null)
            node.next = l1;
        if (l2 != null)
            node.next = l2;

        return head.next;
    }
}
```

## 27. palindrome_partitioning
```java
package palindrome_partitioning;

import java.util.ArrayList;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s ="aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class Solution {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> result = new Solution().partition("a");
        System.out.print(result.toString());
    }

    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0)
            return result;

        ArrayList<String> curResult = new ArrayList<>();
        dfs(s, 0, curResult, result);
        return result;
    }

    /**
     * 回溯法
     */
    private void dfs(String s,
                     int start,
                     ArrayList<String> curResult,
                     ArrayList<ArrayList<String>> result) {
        // 回溯返回的条件
        if (start == s.length()) {
            result.add(new ArrayList<>(curResult));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String curSubString = s.substring(start, i + 1);

            if (isPalindrome(curSubString)) {
                // 添加回文
                curResult.add(curSubString);
                // 从 i+1 开始递归
                dfs(s, i + 1, curResult, result);
                // 回溯，移除刚刚添加的元素，也就是回到之前的状态，以便走其他分支
                curResult.remove(curSubString);
            }
        }

    }

    /**
     * 判断是否是回文
     */
    private boolean isPalindrome(String s) {

        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j))
                return false;
        }
        return true;
    }
}
```

## 28. pascals_triangle
```java
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
```

## 29. pascals_triangle_ii
```java
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

```

## 30. path_sum
```java
package path_sum;


/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree andsum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path5->4->11->2which sum is 22.
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return dfs(root, sum);
    }

    /**
     * 先序遍历的思想，从根节点开始一直到叶子节点，判断和是否为sum
     */
    private boolean dfs(TreeNode root, int sum) {
        if (root == null)
            return false;

        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val)
            return true;

        // 非叶子节点，递归其左右子树
        return dfs(root.left, sum - root.val) || dfs(root.right, sum - root.val);
    }
}
```
