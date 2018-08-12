#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/19 下午2:55
"""


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def preorderTraversal1(self, root):
        """
        :type root: TreeNode
        :rtype: List[int]
        """
        result = []
        self.recursion_helper(result, root)
        return result

    def recursion_helper(self, result, root):
        if not root:
            return

        # 访问根节点
        result.append(root.val)
        # 访问左子树
        if root.left:
            self.recursion_helper(result, root.left)
        # 访问右子树
        if root.right:
            self.recursion_helper(result, root.right)

    def preorderTraversal(self, root):
        result = []
        if not root:
            return result

        stack = [root]

        while len(stack) > 0:
            curr_node = stack.pop()

            # 访问根节点
            result.append(curr_node.val)

            if curr_node.right:
                stack.append(curr_node.right)

            if curr_node.left:
                stack.append(curr_node.left)

        return result
