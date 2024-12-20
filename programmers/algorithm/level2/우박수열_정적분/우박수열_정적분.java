package programmers.algorithm.level2.우박수열_정적분;

import java.util.*;

class 우박수열_정적분 {
    public double[] solution(int k, int[][] ranges) {
        int n = 0;
        List<Integer> points = new ArrayList<>();
        List<Double> areaSum = new ArrayList<>();
        points.add(k);
        areaSum.add(0.0);
        while (k > 1) {
            if (k%2 == 0) {
                int nextK = k / 2;
                areaSum.add(areaSum.get(areaSum.size() - 1) + (k + nextK) / 2.0);
                k = nextK;
            } else {
                int nextK = k * 3 + 1;
                areaSum.add(areaSum.get(areaSum.size() - 1) + (k + nextK) / 2.0);
                k = nextK;
            }

            points.add(k);
            n++;
        }

        double[] result = new double[ranges.length];

        for (int i=0; i<ranges.length; i++) {
            int a = ranges[i][0];
            int b = ranges[i][1];
            b = n + b;

            if (b < a) {
                result[i] = -1.0;
                continue;
            } else if (b == a) {
                result[i] = 0.0;
                continue;
            } else {
                result[i] = areaSum.get(b) - areaSum.get(a);
            }

        }

        return result;
    }
}