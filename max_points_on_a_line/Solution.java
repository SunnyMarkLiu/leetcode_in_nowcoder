package max_points_on_a_line;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that
 * lie on the same straight line.
 * <p>
 * 给定点两两之间都可以算一个斜率，每个斜率代表一条直线，对每一条直线，
 * 带入所有的点看是否共线并计算个数，这是整体的思路。但是还有两点特殊情况需要考虑，
 * 一是当两个点重合时，无法确定一条直线，但这也是共线的情况，需要特殊处理。
 * 二是斜率不存在的情况，由于两个点(x1, y1)和(x2, y2)的斜率k表示为(y2 - y1) / (x2 - x1)，
 * 那么当x1 = x2时斜率不存在，这种共线情况需要特殊处理。我们需要用到哈希表来记录斜率和共线点个数之间的映射，
 * 其中第一种重合点的情况我们假定其斜率为INT_MIN，第二种情况我们假定其斜率为INT_MAX，这样都可以用map映射了。
 * 我们还需要顶一个变量duplicate来记录重合点的个数，最后只需和哈希表中的数字相加即为共线点的总数
 */
public class Solution {
    class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    /**
     * 穷举法
     */
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;

        int result = 0;
        // 第一重循环遍历起始点a
        for (int i = 0; i < points.length; i++) {
            int duplicate = 1;  // 重复点

            // key：斜率，value：点数
            Map<Double, Integer> map = new HashMap<>();
            // 第二重循环遍历剩余点b
            for (int j = i + 1; j < points.length; j++) {
                // 重复点
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    duplicate++;
                } else if (points[i].x == points[j].x) {    // 垂直点
                    map.put(Double.MAX_VALUE, map.getOrDefault(Double.MAX_VALUE, 0) + 1);
                } else if (points[i].y == points[j].y) {
                    map.put(0.0, map.getOrDefault(0.0, 0) + 1);
                } else {
                    // 注意此处的分子先转为 double
                    double slope = (double) (points[i].y - points[j].y) / (points[i].x - points[j].x);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }

            // 计算当前点作为起始点，共线点数
            result = Math.max(result, duplicate);
            for (Map.Entry<Double, Integer> e : map.entrySet()) {
                // 此处很巧妙，如果此时的斜率数最多，如果再次出现依然是最多的
                result = Math.max(result, e.getValue() + duplicate);
            }
        }
        return result;
    }
}
