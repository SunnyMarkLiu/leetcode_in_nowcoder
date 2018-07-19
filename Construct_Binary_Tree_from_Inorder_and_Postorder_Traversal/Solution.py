#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/19 下午9:43
"""


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def buildTree(self, inorder, postorder):
        """
        :type inorder: List[int]
        :type postorder: List[int]
        :rtype: TreeNode
        """
        if not inorder or not postorder:
            return None
        return self.build_helper(inorder, 0, len(inorder) - 1, postorder, 0, len(postorder) - 1)

    def build_helper(self, inorder, in_left, in_right, postorder, pos_left, pos_right):

        if in_left > in_right:
            return None

        # 根据后续遍历的最后一个元素获取根节点
        root = TreeNode(postorder[pos_right])

        if in_left == in_right:
            return root

        # 获取根节点在中序遍历的位置
        root_index = inorder.index(root.val)
        # 左子树的节点数
        left_tree_size = root_index - in_left

        # 递归构造左右子树
        root.left = self.build_helper(inorder, in_left, root_index - 1,
                                      postorder, pos_left, pos_left + left_tree_size - 1)
        root.right = self.build_helper(inorder, root_index + 1, in_right,
                                       postorder, pos_left + left_tree_size, pos_right - 1)
        return root
