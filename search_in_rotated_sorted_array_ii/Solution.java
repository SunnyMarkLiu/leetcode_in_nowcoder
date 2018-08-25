package search_in_rotated_sorted_array_ii;

/**
 * 旋转数组的查找，数组存在重复值
 */
public class Solution {
    /**
     * 同样采用二分搜索：
     * 1、确定哪个分支是有序的，再利用有序分支判断 target 属于哪个分支
     * 2、注意如果 left 和 mid 值重复，说明左分支都是重复的,left++
     */
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0)
            return false;

        int left = 0;
        int right = A.length - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (A[mid] == target)
                return true;

            // 中间值和左右值都相等的情况，（比如：[1,3,1,1,1]查找 3）3 在左右分支均合理，此时只能遍历数组
            if (A[left] == A[mid] && A[mid] == A[right]) {
                // 此处可以优化，出现左中右相等的情况时，left++，right--，肯定会找到不满足左中右相等的情况
                left++;
                right--;
                // for (int a : A) {
                //     if (a == target) return true;
                // }
                // return false;
            }
            // 以下就是 search_in_rotated_sorted_array 的解法，注意此处的 A[left] <= A[mid]
            else if (A[left] <= A[mid]) {  // 左分支有序
                if (A[left] <= target && target < A[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {  // 右分支有序
                if (A[mid] < target && target <= A[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return false;
    }
}