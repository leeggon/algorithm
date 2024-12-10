package programmers.algorithm.level3.네트워크;

import java.util.*;

class 네트워크 {
    public int solution(int n, int[][] computers) {
        int result = 0;
        boolean[] visited = new boolean[n];
        for (int i=0; i<n; i++) {
            if (visited[i]) continue;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                int curV = queue.poll();
                for (int j=0; j<n; j++) {
                    if (j == curV) continue;
                    if (computers[curV][j] == 0) continue;
                    if (visited[j]) continue;

                    visited[j] = true;
                    queue.offer(j);
                }
            }

            result++;
        }

        return result;
    }
}
