#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/19 下午8:59
"""


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def buildTree(self, preorder, inorder):
        """
        :type preorder: List[int]
        :type inorder: List[int]
        :rtype: TreeNode
        """
        if not preorder or not inorder or (len(preorder) == 0) or (len(inorder) == 0):
            return None
        return self.buildHelper(preorder, 0, len(preorder) - 1,
                                inorder, 0, len(inorder) - 1)

    def buildHelper(self, preorder, pre_left, pre_right,
                          inorder, in_left, in_right):

        if pre_left > pre_right:
            return None

        # preorder 的第一个元素即为根节点
        root = TreeNode(preorder[pre_left])

        if pre_left == pre_right:
            return root

        # 找到根节点在中序遍历中的位置
        root_index = inorder.index(root.val)
        left_tree_size = root_index - in_left

        # 创建左子树和右子树
        root.left = self.buildHelper(preorder, pre_left + 1, pre_left + left_tree_size,
                                     inorder, in_left, root_index - 1)
        root.right = self.buildHelper(preorder, pre_left + left_tree_size + 1, pre_right,
                                      inorder, root_index + 1, in_right)
        return root

