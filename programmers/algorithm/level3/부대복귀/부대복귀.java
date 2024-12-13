package programmers.algorithm.level3.부대복귀;

import java.util.*;

class 부대복귀 {
    ArrayList<Integer>[] graph;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new ArrayList[n+1];
        int[] distances = new int[n+1];
        Arrays.fill(distances, -1);
        int[] result = new int[sources.length];

        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
            // System.out.println(graph[i]);
        }

        for (int i=0; i<roads.length; i++) {
            int[] road = roads[i];
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }

        bfs(destination, distances, n);
        for (int i=0; i<sources.length; i++) {
            result[i] = distances[sources[i]];
        }

        return result;
    }

    private void bfs(int destination, int[] distances, int n) {
        Queue<Integer> queue = new ArrayDeque<>();

        distances[destination] = 0;
        queue.offer(destination);

        int cnt = 1;
        while(!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i=0; i<queueSize; i++) {
                int curNode = queue.poll();

                for (int nextNode : graph[curNode]) {
                    if (distances[nextNode] == -1) {
                        distances[nextNode] = cnt;
                        queue.offer(nextNode);
                    }
                }
            }

            cnt++;
        }

    }
}
