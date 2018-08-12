package single_number;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it
 * without using extra memory?
 */
public class Solution {

    /**
     * 1.异或满足交换律。
     * 2.相同两个数异或为0。
     * 3.0异或一个数为那个数本身。
     */
    public int singleNumber(int[] A) {
        int result = 0;
        for (int a : A) {
            result ^= a;
        }
        return result;
    }
}
