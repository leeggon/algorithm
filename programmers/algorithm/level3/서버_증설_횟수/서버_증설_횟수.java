package programmers.algorithm.level3.서버_증설_횟수;

import java.util.*;

class 서버_증설_횟수 {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        int[] maxP = new int[24];
        Arrays.fill(maxP, m - 1);
        for (int time=0; time<24; time++) {

            if (maxP[time] < players[time]) { // 최대 수용 인원이 더 적다면
                int temp = maxP[time];
                int cnt = 0; // 증설 횟수
                while (temp < players[time]) {
                    temp += m;
                    cnt++;
                }

                answer += cnt;
                for (int i=0; i<k; i++) {
                    if (time + i >= 24) continue;
                    maxP[time + i] += cnt * m;
                }
            }
        }


        return answer;
    }
}
