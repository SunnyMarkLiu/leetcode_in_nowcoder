package gas_station;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note:
 * The solution is guaranteed to be unique.
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {

        if (gas == null || cost == null || gas.length == 0 || cost.length == 0)
            return -1;

        int tankLeft = 0;
        int curStepRemain = 0;
        int fromStartRemain = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            // 从 i -> i + 1，可以剩下多少油
            curStepRemain = gas[i] - cost[i];
            fromStartRemain += curStepRemain;
            tankLeft += curStepRemain;

            // 说明从当前 start 走不通，则说明从start到 i 之间的节点开始都走不通
            if (fromStartRemain < 0) {
                fromStartRemain = 0;
                // 从 i 的下一个节点开始
                start = i + 1;
            }
        }

        // 如果最后 tank 有剩余，返回此时的 start
        if (tankLeft >= 0)
            return start;
        return -1;
    }
}