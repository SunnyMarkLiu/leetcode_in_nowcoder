## 1. Binary_Tree_Zigzag_Level_Order_Traversal
```java
package Binary_Tree_Zigzag_Level_Order_Traversal;

import java.util.ArrayList;


public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        recursionHelper(result, 0, root);

        return result;
    }

    /**
     * 递归的方式实现层次遍历，注意奇偶 leve 添加数据的顺序
     */
    private void recursionHelper(ArrayList<ArrayList<Integer>> result, int level, TreeNode root) {
        if (root == null)
            return;

        // 遍历新的行
        if (level == result.size()) {
            ArrayList<Integer> newLevelResult = new ArrayList<>();
            // 注意奇偶 leve 添加数据的顺序
            if (level % 2 == 0)
                newLevelResult.add(root.val);
            else
                newLevelResult.add(0, root.val);
            result.add(newLevelResult);
        } else {
            ArrayList<Integer> levelResult = result.get(level);
            // 注意奇偶 leve 添加数据的顺序
            if (level % 2 == 0)
                levelResult.add(root.val);
            else
                levelResult.add(0, root.val);
            result.set(level, levelResult);
        }

        // 递归遍历左右节点
        recursionHelper(result, level + 1, root.left);
        recursionHelper(result, level + 1, root.right);
    }

    /**
     * 非递归的方式，层次遍历，注意奇偶 leve 添加数据的顺序
     */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);
        int level = 0;

        while (!curLevel.isEmpty()) {
            // 当前层的遍历结果
            ArrayList<Integer> curResult = new ArrayList<>();
            // 保存下一层的节点
            ArrayList<TreeNode> nextLevel = new ArrayList<>();

            for (TreeNode node : curLevel) {
                if (level % 2 == 0)
                    curResult.add(node.val);
                else
                    curResult.add(0, node.val);

                if (node.left != null)
                    nextLevel.add(node.left);

                if (node.right != null)
                    nextLevel.add(node.right);
            }
            level += 1;
            result.add(curResult);
            curLevel = nextLevel;
        }
        return result;
    }
}
```

## 2. Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal
```java
package Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal;

public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (inorder == null || preorder == null ||
                inorder.length == 0 || preorder.length == 0)
            return null;

        return buildHelper(preorder, 0, preorder.length-1,
                            inorder,0, inorder.length-1);
    }

    private TreeNode buildHelper(int[] preorder, int preLeft, int preRight,
                                 int[] inorder, int inLeft, int inRight) {
        if (preLeft > preRight)
            return null;

        // 根据 preorder 的第一个节点创建根节点
        TreeNode root = new TreeNode(preorder[preLeft]);

        if (preLeft == preRight)
            return root;

        // 根据此 root 确定在中序遍历中的位置
        int rootIndex = -1;
        for (int i=inLeft; i <= inRight; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }
        // rootIndex 左边的部分即为 root 的左子树
        int leftTreeSize = rootIndex - inLeft;

        // 递归构造左子树和右子树
        root.left = buildHelper(preorder, preLeft + 1, preLeft + leftTreeSize,
                                inorder, inLeft, rootIndex - 1);
        root.right = buildHelper(preorder, preLeft + leftTreeSize + 1, preRight,
                                 inorder, rootIndex + 1, inRight);

        return root;
    }

}
```

## 3. Binary_Tree_Level_Order_Traversal
```java
package Binary_Tree_Level_Order_Traversal;

import java.util.ArrayList;


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
     * 递归的方式实现
     */
    public ArrayList<ArrayList<Integer>> levelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        recursionHelper(result, 0, root);
        return result;
    }

    private void recursionHelper(ArrayList<ArrayList<Integer>> result, int level, TreeNode root) {
        if (root == null)
            return;

        // 遍历新的行
        if (level == result.size()) {
            ArrayList<Integer> levelResult = new ArrayList<>();
            levelResult.add(root.val);
            result.add(levelResult);
        } else {    // 已经遍历过的 level 行
            ArrayList<Integer> levelResult = result.get(level);
            levelResult.add(root.val);
            result.set(level, levelResult);
        }
        // 遍历下一行
        recursionHelper(result, level + 1, root.left);
        recursionHelper(result, level + 1, root.right);
    }

    /**
     *  非递归的方式，遍历当前层同时保存对应的左右节点
     */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            // 当前层的遍历结果
            ArrayList<Integer> curResult = new ArrayList<>();
            // 保存下一层的节点
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : curLevel) {
                curResult.add(node.val);
                if (node.left != null)
                    nextLevel.add(node.left);

                if (node.right != null)
                    nextLevel.add(node.right);
            }
            result.add(curResult);
            curLevel = nextLevel;
        }
        return result;
    }
}
```

## 4. Binary_Tree_Preorder_Traversal
```java
package Binary_Tree_Preorder_Traversal;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Definition for binary tree
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
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
     * 递归的方式实现二叉树的前序遍历
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即树的深度，O(logn)
     */
    public ArrayList<Integer> preorderTraversal1(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        recursionHelper(result, root);
        return result;
    }

    private void recursionHelper(ArrayList<Integer> result, TreeNode root) {

        if (root == null)
            return;

        // 访问根节点
        result.add(root.val);

        // 访问左子树
        if (root.left != null)
            recursionHelper(result, root.left);

        // 访问右子树
        if (root.right != null)
            recursionHelper(result, root.right);
    }

    /**
     * 迭代（iteratively）的方式实现，用栈模拟实现递归，
     * 因此算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     */
    public ArrayList<Integer> preorderTraversal(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (stack.size() > 0) {
            // 访问 root
            TreeNode curr_node = stack.pop();
            result.add(curr_node.val);

            // 注意栈访问的顺序，先 push right node
            if (curr_node.right != null)
                stack.add(curr_node.right);

            if (curr_node.left != null)
                stack.add(curr_node.left);
        }

        return result;
    }
}

```

## 5. Binary_Tree_Inorder_Traversal
```java
package Binary_Tree_Inorder_Traversal;

import java.util.ArrayList;
import java.util.Stack;

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
     * 递归的方式实现，算法的时间复杂度是O(n),
     * 而空间复杂度则是递归栈的大小，即树的深度，O(logn)
     */
    public ArrayList<Integer> inorderTraversal1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        recursionHelper(result, root);
        return result;
    }

    private void recursionHelper(ArrayList<Integer> result, TreeNode root) {
        if (root == null)
            return;

        // 访问左子树
        if (root.left != null)
            recursionHelper(result, root.left);
        // 访问根节点
        result.add(root.val);
        // 访问右子树
        if (root.right != null)
            recursionHelper(result, root.right);

    }

    /**
     * 迭代（iteratively）的方式实现，用栈模拟实现递归，
     * 因此算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     */
    public ArrayList<Integer> inorderTraversal(TreeNode root) {

        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode tmpNode = root;

        while (tmpNode != null || stack.size() > 0) {
            if (tmpNode != null) { // 当前节点不为空，继续遍历左节点
                stack.add(tmpNode);
                tmpNode = tmpNode.left;
            } else {    // 访问到最左节点之后，从 stack 中 pop 取数据
                tmpNode = stack.pop();
                result.add(tmpNode.val);
                // 访问右子树
                tmpNode = tmpNode.right;
            }
        }
        return result;
    }
}
```

## 6. Binary_Tree_Postorder_Traversal
```java
package Binary_Tree_Postorder_Traversal;

import java.util.ArrayList;
import java.util.Stack;

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
     * 递归的方式实现
     * 算法的时间复杂度是O(n), 而空间复杂度则是递归栈的大小，即树的深度，O(logn)
     */
    public ArrayList<Integer> postorderTraversal1(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        recursionHelper(result, root);
        return result;
    }

    private void recursionHelper(ArrayList<Integer> result, TreeNode root) {
        if (root == null)
            return;

        if (root.left != null)
            recursionHelper(result, root.left);

        if (root.right != null)
            recursionHelper(result, root.right);

        result.add(root.val);
    }

    /**
     * 迭代（iteratively）的方式实现，用栈模拟实现递归，
     * 因此算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn)
     *
     * 先从根往左一直入栈，直到为空，然后判断栈顶元素的右孩子，如果不为空且未被访问过，则从它开始重复左孩子入栈的过程；
     * 否则说明此时栈顶为要访问的节点（因为左右孩子都是要么为空要么已访问过了），出栈然后访问即可，
     * 接下来再判断栈顶元素的右孩子...直到栈空。
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        TreeNode node = root;

        while (stack.size() > 0) {
            // 去除栈顶元素
            TreeNode top = stack.peek();

            // 如果 (当前处理的结点没有左右结点) 或者 (当前结点的左结点已经处理）或者（当前结点的右结点已经处理）

            // 叶子节点，直接访问
            // top 节点的左节点或右节点已经访问过，直接访问 top 节点（后序遍历保证）
            if ((top.left == null && top.right == null) || (top.left == node) || (top.right == node)) {
                result.add(top.val);
                node = stack.pop();
            } else {
                if (top.right != null)
                    stack.add(top.right);

                if (top.left != null)
                    stack.add(top.left);
            }
        }
        return result;
    }
}
```

## 7. Binary_Tree_Level_Order_Traversal_II
```java
package Binary_Tree_Level_Order_Traversal_II;

import java.util.ArrayList;


public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        ArrayList<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            // 遍历当前层的结果
            ArrayList<Integer> curResult = new ArrayList<>();
            // 保存下一层的节点
            ArrayList<TreeNode> nextLevel = new ArrayList<>();

            for (TreeNode node : curLevel) {
                curResult.add(node.val);
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            result.add(0, curResult);
            curLevel = nextLevel;
        }
        return result;
    }
}
```

## 8. Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal
```java
package Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal;


public class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0)
            return null;

        return buildHelper(inorder, 0, inorder.length - 1,
                           postorder, 0, postorder.length - 1);
    }


    private TreeNode buildHelper(int[] inorder, int inLeft, int inRight,
                                 int[] postorder, int posLeft, int posRight) {
        if (inLeft > inRight)
            return null;

        // 根据后续遍历的最后一个元素获取根节点
        TreeNode root = new TreeNode(postorder[posRight]);
        if (inLeft == inRight)
            return root;

        // 获取根节点在中序遍历的位置
        int root_index = -1;
        for (int i = inLeft; i <= inRight; i++) {
            if (inorder[i] == root.val) {
                root_index = i;
                break;
            }
        }
        int leftTreeSize =  root_index - inLeft;

        // 递归构造左右子树
        root.left = buildHelper(inorder, inLeft, root_index - 1,
                                postorder, posLeft, posLeft + leftTreeSize - 1);
        root.right = buildHelper(inorder, root_index + 1, inRight,
                                 postorder, posLeft + leftTreeSize, posRight - 1);
        return root;
    }
}
```

## 9. gas_station
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

## 10. Minimum_Depth_of_Binary_Tree
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

## 11. balanced_binary_tree
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

## 12. distinct_subsequences
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

## 13. longest_consecutive_sequence
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

## 14. convert_sorted_array_to_binary_search_tree
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

## 15. Linked_List_Cycle
```java
package Linked_List_Cycle;

/**
 * Given a linked list, determine if it has a cycle in it.
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

    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode fast = head.next;
        ListNode slow = head;

        while (slow != null && fast != null && fast.next != null) {
            if (fast == slow)
                return true;
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }
}
```

## 16. Linked_List_Cycle_II
```java
package Linked_List_Cycle_II;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, returnnull.
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
     * 判断是否有环，如果有，则返回环中的一个节点
     */
    private ListNode getNodeInLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != null && fast != null && fast.next != null) {
            if (fast == slow)
                return fast;

            fast = fast.next.next;
            slow = slow.next;
        }

        return null;
    }

    /**
     * 检测环的入口节点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;

        // 1. 先判断 list 是否有环
        ListNode loopNode = getNodeInLoop(head);

        if (loopNode == null)
            return null;

        // 2. 统计环的长度
        ListNode node1 = loopNode;
        int loopCount = 1;
        while (node1.next != loopNode) {
            node1 = node1.next;
            loopCount++;
        }

        // 3. 两个指针从头开始，node1 先走 loopCount，再同时走，直到相遇
        node1 = head;
        ListNode node2 = head;

        for (int i = 0; i < loopCount; i++)
            node1 = node1.next;

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        // 此时的相遇点即为环入口点
        return node1;
    }
}
```

## 17. longest_palindromic_substring
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

## 18. convert_sorted_list_to_binary_search_tree
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

## 20. clone_graph
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

## 21. max_points_on_a_line
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

## 22. binary_tree_maximum_path_sum
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

## 23. insertion_sort_list
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

## 24. copy_list_with_random_pointer
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

## 25. single_number_ii
```java
package single_number_ii;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it
 * without using extra memory?
 */
public class Solution {

public int singleNumber(int[] A) {
        int ones = 0;//记录只出现过1次的bits
        int twos = 0;//记录只出现过2次的bits
        int threes;
        for(int i = 0; i < A.length; i++){
            int t = A[i];
            twos |= ones&t;//要在更新ones前面更新twos
            ones ^= t;
            threes = ones&twos;//ones和twos中都为1即出现了3次
            ones &= ~threes;//抹去出现了3次的bits
            twos &= ~threes;
        }
        return ones; 
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

## 27. single_number
```java
package single_number;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it
 * without using extra memory?
 */
public class Solution {

    /**
     * 1.异或满足交换律。
     * 2.相同两个数异或为0。
     * 3.0异或一个数为那个数本身。
     */
    public int singleNumber(int[] A) {
        int result = 0;
        for (int a : A) {
            result ^= a;
        }
        return result;
    }
}

```

## 28. word_break
```java
package word_break;

import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a
 * space-separated sequence of one or more dictionary words.
 *
 * For example, given
 * s ="leetcode",
 * dict =["leet", "code"].
 *
 * Return true because"leetcode"can be segmented as"leet code".
 */
public class Solution {
    /**
     * 动态规划题目的基本思路:
     * 首先我们要决定要存储什么历史信息以及用什么数据结构来存储信息。
     * 然后是最重要的递推式，就是如何从存储的历史信息中得到当前步的结果。
     * 最后我们需要考虑的就是起始条件的值。
     *
     * 接下来我们套用上面的思路来解这道题：
     * 首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，
     * 我们需要一个长度为 n 的布尔数组来存储信息。然后假设我们现在拥有 res[0,...,i-1] 的结果，我们来获得res[i]的表达式。
     * 思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，如果都成立，那么res[i]为true
     *
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) {
            return true;
        }

        // 定义 n+1 长度的标记数组，用于标记从0开始到第 i 元素满足要求
        boolean[] results = new boolean[s.length() + 1];
        // 初始化 s 为空满足条件
        results[0] = true;

        for (int i = 0; i < s.length() + 1; i++) {
            // 判断第 i 个元素，先判断第 i 之前的元素
            for (int j = 0; j < i; j++) {
                // i 之前的 result 为 true，并且当前剩下的 j->i 的子串在 dict 中
                if (results[j] && dict.contains(s.substring(j, i))) {
                    results[i] = true;
                    break;
                }
            }
        }
        return results[s.length()];
    }
}

```

## 29. palindrome_partitioning
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

## 30. pascals_triangle_ii
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

## 31. reorder_list
```java
package reorder_list;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}


/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln →L1→Ln-1→L2→Ln-2→…
 * You must do this in-place without altering the nodes' values.
 * For example,
 * Given{1,2,3,4}, reorder it to{1,4,2,3}.
 *
 * 快慢指针找到中间节点，将后面的链表反转（前插法），合并链表
 * 1,2
 * 3,4
 */
public class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null)
            return;

        // 1、快慢指针找到链表的中点
        ListNode middle = findMiddle(head);

        // 2、反转 middle.next开始的链表
        ListNode start = middle.next;
        middle.next = null;
        ListNode newhead = reverseLinkedList(start);

        // 3. 交替合并两个链表
        merge(head, newhead);
    }

    /**
     * 交叉合并链表
     */
    private void merge(ListNode oddHead, ListNode evenHead) {
        while (oddHead != null && evenHead != null) {
            ListNode oddTmp = oddHead.next;
            oddHead.next = evenHead;
            ListNode evenTmp = evenHead.next;
            evenHead.next = oddTmp;
            oddHead = oddTmp;
            evenHead = evenTmp;
        }
    }

    /**
     * 快慢指针获取链表中点
     */
    private ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow即指向中间节点
        return slow;
    }

    /**
     * 递归的方式反转链表, 返回反转后的头结点，原头结点指向了null
     */
    private ListNode reverseLinkedList(ListNode head) {
        if (head.next == null)
            return head;

        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

```

## 32. path_sum
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

## 33. populating_next_right_pointers_in_each_node
```java
package populating_next_right_pointers_in_each_node;


import java.util.ArrayList;

/**
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set toNULL.
 * Initially, all next pointers are set toNULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the
 * same level, and every parent has two children).
 *
 * For example,
 * Given the following perfect binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 *
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 */
public class Solution {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    /**
     * 树的层次遍历，由于使用了空间复杂度 O(n)
     */
    public void connect1(TreeLinkNode root) {
        if (root == null)
            return;

        ArrayList<TreeLinkNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            ArrayList<TreeLinkNode> nextLevel = new ArrayList<>();
            // 遍历当前层
            TreeLinkNode node = curLevel.get(0);
            TreeLinkNode nextNode;

            // 保存下一层节点
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);

            for (int i = 1; i < curLevel.size(); i++) {
                // 修改 next 指针
                nextNode = curLevel.get(i);
                node.next = nextNode;
                node = nextNode;

                // 保存下一层节点
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            curLevel = nextLevel;
        }
    }

    /**
     * 递归的方式实现，空间复杂度为调用栈的深度
     * 边界条件几种方式
     * 1、root 为 null 直接返回
     * 2、root 根节点下的左右子树的 next 链接
     * 3、跨 root 根节点的子树的 next 链接
     */
    public void connect2(TreeLinkNode root) {
        if (root == null)
            return;

        // root 根节点下的左右子树的 next 链接
        if (root.left != null && root.right != null)
            root.left.next = root.right;
        // 跨 root 根节点的子树的 next 链接
        if (root.right != null && root.next != null)
            root.right.next = root.next.left;

        // 递归connect以root左右子树节点为根节点的子树
        connect2(root.left);
        connect2(root.right);
    }

    /**
     * 非递归的方式实现
     *
     * 从最左边的节点开始更新 next 指针，同样两种情况：
     * root.left.next -> root.right
     * root.right.next -> root.next.left
     */
    public void connect(TreeLinkNode root) {
        if (root ==null)
            return;

        TreeLinkNode p = root;
        TreeLinkNode updateRoot;

        // 从根节点 p 开始，从最左边的节点开始修改p的下一层的左右子树节点的 next 指针
        while (p.left != null) {
            updateRoot = p;
            while (updateRoot != null) {
                // 第一种情况
                updateRoot.left.next = updateRoot.right;
                // 第二种情况
                if (updateRoot.next != null) {
                    updateRoot.right.next = updateRoot.next.left;
                }
                // 更新的节点向右移动
                updateRoot = updateRoot.next;
            }
            p = p.left;
        }
    }
}

```

## 34. valid_palindrome
```java
package valid_palindrome;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama"is a palindrome.
 * "race a car"is not a palindrome.
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * For the purpose of this problem, we define empty string as valid palindrome.
 */
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() == 0) return true;

        // 两边开始扫描，遇到非字母或数字的（alphanumeric）的跳过
        int left = 0;
        int right = s.length() - 1;

        for (; left < right; left++, right--) {
            while (left < right && !isAlphaNumeric(s.charAt(left)))
                left++;
            while (left < right && !isAlphaNumeric(s.charAt(right)))
                right--;

            // 判断此时left 和 right 的字符是否相等
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
        }

        return true;
    }

    private boolean isAlphaNumeric(char c) {
        return ((c>='0' && c<='9') || (c>='a' && c<='z') || (c>='A' && c<='Z'));
    }
}
```

## 35. path_sum_ii
```java
package path_sum_ii;

import java.util.ArrayList;

public class Solution {

    public class TreeNode {
        int val;
        Solution.TreeNode left;
        Solution.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (root == null)
            return result;

        dfs(root, sum, new ArrayList<>(), result);

        return result;
    }

    private void dfs2(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {

        if (root == null)
            return;

        curResult.add(root.val);
        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val) {
            // 注意需要 new 一个新的变量
            result.add(new ArrayList<>(curResult));
            // 注意此处需要删除最后一个，以探索更多的可能组合
            curResult.remove(curResult.size() - 1);
            return;
        }

        // 递归左子树和右子树
        dfs2(root.left, sum - root.val, curResult, result);
        dfs2(root.right, sum - root.val, curResult, result);
        // 注意此处需要删除最后一个，以探索更多的可能组合
        curResult.remove(curResult.size() - 1);
    }

    /**
     * 递归调用时，直接传入新创建的 curResult，避免add remove 操作
     */
    private void dfs(TreeNode root,
                     int sum,
                     ArrayList<Integer> curResult,
                     ArrayList<ArrayList<Integer>> result) {
        if (root == null)
            return;

        curResult.add(root.val);
        // 如果 root 是叶子节点，并且此时的 sum 和 root 相等
        if (root.left == null && root.right == null && sum == root.val) {
            // 注意需要 new 一个新的变量
            result.add(new ArrayList<>(curResult));
            return;
        }

        dfs(root.left, sum - root.val, new ArrayList<>(curResult), result);
        dfs(root.right, sum - root.val, new ArrayList<>(curResult), result);
    }
}
```

## 36. pascals_triangle
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

## 37. sum_root_to_leaf_numbers
```java
package sum_root_to_leaf_numbers;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * <p>
 * 1
 * / \
 * 2   3
 * \
 * 3
 * <p>
 * The root-to-leaf path 1->2->3 represents the number 123.
 * The root-to-leaf path 1->3 represents the number 13.
 * <p>
 * Return the sum = 123 + 13 = 136.
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

    private int sum = 0;

    /**
     * 先序遍历的思想(根左右) + 数字求和(每一层都比上层和*10+当前根节点的值)
     */
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        preorderSum(root, root.val);
        return sum;
    }

    /**
     * 先序遍历求和
     */
    private void preorderSum(TreeNode root, int pathNum) {
        if (root == null)
            return;

        // 到达叶子节点，计算此时的 sum
        if (root.left == null && root.right == null) {
            sum += pathNum;
        }

        if (root.left != null) {
            preorderSum(root.left, 10 * pathNum + root.left.val);
        }

        if (root.right != null) {
            preorderSum(root.right, 10 * pathNum + root.right.val);
        }
    }
}

```

## 38. word_break_ii
```java
package word_break_ii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * <p>
 * Return all such possible sentences.
 * <p>
 * For example, given
 * s ="catsanddog",
 * dict =["cat", "cats", "and", "sand", "dog"].
 * <p>
 * A solution is["cats and dog", "cat sand dog"].
 */
public class Solution {

    private HashMap<String, ArrayList<String>> cache = new HashMap<>();

    /**
     * Assuming the length of s is n+1, for each position in [1, ..., n], split s,
     * left part as word, right part as remian, if word exists in the wordDict,
     * process remain in the same way recursively and concat the results.
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfsHelper(s, wordDict);
    }

    private ArrayList<String> dfsHelper(String s, List<String> wordDict) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }

        ArrayList<String> result = new ArrayList<>();

        if (s.equals("")) {
            result.add("");
            return result;
        }

        for (int i = 1; i <= s.length(); i++) {
            String word = s.substring(0, i);
            String remain = s.substring(i);

            if (wordDict.contains(word)) {
                // remain 剩下的字符串用 dict 内容构成的列表（即 remain 字符串所对应的结果）
                ArrayList<String> remainResult = dfsHelper(remain, wordDict);

                // remain 的结果拼接第一个 word
                for (String remainRes : remainResult) {
                    // 必须要顺序一致，醉了
                    result.add(0, (word + " " + remainRes).trim());
                }
            }
        }
        // 缓存中间结果
        cache.put(s, result);
        return result;
    }
}

```

## 39. populating_next_right_pointers_in_each_node_ii
```java
package populating_next_right_pointers_in_each_node_ii;

import java.util.*;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 *
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 */
public class Solution {

    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    /**
     * 层次遍历的方法
     */
    public void connect1(TreeLinkNode root) {
        if (root == null)
            return;

        ArrayList<TreeLinkNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            ArrayList<TreeLinkNode> nextLevel = new ArrayList<>();
            // 遍历当前层，从最左边开始
            TreeLinkNode node = curLevel.get(0);
            TreeLinkNode nextNode;

            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);

            for (int i = 1; i < curLevel.size(); i++) {
                // 修改 next 指针
                nextNode = curLevel.get(i);
                node.next = nextNode;
                node = nextNode;

                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            curLevel = nextLevel;
        }
    }

    /**
     * 递归的方式实现
     */
    public void connect(TreeLinkNode root) {
        if (root == null) return;

        TreeLinkNode cur = root;

        while (cur != null) {
            if (cur.left != null)
                cur.left.next = cur.right != null ? cur.right : getNext(cur);

            if (cur.right != null)
                cur.right.next = getNext(cur);

            cur = cur.next;
        }

        connect(root.left);
        connect(root.right);
    }

    /**
     * 根据当前 root 获取 root 下一层合适的节点
     */
    private TreeLinkNode getNext(TreeLinkNode root) {

        TreeLinkNode nextRoot = root.next;
        while (nextRoot != null) {
            if (nextRoot.left != null) return nextRoot.left;
            if (nextRoot.right != null) return nextRoot.right;
            nextRoot = nextRoot.next;
        }
        return null;
    }

}
```

## 40. sort_list
```java
package sort_list;


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Sort a linked list in O(nlogn) time using constant space complexity.
 */
public class Solution {

    /**
     * 时间复杂度为 O(nlogn)，采用归并排序
     *
     * 归并排序的一般步骤为：
     * 1）将待排序数组（链表）取中点并一分为二；
     * 2）递归地对左半部分进行归并排序；
     * 3）递归地对右半部分进行归并排序；
     * 4）将两个半部分进行合并（merge）,得到结果。
     *
     * 所以对应此题目，可以划分为三个小问题:
     * 1、快慢指针找到链表的中点
     * 2、拆分链表
     * 3、合并有序链表
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        // 快慢指针找链表的中点
        ListNode middle = findMiddle(head);
        // 右边排序
        ListNode right = sortList(middle.next);
        middle.next = null;
        // 左边排序
        ListNode left = sortList(head);
        // 合并
        return mergeTwoLists(left, right);
    }

    private ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

## 41. maximum_depth_of_binary_tree
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

## 42. symmetric_tree
```java
package symmetric_tree;

/**
 * 判断二叉树是否自对称
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

    public boolean isSymmetric(TreeNode root) {
        return helper(root, root);
    }
 
    // 递归： leftNode 的右子树和 rightNode 的左子树是否对称，leftNode 的左子树和 rightNode 的右子树是否对称
    private boolean helper(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null)
            return true;
 
        if ((leftNode == null && rightNode != null) || (leftNode != null && rightNode == null))
            return false;
 
        // leftNode 和 rightNode 都不为空，比较 val 是否相等
        // 同时看 leftNode 的右子树和 rightNode 的左子树是否对称，leftNode 的左子树和 rightNode 的右子树是否对称
        return (leftNode.val == rightNode.val) &&
                helper(leftNode.right, rightNode.left) &&
                helper(leftNode.left, rightNode.right);
    }
}
```

## 43. same_tree
```java
package same_tree;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
         
        if (p == null || q == null)
            return false;
         
        // 都不为空，则比较 val 是否相等，并进行递归比较
        return (p.val == q.val) && (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}
```

## 44. validate_binary_search_tree
```java
package validate_binary_search_tree;

/**
 * 判断二叉树是否是二叉搜索树
 * BST 满足：
 * 1.若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2.若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3.任意节点的左、右子树也分别为二叉查找树；
 * 4.没有键值相等的节点。
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

    public boolean isValidBST(TreeNode root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
     
    /**
     * 递归的方法，注意引入当前根节点的左右子树所满足的上下界
     */
    private boolean helper(TreeNode root, int min, int max) {
        if (root == null)
            return true;
         
        // 每个节点都有一个对应的上下界
        if (root.val <= min || root.val >= max)
            return false;
         
        // 递归判断root的左右子树是否满足 BST
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
```

## 45. recover_binary_search_tree
```java
package recover_binary_search_tree;

public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private TreeNode misLeft, misRight;
    // 中序遍历的前一个节点，用于和当前节点进行比较，判断是否有序
    private TreeNode preNode = new TreeNode(Integer.MIN_VALUE);

    /**
     * 利用：二叉搜索树的中序遍历为有序数组 的性质
     * 中序遍历，记录不满足顺序排列的节点
     */
    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);

        int tmp = misLeft.val;
        misLeft.val = misRight.val;
        misRight.val = tmp;
    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null)
            return;

        inOrderTraverse(root.left);
        // misstake left 未记录
        if (misLeft == null && preNode.val >= root.val)
            misLeft = preNode;

        if (misLeft != null && preNode.val >= root.val)
            misRight = root;

        preNode = root;
        inOrderTraverse(root.right);
    }

}
```

## 46. unique_binary_search_trees
```java
package unique_binary_search_trees;

/**
 * Given n, how many structurally unique BST's
 * (binary search trees) that store values 1...n?
 *
 * 思路：
 *     考虑根节点，设对于任意根节点k，有f(k)种树的可能。
 *     比k小的k-1个元素构成k的左子树。则左子树有f(k-1)种情况。
 *     比k大的n-k个元素构成k的右子树。则右子树有f(n-k)种情况。
 *     易知，左右子树相互独立，所以f(k)=f(k-1)*f(n-k)。
 *     综上，对于n，结果为k取1,2,3,...,n时，所有f(k)的和。
 */
public class Solution {

    /**
     * 递归的方式
     */
    public int numTrees1(int n) {
        if (n <= 1)
            return 1;

        int result = 0;
        for (int i = 1; i <= n; i++) {
            int leftResult = numTrees1(i - 1);
            int rightResult = numTrees1(n - i);
            // 注意求和的方式
            result += leftResult * rightResult;
        }
        return result;
    }

    /**
     * 动态规划
     *
     * n = 0, f(0) = 1
     * n = 1, f(1) = 1
     * 针对k有 f(k)=f(k-1)*f(n-k)，所以对应的 f(n) 就是 f(k) 的求和
     */
    public int numTrees(int n) {
        if (n < 0)
            return 0;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int k = 1; k <= i; k++) {
                int dp_k = dp[k - 1] * dp[i - k];
                dp[i] += dp_k;
            }
        }
        return dp[n];
    }
}

```

## 47. unique_binary_search_trees_ii
```java
package unique_binary_search_trees_ii;

import java.util.ArrayList;

/**
 * Given n, generate all structurally unique BST's
 * that store values 1...n.
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
     * 递归的方式
     */
    public ArrayList<TreeNode> generateTrees(int n) {
        return createTree(1, n);
    }

    /**
     * 每次一次选取一个结点为根，然后递归求解左右子树的所有结果，
     * 最后根据左右子树的返回的所有子树，然后针对左右子树的结果进行全排列，
     * 总共有左右子树数量的乘积种情况。
     *
     * 因为需要递归求得左右子树的数目，因此需要定义 left 和 right 划定范围
     */
    private ArrayList<TreeNode> createTree(int left, int right) {
        ArrayList<TreeNode> result = new ArrayList<>();

        if (left > right) {
            // tricky！此处的 null可以用于在全排列时作为叶子节点的 null子节点
            result.add(null);
            return result;
        }

        // 以i为根节点的树，左子树的范围 [left,i-1], 右子树的范围 [i+1, right]
        for (int i = left; i <= right; i++) {
            ArrayList<TreeNode> leftResult = createTree(left, i - 1);
            ArrayList<TreeNode> rightResult = createTree(i+1, right);
            // 对左右子树的结果进行排列拼接
            int leftSize = leftResult.size();
            int rightSize = rightResult.size();
            for (int l = 0; l < leftSize; l++) {
                for (int r = 0; r < rightSize; r++) {
                    // 此时的 i 为根节点
                    TreeNode root = new TreeNode(i);
                    root.left = leftResult.get(l);
                    root.right = rightResult.get(r);
                    // 添加此时拼接好的结果
                    result.add(root);
                }
            }
        }

        return result;
    }
}

```

## 48. restore_ip_addresses
```java
package restore_ip_addresses;

import java.util.ArrayList;

/**
 * Given a string containing only digits, restore it by
 * returning all possible valid IP address combinations.
 *
 * For example:
 * Given"25525511135",
 * return["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class Solution {

    /**
     * 由于每段数字最多只能有三位，而且只能分为四段，所以情况并不是很多，使用暴力搜索的方法
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();

        for (int a = 1; a < 4; a++)
        for (int b = 1; b < 4; b++)
        for (int c = 1; c < 4; c++)
        for (int d = 1; d < 4; d++) {
            // 此时的划分，总长度等于 s 的长度
            if (a + b + c + d == s.length()) {
                int A = Integer.parseInt(s.substring(0, a));
                int B = Integer.parseInt(s.substring(a, a+b));
                int C = Integer.parseInt(s.substring(a+b, a+b+c));
                int D = Integer.parseInt(s.substring(a+b+c, a+b+c+d));

                // 每段要满足 [0, 255]
                if ( A<=255 && B<=255 && C<=255 && D<=255) {
                    // 拼接此时的 ip
                    String ip = A + "." + B + "." + C + "." + D;
                    // 需要判断此时的 ip 长度是否合法，排除 100.001.123.456 的不合法 ip
                    if (ip.length() == s.length() + 3)
                        result.add(ip);
                }
            }
        }
        return result;

    }
}
```

## 49. reverse_linked_list_ii
```java
package reverse_linked_list_ii;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 *
 * For example:
 * Given1->2->3->4->5->NULL, m = 2 and n = 4,
 * return1->4->3->2->5->NULL.
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
     * 初始位置   ：自上而下为 A B C D
     * 第一次操作后：自上而下为 B A C D
     * 第二次操作后：自上而下为 C B A D
     * 第三次操作后：自上而下为 D C B A
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preStart = dummy; // 记录反转的开始节点的前一个节点，防止链表断开
        ListNode start = head; // 反转的开始节点
        // 找到第 m 个节点，注意从 1 开始
        for (int i = 1; i < m; i++) {
            preStart = start;
            start = start.next;
        }

        // 反转链表，同时记录不超过 n, 画图解释
        for (int j = 0; j < n - m; j++) {
            ListNode temp = start.next;
            start.next = temp.next;
            temp.next = preStart.next;
            preStart.next = temp;
        }
        return dummy.next;
    }
}

```

