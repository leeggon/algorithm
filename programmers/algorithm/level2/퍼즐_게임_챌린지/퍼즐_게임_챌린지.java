package programmers.algorithm.level2.퍼즐_게임_챌린지;

import java.util.*;

class 퍼즐_게임_챌린지 {
    public int solution(int[] diffs, int[] times, long limit) {

        int left = 1;
        int right = Arrays.stream(diffs).max().getAsInt();
        int level = -1;
        while (left <= right) {
            level = (left + right) / 2;
            long timeTaken = calcTime(diffs, times, level);

            if (timeTaken <= limit) { // 소요시간 안에 들어오는 경우(이미 충분), 레벨 탐색 범위 더 낮춰야함.
                right = level - 1;
            } else {
                left = level + 1;
            }
        }

        return left;
    }

    private long calcTime(int[] diffs, int[] times, int level) {
        long timeTaken = 0;
        for (int i=0; i<diffs.length; i++) {
            int diff = diffs[i];
            int timeCur = times[i];
            int timePrev = i > 0 ? times[i-1] : 0;

            if (level >= diff) {
                timeTaken += timeCur;
            } else {
                timeTaken += (long) (diff - level) * (timeCur + timePrev) + timeCur;
            }
        }

        return timeTaken;
    }
}
