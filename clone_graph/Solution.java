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
