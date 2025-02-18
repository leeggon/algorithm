package programmers.algorithm.level2.과제_진행하기;

import java.util.*;

class 과제_진행하기 {

    class StartTask implements Comparable<StartTask> {
        String name;
        int startTime;
        int duration;

        StartTask(String name, int startTime, int duration) {
            this.name = name;
            this.startTime = startTime;
            this.duration = duration;
        }

        @Override
        public int compareTo(StartTask o) {
            return Integer.compare(this.startTime, o.startTime);
        }
    }

    class RemainderTask implements Comparable<RemainderTask> {
        String name;
        int pauseTime;
        int remainderTime;

        RemainderTask (String name, int pauseTime, int remainderTime) {
            this.name = name;
            this.pauseTime = pauseTime;
            this.remainderTime = remainderTime;
        }

        @Override
        public int compareTo(RemainderTask o) {
            return Integer.compare(o.pauseTime, this.pauseTime);
        }
    }

    public List<String> solution(String[][] plans) {

        List<String> answer = new ArrayList<>();

        PriorityQueue<StartTask> startPq = new PriorityQueue<>();
        PriorityQueue<RemainderTask> remainderPq = new PriorityQueue<>();

        for (String[] plan : plans) {
            String[] time = plan[1].split(":");
            int startTime = 60 * Integer.parseInt(time[0]) + Integer.parseInt(time[1]);

            StartTask st = new StartTask(plan[0], startTime, Integer.parseInt(plan[2]));
            startPq.offer(st);
        }

        while (!startPq.isEmpty()) {

            StartTask st = startPq.poll();

            if (startPq.isEmpty()) {
                answer.add(st.name);
            } else {

                StartTask nextSt = startPq.peek();
                if (st.startTime + st.duration <= nextSt.startTime) {
                    answer.add(st.name);

                    int currentTime = st.startTime + st.duration;
                    while (!remainderPq.isEmpty()) {

                        RemainderTask rt = remainderPq.poll();
                        if (currentTime + rt.remainderTime <= nextSt.startTime) {
                            answer.add(rt.name);
                            currentTime += rt.remainderTime;
                        } else {
                            int remainderTime = rt.remainderTime - (nextSt.startTime - currentTime);
                            int pauseTime = nextSt.startTime;
                            remainderPq.offer(new RemainderTask(rt.name, pauseTime, remainderTime));
                            break;
                        }
                    }

                } else {
                    int pauseTime = nextSt.startTime;
                    int remainderTime = st.duration - (nextSt.startTime - st.startTime);
                    RemainderTask rt = new RemainderTask(st.name, pauseTime, remainderTime);
                    remainderPq.offer(rt);
                }
            }

        }

        while (!remainderPq.isEmpty()) {
            RemainderTask rt = remainderPq.poll();
            answer.add(rt.name);
        }

        return answer;

    }
}
