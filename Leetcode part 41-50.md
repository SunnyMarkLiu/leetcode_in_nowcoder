## 41. sort_list
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

## 42. sum_root_to_leaf_numbers
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

## 43. symmetric_tree
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

## 44. unique_binary_search_trees
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

## 45. unique_binary_search_trees_ii
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

## 46. valid_palindrome
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

## 47. validate_binary_search_tree
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

## 48. word_break
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

## 49. word_break_ii
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

## 50. merge_sorted_array
```java
package merge_sorted_array;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note:
 * You may assume that A has enough space to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 */
public class Solution {
    /**
     * 分别从 A、B 的尾部开始比较，较大的数插入到A的尾部，同时尾部的指针前移，
     * 如果 B 还有剩余则拼接剩余的 B，如果 A 剩余，则直接推出
     */
    public void merge(int A[], int m, int B[], int n) {
        int ai = m - 1, bi = n - 1, tail = m + n - 1;
        while (ai >= 0 && bi >= 0) {
            // 比较 AB 尾部的值的大小
            A[tail--] = A[ai] > B[bi] ? A[ai--] : B[bi--];
        }
        // 如果 B 剩余，拼接剩余的 B
        while (bi >= 0)
            A[tail--] = B[bi--];
    }
}

```
