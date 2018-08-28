package word_search;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 */
public class Solution {

    private boolean[][] visited;

    /**
     * DFS + 回溯法
     * 定义一个 visited 数组，标记数组元素是否访问。然后遍历数组，以每个元素为起点开始 DFS 查找
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0)
            return false;

        // 标记访问过的char
        visited = new boolean[board.length][board[0].length];
        // 遍历char数组,以里面每一个board[i][j]作为dfs的起始点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果数组里能匹配word的第一个字母 &&能匹配剩下的
                if (word.charAt(0) == board[i][j] && dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    /**
     * 以（i，j）为起点 DFS 递归的检测剩下的字符，matchedIndex 记录匹配的长度，
     * 长度和 word 相等则匹配成功。
     */
    private boolean dfs(char[][] board, String word, int i, int j, int matchedIndex) {
        // true 返回条件: matchedIndex 长度和word一样
        if (matchedIndex == word.length())
            return true;

        // false 返回条件: ij 越界 / 元素不相等 / 当前元素已经访问过
        if (i >= board.length || i < 0 || j < 0 || j >= board[0].length ||
                board[i][j] != word.charAt(matchedIndex) || visited[i][j])
            return false;

        // 标记当前 （i，j）访问过
        visited[i][j] = true;
        // 递归判断左右上下分支
        if (dfs(board, word, i - 1, j, matchedIndex + 1) ||
            dfs(board, word, i + 1, j, matchedIndex + 1) ||
            dfs(board, word, i, j - 1, matchedIndex + 1) ||
            dfs(board, word, i, j + 1, matchedIndex + 1))
            return true;

        // 回溯，清除 （i，j）访问过的标记
        visited[i][j] = false;
        return false;
    }
}