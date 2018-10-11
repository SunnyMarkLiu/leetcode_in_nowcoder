## 1. Binary_Tree_Inorder_Traversal
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
        recursionHelper(result, root.left);
        // 访问根节点
        result.add(root.val);
        // 访问右子树
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

## 2. Binary_Tree_Level_Order_Traversal
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

## 3. Binary_Tree_Level_Order_Traversal_II
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

## 4. Binary_Tree_Postorder_Traversal
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

        recursionHelper(result, root.left);
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

## 5. Binary_Tree_Preorder_Traversal
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
        recursionHelper(result, root.left);
        // 访问右子树
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

## 6. Binary_Tree_Zigzag_Level_Order_Traversal
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

## 7. Construct_Binary_Tree_from_Inorder_and_Postorder_Traversal
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

## 8. Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal
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
        if (inorder == null || preorder == null || inorder.length == 0 || preorder.length == 0)
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

## 9. Linked_List_Cycle
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
            
            ListNode fast = head;
            ListNode slow = head;
            while (slow != null && fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow)
                    return true;
            }
            return false;
        }
}
```

## 10. Linked_List_Cycle_II
```java
package Linked_List_Cycle_II;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
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
        ListNode fast = head;

        while (slow != null && fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return fast;
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
