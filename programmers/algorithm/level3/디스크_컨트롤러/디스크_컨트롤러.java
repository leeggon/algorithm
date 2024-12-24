package programmers.algorithm.level3.디스크_컨트롤러;

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {

        // pq는 작업의 소요시간 순으로 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        // jobs 배열 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        int idx = 0; // jobs 배열 순회 인덱스
        int end = 0; // 수행되고난 직후의 시간
        int count= 0; // 수행된 요청의 갯수
        int answer = 0;

        while (count < jobs.length) { // 요청이 모두 수행될 때까지

            // 하나의 작업이 완료되는 시점까지 들어온 모든 요청을 pq에 넣음
            while (idx < jobs.length && end >= jobs[idx][0]) {
                pq.offer(jobs[idx++]);
            }

            // 큐가 비어있다면 작업 완료 end 시각 이후에 다시 요청이 들어온다는 의미
            // end를 요청의 가장 처음으로 초기화시켜줌
            if (pq.isEmpty()) {
                end = jobs[idx][0];

                // 큐가 차있다면, end 이전 들어온 요청 중 가장 수행시간 짧은 거부터 수행(pq 대로)
            } else {

                int[] task = pq.poll();
                answer += (end + task[1]) - task[0];
                end += task[1];
                count++;

            }

        }

        return (int) Math.floor(answer / jobs.length);
    }

}
