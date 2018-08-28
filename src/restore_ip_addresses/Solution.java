package restore_ip_addresses;

import java.util.ArrayList;

/**
 * Given a string containing only digits, restore it by
 * returning all possible valid IP address combinations.
 *
 * For example:
 * Given"25525511135",
 * return["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */
public class Solution {

    /**
     * 由于每段数字最多只能有三位，而且只能分为四段，所以情况并不是很多，使用暴力搜索的方法
     */
    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<>();

        for (int a = 1; a < 4; a++)
        for (int b = 1; b < 4; b++)
        for (int c = 1; c < 4; c++)
        for (int d = 1; d < 4; d++) {
            // 此时的划分，总长度等于 s 的长度
            if (a + b + c + d == s.length()) {
                int A = Integer.parseInt(s.substring(0, a));
                int B = Integer.parseInt(s.substring(a, a+b));
                int C = Integer.parseInt(s.substring(a+b, a+b+c));
                int D = Integer.parseInt(s.substring(a+b+c, a+b+c+d));

                // 每段要满足 [0, 255]
                if ( A<=255 && B<=255 && C<=255 && D<=255) {
                    // 拼接此时的 ip
                    String ip = A + "." + B + "." + C + "." + D;
                    // 需要判断此时的 ip 长度是否合法，排除 100.001.123.456 的不合法 ip
                    if (ip.length() == s.length() + 3)
                        result.add(ip);
                }
            }
        }
        return result;

    }
}