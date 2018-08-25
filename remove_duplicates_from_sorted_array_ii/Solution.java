package remove_duplicates_from_sorted_array_ii;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array A =[1,1,1,2,2,3],
 * Your function should return length =5, and A is now[1,1,2,2,3].
 */
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        int count = 0;
        for (int i = 0; i < A.length; i++) {
            // 满足重复 3 次及以上的,继续查找该重复的值，直到找到该重复值的最后一个值，接下来替换 count 下表的值
            if (i > 0 && i < A.length - 1 && A[i-1] == A[i] && A[i] == A[i+1])
                continue;

            A[count++] = A[i];
        }
        return count;
    }
}