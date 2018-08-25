package search_in_rotated_sorted_array;

/**
 * 旋转数组的查找、类似旋转数组的最小值
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index,
 * otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */
public class Solution {

    /**
     * 采用二分查找，关键是决定查找的左右分支
     *
     * 先判断哪个分支有序，再判断 target 属于哪个分支
     */
    public int search(int[] A, int target) {
        if (A == null || A.length == 0)
            return -1;

        int left = 0;
        int right = A.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (A[mid] == target)
                return mid;

            // 中间值大于最左边的值，则左分支递增有序，注意此处的 <=
            if (A[left] <= A[mid]) {
                // 注意此处 A[left] <= target
                if (A[left] <= target && target < A[mid]) {  // target 在左分支
                    right = mid - 1;
                } else { // target 在右分支
                    left = mid + 1;
                }
            } else { // 右分支递增有序
                // 注意此处 target <= A[right]
                if (A[mid] < target && target <= A[right]) {  // target 在右分支
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
