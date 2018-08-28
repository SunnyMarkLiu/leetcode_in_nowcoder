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
