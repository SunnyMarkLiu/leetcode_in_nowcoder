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
    def minDepthRecursion(self, root):
        """
        递归的方式实现, 比较左右子树的深度
        """
        if not root:
            return 0

        # 如果 root 的左右子树都存在，则递归获取左右子树的 min + 1
        if root.left and root.right:
            return min(self.minDepthRecursion(root.left), self.minDepthRecursion(root.right)) + 1

        # 如果当前结点只存在一个左右结点，选择有叶子结点的分支，即 depth 较大的，同时注意加上当前的结点 + 1
        return max(self.minDepthRecursion(root.left), self.minDepthRecursion(root.right)) + 1

    def minDepth(self, root):
        """
        层次遍历，找到第一个左右节点都是 null 的叶子节点，直接返回此时的 depth
        """
        if not root:
            return 0

        if not root.left and not root.right:
            return 1

        depth = 0
        levelQueue = [root]

        while len(levelQueue) > 0:
            cur_level_size = len(levelQueue)
            depth += 1

            for i in range(cur_level_size):
                node = levelQueue.pop(0)
                if not node.left and not node.right:
                    return depth

                if node.left:
                    levelQueue.append(node.left)

                if node.right:
                    levelQueue.append(node.right)

        return 0
