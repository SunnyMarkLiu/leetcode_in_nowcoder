package google;

import java.util.*;
import java.io.*;

public class Yogurt {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        // T test
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int k = in.nextInt();

            int[] yogurts = new int[n];
            for (int j = 0; j < n; j++) {
                yogurts[j] = in.nextInt();
            }
            Arrays.sort(yogurts);
            // maxConsume for n,k
            int max = maxConsume(n, k, yogurts);
            System.out.println("Case #" + i + ": " + max);
        }
    }

    private static int maxConsume(int n, int k, int[] yogurts) {
        int max = 0;
        int eatDay = 0;
        for (int yogurt : yogurts) {
            if (yogurt > eatDay) {
                max++;
                eatDay++;
            }
        }
        return max;
    }

}