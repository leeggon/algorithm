package programmers.algorithm.level3.가장_먼_노드;

import java.util.*;

class 가장_먼_노드 {
	static ArrayList<Integer>[] graph;
	static int[] distances;
	public static int solution(int n, int[][] vertex) {
		graph = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i=0; i<vertex.length; i++) {
			int[] info = vertex[i];
			int from = info[0];
			int to = info[1];
			graph[from].add(to);
			graph[to].add(from);

		}

		distances = new int[n+1];

		boolean[] visited = new boolean[n+1];
		Queue<Integer> queue = new ArrayDeque<>();
		visited[1] = true;
		queue.offer(1);

		int level = 0;
		while (!queue.isEmpty()) {
			int queueSize = queue.size();
			for (int i=0; i<queueSize; i++) {
				int curNode = queue.poll();
				distances[curNode] = level;

				for (int nextNode : graph[curNode]) {
					if (visited[nextNode]) continue;
					visited[nextNode] = true;
					queue.offer(nextNode);
				}

			}

			level++;
		}

		int maxVal = 0;
		for (int i=1; i<distances.length; i++) {
			maxVal = Math.max(maxVal, distances[i]);
		}

		int result = 0;
		for (int i=1; i<distances.length; i++) {
			if (distances[i] == maxVal) {
				result++;
			}
		}


		return result;
	}
}
