package programmers.algorithm.level3.광고_삽입;

import java.util.*;

class 광고_삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        long answer = 0;

        String[] play = play_time.split(":");
        int totalSec = 60 * 60 * Integer.parseInt(play[0]) + 60 * Integer.parseInt(play[1]) + Integer.parseInt(play[2]);
        int[] acc = new int[totalSec + 1];

        String[] adv = adv_time.split(":");
        int advSec = 60 * 60 * Integer.parseInt(adv[0]) + 60 * Integer.parseInt(adv[1]) + Integer.parseInt(adv[2]);

        for (String log : logs) {
            String[] lg = log.split("-");
            String[] start = lg[0].split(":");
            String[] end = lg[1].split(":");

            int s = 60 * 60 * Integer.parseInt(start[0]) + 60 * Integer.parseInt(start[1]) + Integer.parseInt(start[2]);
            int e = 60 * 60 * Integer.parseInt(end[0]) + 60 * Integer.parseInt(end[1]) + Integer.parseInt(end[2]);

            acc[s]++;
            acc[e]--;
        }

        for (int i=1; i<=totalSec; i++) {
            acc[i] += acc[i-1];
        }

        long[] accSum = new long[totalSec]; // advSec 동영상 길이만큼의 누적합 배열
        long initSum = 0;
        for (int i=0; i<advSec; i++) {
            initSum += acc[i];
        }
        accSum[advSec-1] = initSum;

        for (int i=advSec; i<totalSec; i++) {
            long temp = accSum[i - 1];
            temp -= acc[i - advSec];
            temp += acc[i];
            accSum[i] = temp;
        }

        long maxOverlap = Arrays.stream(accSum).max().getAsLong();

        for (int i=0; i<totalSec; i++) {
            if (accSum[i] == maxOverlap) {
                answer = i - advSec + 1;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        int h = (int) answer/3600;
        int m = (int) (answer % 3600 / 60);
        int s = (int) (answer % 3600 % 60);

        sb.append(String.format("%02d", h));
        sb.append(":");
        sb.append(String.format("%02d", m));
        sb.append(":");
        sb.append(String.format("%02d", s));

        return sb.toString();
    }
}
