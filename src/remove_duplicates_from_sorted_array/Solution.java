package remove_duplicates_from_sorted_array;

/**
 * Given a sorted array, remove the duplicates in place such
 * that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do
 * this in place with constant memory.
 * For example,
 * Given input array A =[1,1,2],
 * Your function should return length =2, and A is now[1,2].
 */
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;

        // count 用于记录有效数的个数，初始化第一个数肯定满足，从 1 开始
        int count = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] != A[i-1])
                A[count++] = A[i];
        }
        return count;
    }
}