package programmers.algorithm.level2.요격_시스템;

import java.util.*;

class 요격_시스템 {
    public int solution(int[][] targets) {

        int answer = 0;

        int maxVal = Integer.MIN_VALUE;

        Arrays.sort(targets, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        for (int[] target : targets) {
            int s = target[0];
            int e = target[1];

            if (s >= maxVal) {
                maxVal = e;
                answer++;
            }

        }

        return answer;
    }
}
