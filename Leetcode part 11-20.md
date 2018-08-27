## 11. Minimum_Depth_of_Binary_Tree
```java

package Minimum_Depth_of_Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 递归的方式实现, 比较左右子树的深度
     */
    public int runRecursion(TreeNode root) {
        if (root == null)
            return 0;

        // 如果 root 的左右子树都存在，则递归获取左右子树的 min + 1
        if (root.left != null && root.right != null)
            return Math.min(runRecursion(root.left), runRecursion(root.right)) + 1;

        // 如果当前结点只存在一个左右结点，选择有叶子结点的分支，即 depth 较大的，同时注意加上当前的结点 + 1
        return Math.max(runRecursion(root.left), runRecursion(root.right)) + 1;
    }

    /**
     * 层次遍历，找到第一个左右节点都是 null 的叶子节点，直接返回此时的 depth
     */
    public int run(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;

        int depth = 0;
        // 层次遍历，先进先出，采用队列
        Queue<TreeNode> levelQueue = new LinkedList<>();
        levelQueue.offer(root);

        while (!levelQueue.isEmpty()) {
            // 当前待遍历 level 的节点数
            int cur_level_size = levelQueue.size();
            depth++;

            // 遍历当前层
            for (int i=0; i < cur_level_size; i++) {
                TreeNode node = levelQueue.poll();
                // 如果此 node 为叶子节点，直接返回此时的 depth
                if (node.left == null && node.right == null)
                    return depth;

                if (node.left != null)
                    levelQueue.offer(node.left);

                if (node.right != null)
                    levelQueue.offer(node.right);
            }
        }
        return 0;
    }
}

```

## 12. balanced_binary_tree
```java
package balanced_binary_tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as
 * a binary tree in which the depth of the two subtrees of every node
 * never differ by more than 1.
 */
public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Map<TreeNode, Integer> subTreeHeight = new HashMap<>();

    /**
     * 递归法
     * 递归获取左子树和右子树的深度，判断差值是否大于1,
     * 注意 getMaxHeight 存在大量重复计算，采用全局变量进行优化
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null)
            return true;

        int leftH = getMaxHeight(root.left);
        int rightH = getMaxHeight(root.right);
        if (Math.abs(leftH - rightH) > 1)
            return false;

        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    private int getMaxHeight2(TreeNode root) {

        if (root == null)
            return 0;

        if (subTreeHeight.containsKey(root))
            return subTreeHeight.get(root);

        int leftH = getMaxHeight2(root.left) + 1;
        int rightH = getMaxHeight2(root.right) + 1;
        // 计算 root 为根节点的深度
        int height = leftH > rightH ? leftH : rightH;
        subTreeHeight.put(root, height);
        return height;
    }

    /**
     * 假设只要高度大于1，就将每层的返回值设定为-1，如果最终返回到root的值没有-1，代表高度都小于1，
     * 但凡返回了-1，就证明这颗树在某一层出现unbalance的情况。
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        return getMaxHeight(root) != -1;
    }

    private int getMaxHeight(TreeNode root) {
        if (root == null)
            return 0;

        int leftH = getMaxHeight(root.left);
        int rightH = getMaxHeight(root.right);

        // 如果此时不平衡，返回 -1，或者左子树或右子树为-1，也直接返回 -1
        if ((Math.abs(leftH - rightH) > 1) || (leftH == -1) || rightH == -1)
            return -1;

        // 返回此时的深度
        return Math.max(leftH, rightH) + 1;
    }
}
```

## 13. binary_tree_maximum_path_sum
```java
package binary_tree_maximum_path_sum;

/**
 *
 Given a binary tree, find the maximum path sum.

 The path may start and end at any node in the tree.

 For example:
 Given the below binary tree,

   1
  / \
 2   3

 Return6.


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

    /**
     * 在递归函数中，如果当前结点不存在，那么直接返回0。否则就分别对其左右子节点调用递归函数，
     * 由于路径和有可能为负数，而我们当然不希望加上负的路径和，所以我们和0相比，取较大的那个，
     * 就是要么不加，加就要加正数。然后我们来更新全局最大值结果res，就是以左子结点为终点的
     * 最大path之和加上以右子结点为终点的最大path之和，还要加上当前结点值，这样就组成了一个
     * 条完整的路径。而我们返回值是取left和right中的较大值加上当前结点值，因为我们返回值的
     * 定义是以当前结点为终点的path之和，所以只能取left和right中较大的那个值，而不是两个都要
     */
    private Integer result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return result;
    }

    /**
     * 从叶子节点开始到当前结点为终点的 path 的最大路径之和，然后全局路径最大值放在参数中
     */
    private int helper(TreeNode root) {
        if (root == null)
            return 0;

        // 左右子节点调用递归函数，获取当前节点为根节点的最大路径和
        // 注意要和 0 进行比较
        int leftNodeResult = Math.max(helper(root.left), 0);
        int rightNodeResult = Math.max(helper(root.right), 0);
        // 计算全局的result为：当前根节点的值加上左右节点为终点的最大路径和
        result = Math.max(result, root.val + leftNodeResult + rightNodeResult);

        // 返回当前结点为终点的 path 的最大路径之和
        return Math.max(leftNodeResult, rightNodeResult) + root.val;
    }
}

```

## 14. clone_graph
```java
package clone_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

 DFS 和 BFS 遍历
 1)克隆结点 label;
 2)克隆结点与临近结点关系

 深度遍历或者广度优先遍历复制 label，同时用 map 记录复制的node 和对应的 neighbors
 */
public class Solution {

    class UndirectedGraphNode {
        int label;
        ArrayList<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<>(); }
    };

    /**
     * 递归的方式实现 DFS
     */
    public UndirectedGraphNode cloneGraph1(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    /**
     * 递归方式实现深度优先遍历
     */
    private UndirectedGraphNode dfs(UndirectedGraphNode root,
                                    Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        if (root == null)
            return null;

        // 复制root节点
        UndirectedGraphNode cloneRoot = new UndirectedGraphNode(root.label);
        map.put(root, cloneRoot);
        // 遍历 root 的邻居节点
        for (UndirectedGraphNode neighbor : root.neighbors) {
            if (map.containsKey(neighbor)) {
                // 取出来直接添加到新复制的节点的neighbors
                cloneRoot.neighbors.add(map.get(neighbor));
            } else {
                UndirectedGraphNode neighborClone = dfs(neighbor, map);
                cloneRoot.neighbors.add(neighborClone);
            }
        }
        return cloneRoot;
    }

    /**
     * 利用队列实现非递归的 BFS
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null)
            return null;

        // 未访问结点的队列
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        queue.push(node);
        // 原始节点和已复制节点的映射
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        UndirectedGraphNode cloneRoot = new UndirectedGraphNode(node.label);
        map.put(node, cloneRoot);

        while (!queue.isEmpty()) {
            UndirectedGraphNode snode = queue.pop();
            UndirectedGraphNode snodeClone = map.get(snode);
            // 遍历复制 snode 的邻居节点
            for (UndirectedGraphNode neighbor : snode.neighbors) {
                // 如果 neighbor 已经复制，直接取出进行添加
                if (map.containsKey(neighbor)) {
                    snodeClone.neighbors.add(map.get(neighbor));
                } else {
                    // 复制新的邻居节点
                    UndirectedGraphNode cloneNeighbor = new UndirectedGraphNode(neighbor.label);
                    map.put(neighbor, cloneNeighbor);
                    snodeClone.neighbors.add(cloneNeighbor);
                    queue.push(neighbor);
                }
            }
        }
        return cloneRoot;
    }
}

```

## 15. convert_sorted_array_to_binary_search_tree
```java
package convert_sorted_array_to_binary_search_tree;

/**
 * Given an array where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * 把一个有序数组转换成一颗二分查找树
 */
public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 把中间元素转化为根，然后递归构造左右子树。
     */
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0)
            return null;

        return helper(num, 0, num.length - 1);
    }

    /**
     * 0, 1, 2, 3, 4
     */
    private TreeNode helper(int[] num, int left, int right) {
        if (left > right)
            return null;

        // 只剩下一个节点，直接返回
        if (left == right)
            return new TreeNode(num[left]);

        // 中间节点作为根节点
        int mid = left + (right - left + 1) / 2;
        TreeNode root = new TreeNode(num[mid]);

        root.left = helper(num, left, mid - 1);
        root.right = helper(num, mid + 1, right);
        return root;
    }
}

```

## 16. convert_sorted_list_to_binary_search_tree
```java
package convert_sorted_list_to_binary_search_tree;


import java.util.List;

/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * 把有序链表转为二叉搜索树
 */
public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        return helper(head, null);
    }

    private TreeNode helper(ListNode head, ListNode tail) {
        if (head == tail)
            return null;

        // 快慢指针获取链表的中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(head, slow);
        root.right = helper(slow.next, tail);
        return root;
    }

}
```

## 17. copy_list_with_random_pointer
```java
package copy_list_with_random_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 * 复杂链表的复制
 */
public class Solution {
    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        // 先复制原始链表，同时记录 random 映射关系
        Map<RandomListNode, RandomListNode> randomMap = new HashMap<>();

        RandomListNode newHead = new RandomListNode(head.label);
        RandomListNode oldTmp = head.next;
        RandomListNode newTmp = newHead;
        randomMap.put(newHead, head);

        while (oldTmp != null) {
            RandomListNode newNode = new RandomListNode(oldTmp.label);
            randomMap.put(newNode, oldTmp);
            oldTmp = oldTmp.next;
            newTmp.next = newNode;
            newTmp = newTmp.next;
        }

        // 更新 random 内容
        newTmp = newHead;
        while (newTmp != null) {
            newTmp.random = randomMap.get(newTmp).random;
            newTmp = newTmp.next;
        }
        return newHead;
    }
}
```

## 18. distinct_subsequences
```java
package distinct_subsequences;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string by
 * deleting some (can be none) of the characters without disturbing the relative positions
 * of the remaining characters. (ie,"ACE"is a subsequence of"ABCDE"while"AEC"is not).
 *
 * 只通过删除（而不是重新排序）S中的字符就可以生成字符串T的方法有多少？
 *
 * Here is an example:
 * S ="rabbbit", T ="rabbit"
 *
 * Return3.
 *
 * 看到有关字符串的子序列或者配准类的问题，首先应该考虑的就是用动态规划求解，这个应成为条件反射
 */
public class Solution {

    /**
     *      S（source）
     *   Ø r a b b b i t
     * Ø 1 1 1 1 1 1 1 1
     * r 0 1 1 1 1 1 1 1
     * a 0 0 1 1 1 1 1 1
     * b 0 0 0 1 2 3 3 3
     * b 0 0 0 0 1 3 3 3
     * i 0 0 0 0 0 0 3 3
     * t 0 0 0 0 0 0 0 3
     *
     * 首先，若原字符串和子序列都为空时，返回1，因为空串也是空串的一个子序列。
     * 若原字符串不为空，而子序列为空，也返回1，因为空串也是任意字符串的一个子序列。
     * 而当原字符串为空，子序列不为空时，返回0，因为非空字符串不能当空字符串的子序列。
     * 理清这些，二维数组dp的边缘便可以初始化了，下面只要找出递推式，就可以更新整个dp数组了。
     * 我们通过观察上面的二维数组可以发现，
     * 当更新到 dp[i][j] 时，dp[i][j] >= dp[i][j - 1] 总是成立，
     * 再进一步观察发现，
     * 当 T[i - 1] == S[j - 1] 时，dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]，
     * 若不等， dp[i][j] = dp[i][j - 1]，
     * 所以，综合以上，递推式为：
     *
     * dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1] : 0)
     */
    public int numDistinct(String S, String T) {
        if (S == null || T == null)
            return 0;

        int row = T.length() + 1;
        int col = S.length() + 1;
        int[][] dp = new int[row][col];
        // 初始化 dp 的第一行、第一列
        for (int i=0; i < row; i++) {
            dp[i][0] = 0;
        }
        for (int i=0; i < col; i++) {
            dp[0][i] = 1;
        }

        for (int i=1; i<row; i++) {
            for (int j = 1; j < col; j++) {
                // T[i - 1] == S[j - 1]
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[row-1][col-1];
    }
}

```

## 19. evaluate_reverse_polish_notation
```java
package evaluate_reverse_polish_notation;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are+,-,*,/. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class Solution {
    /**
     * 逆波兰表达式求值
     * <p>
     * 遇到数字入栈，遇到符号出栈，计算结果，结果再入栈
     */
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // 如果 tokens[i] 为数字，入栈
            try {
                int num = Integer.parseInt(tokens[i]);
                stack.push(num);
            } catch (NumberFormatException e) {
                // 为操作符,出栈两个，计算结果，结果入栈，注意计算的顺序
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(calcOperate(num1, num2, tokens[i]));
            }
        }
        return stack.pop();
    }

    private int calcOperate(int num1, int num2, String operator) {
        if (operator.equals("+"))
            return num1 + num2;
        else if (operator.equals("-"))
            return num1 - num2;
        else if (operator.equals("*"))
            return num1 * num2;
        else if (operator.equals("/")) {
                if (num2 == 0)
                    throw new RuntimeException("The dividend cannot be 0");
                return num1 / num2;
            }
        else
            throw new RuntimeException(operator + " is not supported");
    }
}
```

## 20. gas_station
```java
package gas_station;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        if (gas == null || cost == null || gas.length == 0 || cost.length == 0)
            return -1;

        int tankLeft = 0;
        int curStepRemain = 0;
        int fromStartRemain = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            // 从 i -> i + 1，可以剩下多少油
            curStepRemain = gas[i] - cost[i];
            fromStartRemain += curStepRemain;
            tankLeft += curStepRemain;

            // 说明从当前 start 走不通，则说明从start到 i 之间的节点开始都走不通
            if (fromStartRemain < 0) {
                fromStartRemain = 0;
                // 从 i 的下一个节点开始
                start = i + 1;
            }
        }

        // 如果最后 tank 有剩余，返回此时的 start
        if (tankLeft >= 0)
            return start;
        return -1;
    }
}
```
