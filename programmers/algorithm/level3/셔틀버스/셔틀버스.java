package programmers.algorithm.level3.셔틀버스;

import java.util.*;

class 셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        int answer = -1;

        Set<Integer> busArrivalSet = new HashSet<>();
        for (int i=0; i<n; i++) {
            int time = 540;
            busArrivalSet.add(time + i * t);
        }

        int[] peopleByTime = new int[1440];
        for (String s : timetable) {
            String[] infos = s.split(":");
            int hour = Integer.parseInt(infos[0]);
            int min = Integer.parseInt(infos[1]);

            peopleByTime[hour * 60 + min] ++;
        }

        int curP = 0;
        int totalP = m * n;
        for (int i=1; i<1440; i++) {

            curP += peopleByTime[i];
            if (curP >= totalP) {
                answer = i - 1;

                int hour = answer / 60;
                int min = answer % 60;

                return String.format("%02d", hour) + ":" + String.format("%02d", min);
            }

            if (busArrivalSet.contains(i)) {
                curP -= Math.min(m, curP);
                totalP -= m;
            }
        }

        return "";
    }
}
