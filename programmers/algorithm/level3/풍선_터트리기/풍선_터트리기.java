package programmers.algorithm.level3.풍선_터트리기;

import java.util.*;

class 풍선_터트리기 {
    public int solution(int[] a) {
        int answer = 0;

        int N = a.length;
        int[] leftMin = new int[N];
        int[] rightMin = new int[N];

        int minTemp = a[0];
        for (int i=1; i<N; i++) {
            leftMin[i] = minTemp;
            minTemp = Math.min(minTemp, a[i]);
        }

        minTemp = a[N-1];
        for (int i=N-2; i>=0; i--) {
            rightMin[i] = minTemp;
            minTemp = Math.min(minTemp, a[i]);
        }

        for (int i=1; i<N-1; i++) {
            if (a[i] > leftMin[i] && a[i] > rightMin[i]) {
                answer++;
            }
        }

        return N-answer;
    }
}
