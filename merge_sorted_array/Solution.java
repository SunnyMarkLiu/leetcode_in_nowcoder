package merge_sorted_array;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note:
 * You may assume that A has enough space to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 */
public class Solution {
    /**
     * 分别从 A、B 的尾部开始比较，较大的数插入到A的尾部，同时尾部的指针前移，
     * 如果 B 还有剩余则拼接剩余的 B，如果 A 剩余，则直接推出
     */
    public void merge(int A[], int m, int B[], int n) {
        int ai = m - 1, bi = n - 1, tail = m + n - 1;
        while (ai >= 0 && bi >= 0) {
            // 比较 AB 尾部的值的大小
            A[tail--] = A[ai] > B[bi] ? A[ai--] : B[bi--];
        }
        // 如果 B 剩余，拼接剩余的 B
        while (bi >= 0)
            A[tail--] = B[bi--];
    }
}
