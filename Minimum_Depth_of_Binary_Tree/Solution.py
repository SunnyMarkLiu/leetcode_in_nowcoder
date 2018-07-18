#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/18 下午10:14
"""


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution(object):
    def minDepth(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        if not root:
            return 0

        # 如果 root 的左右子树都存在，则递归获取左右子树的 min + 1
        if root.left and root.right:
            return min(self.minDepth(root.left), self.minDepth(root.right)) + 1

        # 如果当前结点只存在一个左右结点，选择有叶子结点的分支，即 depth 较大的，同时注意加上当前的结点 + 1
        return max(self.minDepth(root.left), self.minDepth(root.right)) + 1
